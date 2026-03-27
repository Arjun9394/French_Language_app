package com.frenchquest.game.engines

import com.frenchquest.data.models.Word

/**
 * Simplified SM-2 spaced repetition algorithm.
 *
 * After each answer:
 *   - Correct (quality >= 3): increase interval, increase ease
 *   - Wrong (quality < 3): reset repetitions, short interval
 *
 * Quality scale: 0-5 (we map: correct=5, almost=3, wrong=0)
 */
object SpacedRepetitionEngine {

    const val QUALITY_CORRECT = 5
    const val QUALITY_CORRECT_SLOW = 3
    const val QUALITY_WRONG = 0

    private const val MIN_EASE = 1.3f
    private const val DAY_MS = 86_400_000L

    /**
     * Update the word's SM-2 fields in-place after an answer.
     * Call wordDao.update(word) afterwards to persist.
     */
    fun processAnswer(word: Word, quality: Int) {
        if (quality >= 3) {
            word.timesCorrect++

            word.interval = when {
                word.repetitions == 0 -> 1
                word.repetitions == 1 -> 6
                else -> (word.interval * word.easeFactor).toInt()
            }

            word.easeFactor = (word.easeFactor +
                    (0.1f - (5 - quality) * (0.08f + (5 - quality) * 0.02f)))
                .coerceAtLeast(MIN_EASE)

            word.repetitions++

            if (word.repetitions >= 3) {
                word.isLearned = true
            }
        } else {
            word.timesWrong++
            word.repetitions = 0
            word.interval = 1
        }

        word.nextReviewDate = System.currentTimeMillis() + (word.interval * DAY_MS)
    }

    fun isDue(word: Word): Boolean =
        word.nextReviewDate <= System.currentTimeMillis()

    fun getNextReviewLabel(word: Word): String {
        val diffMs = word.nextReviewDate - System.currentTimeMillis()
        if (diffMs <= 0) return "Due now"
        val days = diffMs / DAY_MS
        return when {
            days == 0L -> "Due today"
            days == 1L -> "Due tomorrow"
            else -> "Due in $days days"
        }
    }
}
