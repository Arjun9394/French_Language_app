package com.frenchquest.ui.game

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.frenchquest.data.database.FrenchDatabase
import com.frenchquest.data.models.*
import com.frenchquest.data.repository.FrenchQuestRepository
import com.frenchquest.game.engines.AdaptiveDifficultyEngine
import com.frenchquest.utils.GameResultEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

/**
 * Shared ViewModel for all mini-game fragments.
 * Handles: word loading, XP, streaks, difficulty, badge checks.
 * Uses Kotlin coroutines instead of raw thread executors.
 */
class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val db = FrenchDatabase.getInstance(application)
    val repository = FrenchQuestRepository(db.wordDao(), db.userProgressDao(), db.badgeDao())
    private val difficultyEngine = AdaptiveDifficultyEngine()

    private val _progress = MutableLiveData<UserProgress>()
    val progress: LiveData<UserProgress> = _progress

    init {
        loadProgress()
    }

    private fun loadProgress() {
        viewModelScope.launch(Dispatchers.IO) {
            val p = repository.getProgress() ?: UserProgress()

            // Reset daily counters if it's a new day
            val todayDay = System.currentTimeMillis() / 86_400_000L
            val lastDay = p.lastPlayedDate / 86_400_000L
            if (todayDay != lastDay) {
                p.dailyXPToday = 0
                p.dailyWordsToday = 0
                p.dailyMinutesToday = 0
            }

            // Reset weekly counters if it's a new week (7+ days since weekStartDate)
            val weekDayMs = 7 * 86_400_000L
            if (p.weekStartDate == 0L || System.currentTimeMillis() - p.weekStartDate >= weekDayMs) {
                p.weeklyGamesPlayed = 0
                p.weeklyListeningGames = 0
                p.weekStartDate = System.currentTimeMillis()
                repository.updateProgress(p)
            }

            _progress.postValue(p)
        }
    }

    fun getProgress(): LiveData<UserProgress> = _progress

    val difficultyLevel: Int
        get() = _progress.value?.difficultyLevel ?: 1

    // ── Word loading (with coroutines) ────────

    fun loadRandomWords(cefrLevel: String, count: Int, callback: (List<Word>) -> Unit) {
        viewModelScope.launch {
            val words = withContext(Dispatchers.IO) {
                repository.getRandomWords(cefrLevel, count)
            }
            callback(words)
        }
    }

    fun loadWordsForPack(packId: String, count: Int, callback: (List<Word>) -> Unit) {
        viewModelScope.launch {
            val words = withContext(Dispatchers.IO) {
                repository.getRandomFromPack(packId, count)
            }
            callback(words)
        }
    }

    fun loadDueWords(limit: Int, callback: (List<Word>) -> Unit) {
        viewModelScope.launch {
            val words = withContext(Dispatchers.IO) {
                repository.getDueWords(limit)
            }
            callback(words)
        }
    }

    fun updateWord(word: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateWord(word)
        }
    }

    // ── Placement test result ─────────────────

    fun setPlacementResult(cefrLevel: String, score: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val p = repository.getProgress() ?: UserProgress()
            p.cefrLevel = cefrLevel
            p.hasCompletedPlacement = true
            if (cefrLevel == "A2") unlockBadge(Badge.A2_UNLOCKED)
            repository.updateProgress(p)
            _progress.postValue(p)
        }
    }

    // ── Badges ────────────────────────────────

    fun getAllBadges() = repository.getAllBadgesLive()

    // ── Game completion ───────────────────────

    fun onGameCompleted(result: GameResultEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            val p = repository.getProgress() ?: return@launch

            // 1. Add XP
            p.addXP(result.xpEarned)

            // 2. Update skill accuracy (weighted by actual game performance)
            val skill = getSkillForGame(result.gameType)
            val gameAccuracy = if (result.total > 0) result.correct.toFloat() / result.total else 0f
            val weight = 0.15f
            when (skill) {
                "listening" -> p.listeningAccuracy = p.listeningAccuracy * (1 - weight) + gameAccuracy * weight
                "speaking" -> p.speakingAccuracy = p.speakingAccuracy * (1 - weight) + gameAccuracy * weight
                "reading" -> p.readingAccuracy = p.readingAccuracy * (1 - weight) + gameAccuracy * weight
                "writing" -> p.writingAccuracy = p.writingAccuracy * (1 - weight) + gameAccuracy * weight
            }

            // 3. Update adaptive difficulty
            difficultyEngine.recordSession(p, result.correct, result.total)

            // 4. Update streak
            updateStreak(p)

            // 5. Update words learned count
            p.wordsLearned = repository.getLearnedCount()
            p.dailyWordsToday += result.total

            // 6. Weekly games
            p.weeklyGamesPlayed++
            if (result.gameType == "listen_tap") p.weeklyListeningGames++

            // 7. Save progress
            repository.updateProgress(p)
            _progress.postValue(p)

            // 8. Check badges
            checkAndUnlockBadges(p, result)

            // 9. Analytics (local)
            logAnalytics(result)
        }
    }

    private fun getSkillForGame(gameType: String): String = when (gameType) {
        "listen_tap" -> "listening"
        "pronounce" -> "speaking"
        "fill_gap" -> "writing"
        else -> "reading"
    }

    private fun updateStreak(progress: UserProgress) {
        val todayMs = System.currentTimeMillis()
        val todayDay = todayMs / 86_400_000L
        val lastDay = progress.lastPlayedDate / 86_400_000L

        when {
            todayDay == lastDay -> { /* Already played today */ }
            todayDay == lastDay + 1 -> {
                progress.currentStreak++
                if (progress.currentStreak > progress.longestStreak)
                    progress.longestStreak = progress.currentStreak
            }
            else -> progress.currentStreak = 1
        }

        progress.lastPlayedDate = todayMs
    }

    private suspend fun checkAndUnlockBadges(progress: UserProgress, result: GameResultEvent) {
        if (progress.wordsLearned >= 1) unlockBadge(Badge.FIRST_WORD)
        if (progress.wordsLearned >= 10) unlockBadge(Badge.WORDS_10)
        if (progress.wordsLearned >= 50) unlockBadge(Badge.WORDS_50)
        if (progress.wordsLearned >= 100) unlockBadge(Badge.WORDS_100)

        if (progress.currentStreak >= 3) unlockBadge(Badge.STREAK_3)
        if (progress.currentStreak >= 7) unlockBadge(Badge.STREAK_7)
        if (progress.currentStreak >= 30) unlockBadge(Badge.STREAK_30)

        if (progress.level >= 5) unlockBadge(Badge.LEVEL_5)
        if (progress.level >= 10) unlockBadge(Badge.LEVEL_10)

        if (result.total >= 5 && result.correct == result.total) unlockBadge(Badge.PERFECT_QUIZ)
        if (progress.cefrLevel == "A2") unlockBadge(Badge.A2_UNLOCKED)

        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        if (hour < 8) unlockBadge(Badge.EARLY_BIRD)
        if (hour >= 22) unlockBadge(Badge.NIGHT_OWL)
    }

    private suspend fun unlockBadge(badgeId: String) {
        val badge = repository.getBadge(badgeId)
        if (badge != null && !badge.isUnlocked) {
            badge.isUnlocked = true
            badge.unlockedAt = System.currentTimeMillis()
            repository.updateBadge(badge)
        }
    }

    private fun logAnalytics(result: GameResultEvent) {
        Log.d("Analytics",
            "GAME_END game=${result.gameType}" +
                    " acc=${"%.0f%%".format(result.accuracy * 100)}" +
                    " xp=${result.xpEarned}" +
                    " difficulty=$difficultyLevel"
        )
    }
}
