package com.frenchquest.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Single-row table storing the user's overall game state.
 * Always query with id = 1.
 */
@Entity(tableName = "user_progress")
data class UserProgress(
    @PrimaryKey
    val id: Int = 1,

    var totalXP: Int = 0,
    var level: Int = 1,
    var cefrLevel: String = "A1",

    var currentStreak: Int = 0,
    var longestStreak: Int = 0,
    var lastPlayedDate: Long = 0L,

    var listeningAccuracy: Float = 0f,
    var speakingAccuracy: Float = 0f,
    var readingAccuracy: Float = 0f,
    var writingAccuracy: Float = 0f,

    var wordsLearned: Int = 0,
    var totalWordsAttempted: Int = 0,

    var dailyXPGoal: Int = 20,
    var dailyXPToday: Int = 0,
    var dailyWordsGoal: Int = 10,
    var dailyWordsToday: Int = 0,
    var dailyMinutesGoal: Int = 10,
    var dailyMinutesToday: Int = 0,

    var difficultyLevel: Int = 1,
    var recentCorrect: Int = 0,
    var recentTotal: Int = 0,

    var hasCompletedPlacement: Boolean = false,

    var weeklyGamesPlayed: Int = 0,
    var weeklyListeningGames: Int = 0,
    var weekStartDate: Long = 0L
) {
    companion object {
        val XP_THRESHOLDS = intArrayOf(
            0, 100, 250, 500, 850, 1300, 1900, 2600, 3500, 4600,
            5900, 7400, 9100, 11100, 13400, 16000, 19000, 22500, 26500, 31000
        )
    }

    fun getXPForNextLevel(): Int =
        if (level >= XP_THRESHOLDS.size) Int.MAX_VALUE else XP_THRESHOLDS[level.coerceAtMost(XP_THRESHOLDS.size - 1)]

    fun getXPProgressInLevel(): Int =
        if (level <= 1) totalXP else totalXP - XP_THRESHOLDS[level - 1]

    fun getXPRangeForLevel(): Int {
        if (level >= XP_THRESHOLDS.size) return 1
        val prev = if (level <= 1) 0 else XP_THRESHOLDS[level - 1]
        return XP_THRESHOLDS[level] - prev
    }

    fun getLevelProgress(): Float =
        getXPProgressInLevel().toFloat() / getXPRangeForLevel()

    /** Call after gaining XP; auto-levels up. Returns true if leveled up. */
    fun addXP(xp: Int): Boolean {
        totalXP += xp
        dailyXPToday += xp
        var newLevel = 1
        for (i in XP_THRESHOLDS.indices.reversed()) {
            if (i >= 1 && totalXP >= XP_THRESHOLDS[i]) {
                newLevel = (i + 1).coerceAtMost(XP_THRESHOLDS.size)
                break
            }
        }
        val leveledUp = newLevel > level
        level = newLevel
        return leveledUp
    }

    fun updateAccuracy(skill: String, correct: Boolean) {
        val weight = 0.15f
        val value = if (correct) 1f else 0f
        when (skill) {
            "listening" -> listeningAccuracy = listeningAccuracy * (1 - weight) + value * weight
            "speaking" -> speakingAccuracy = speakingAccuracy * (1 - weight) + value * weight
            "reading" -> readingAccuracy = readingAccuracy * (1 - weight) + value * weight
            "writing" -> writingAccuracy = writingAccuracy * (1 - weight) + value * weight
        }
    }
}
