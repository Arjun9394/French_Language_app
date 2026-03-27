package com.frenchquest.game.engines

import com.frenchquest.data.models.UserProgress

/**
 * Adjusts the game's difficulty level based on recent performance.
 *
 * Difficulty levels 1-5:
 *   1 = Very easy  (only A1 core words, 4 choices, slow audio)
 *   2 = Easy       (A1 all words, 4 choices, normal audio)
 *   3 = Medium     (A1+A2 mix, 4 choices, faster games)
 *   4 = Hard       (A2, 5 choices, time pressure)
 *   5 = Challenge  (A2 advanced, no hints, strict time)
 */
class AdaptiveDifficultyEngine {

    private var consecutiveGoodSessions = 0
    private var consecutiveBadSessions = 0

    companion object {
        private const val WINDOW_SIZE = 10
        private const val BUMP_UP_THRESHOLD = 0.80f
        private const val STEP_DOWN_THRESHOLD = 0.40f
        private const val SESSIONS_TO_BUMP = 3
        private const val SESSIONS_TO_DROP = 2

        fun getChoiceCount(difficultyLevel: Int): Int =
            if (difficultyLevel <= 3) 4 else 5

        fun showPhoneticHint(difficultyLevel: Int): Boolean =
            difficultyLevel <= 2

        fun autoPlayAudio(difficultyLevel: Int): Boolean =
            difficultyLevel <= 3

        fun getTimeLimitMs(difficultyLevel: Int): Int = when (difficultyLevel) {
            1, 2 -> 0
            3 -> 15000
            4 -> 10000
            5 -> 7000
            else -> 0
        }

        fun getCEFRLevel(difficultyLevel: Int): String =
            if (difficultyLevel >= 3) "A2" else "A1"

        fun getXPMultiplier(difficultyLevel: Int): Float =
            0.8f + (difficultyLevel * 0.2f)
    }

    fun recordSession(progress: UserProgress, correctAnswers: Int, totalAnswers: Int): Int {
        if (totalAnswers == 0) return progress.difficultyLevel

        val accuracy = correctAnswers.toFloat() / totalAnswers

        progress.recentCorrect = (progress.recentCorrect + correctAnswers).coerceAtMost(WINDOW_SIZE)
        progress.recentTotal = (progress.recentTotal + totalAnswers).coerceAtMost(WINDOW_SIZE)

        when {
            accuracy >= BUMP_UP_THRESHOLD -> {
                consecutiveGoodSessions++
                consecutiveBadSessions = 0
            }
            accuracy < STEP_DOWN_THRESHOLD -> {
                consecutiveBadSessions++
                consecutiveGoodSessions = 0
            }
            else -> {
                consecutiveGoodSessions = 0
                consecutiveBadSessions = 0
            }
        }

        when {
            consecutiveGoodSessions >= SESSIONS_TO_BUMP && progress.difficultyLevel < 5 -> {
                progress.difficultyLevel++
                consecutiveGoodSessions = 0
            }
            consecutiveBadSessions >= SESSIONS_TO_DROP && progress.difficultyLevel > 1 -> {
                progress.difficultyLevel--
                consecutiveBadSessions = 0
            }
        }

        return progress.difficultyLevel
    }
}
