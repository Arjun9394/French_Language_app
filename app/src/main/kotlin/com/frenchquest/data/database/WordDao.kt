package com.frenchquest.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.frenchquest.data.models.Word

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(word: Word)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(words: List<Word>)

    @Update
    suspend fun update(word: Word)

    @Query("SELECT * FROM words WHERE packId = :packId ORDER BY id")
    fun getWordsByPack(packId: String): LiveData<List<Word>>

    @Query("SELECT * FROM words WHERE packId = :packId ORDER BY id")
    suspend fun getWordsByPackSync(packId: String): List<Word>

    @Query("SELECT * FROM words WHERE cefrLevel = :level ORDER BY id")
    suspend fun getWordsByLevel(level: String): List<Word>

    @Query("SELECT * FROM words WHERE nextReviewDate <= :now ORDER BY nextReviewDate LIMIT :limit")
    suspend fun getWordsDueForReview(now: Long, limit: Int): List<Word>

    @Query("SELECT * FROM words WHERE cefrLevel = :level ORDER BY RANDOM() LIMIT :count")
    suspend fun getRandomWords(level: String, count: Int): List<Word>

    @Query("SELECT * FROM words WHERE packId = :packId ORDER BY RANDOM() LIMIT :count")
    suspend fun getRandomFromPack(packId: String, count: Int): List<Word>

    @Query("SELECT * FROM words WHERE isLearned = 0 AND cefrLevel = :level ORDER BY RANDOM() LIMIT :count")
    suspend fun getUnlearnedWords(level: String, count: Int): List<Word>

    @Query("SELECT COUNT(*) FROM words WHERE isLearned = 1")
    suspend fun getLearnedCount(): Int

    @Query("SELECT COUNT(*) FROM words WHERE packId = :packId AND isLearned = 1")
    suspend fun getLearnedCountForPack(packId: String): Int

    @Query("SELECT COUNT(*) FROM words WHERE packId = :packId")
    suspend fun getTotalCountForPack(packId: String): Int

    @Query("SELECT DISTINCT packId FROM words")
    suspend fun getAllPackIds(): List<String>
}
