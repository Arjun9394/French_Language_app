package com.frenchquest.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Core vocabulary unit.
 * Stores the French word, its English translation, metadata for spaced repetition,
 * and which themed pack it belongs to.
 */
@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // Content
    val french: String = "",
    val english: String = "",
    val phonetic: String? = null,
    val exampleSentence: String? = null,
    val exampleTranslation: String? = null,
    val imageResName: String? = null,
    val packId: String = "",
    val cefrLevel: String = "A1",
    val category: String? = null,

    // Spaced Repetition (SM-2 simplified)
    var easeFactor: Float = 2.5f,
    var interval: Int = 1,
    var repetitions: Int = 0,
    var nextReviewDate: Long = 0L,

    // Stats
    var timesCorrect: Int = 0,
    var timesWrong: Int = 0,
    var isLearned: Boolean = false
) {
    val accuracy: Float
        get() {
            val total = timesCorrect + timesWrong
            return if (total == 0) 0f else timesCorrect.toFloat() / total
        }

    val displayLabel: String
        get() = "$french → $english"
}
