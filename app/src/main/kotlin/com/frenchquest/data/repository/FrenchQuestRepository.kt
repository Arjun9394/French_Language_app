package com.frenchquest.data.repository

import com.frenchquest.data.database.BadgeDao
import com.frenchquest.data.database.UserProgressDao
import com.frenchquest.data.database.WordDao
import com.frenchquest.data.models.Badge
import com.frenchquest.data.models.UserProgress
import com.frenchquest.data.models.Word

/**
 * Single source of truth for all FrenchQuest data.
 * All ViewModels go through here — never touch DAOs directly.
 */
class FrenchQuestRepository(
    private val wordDao: WordDao,
    private val userProgressDao: UserProgressDao,
    private val badgeDao: BadgeDao
) {
    // ── Progress ──────────────────────────────
    fun getProgressLive() = userProgressDao.getProgress()
    suspend fun getProgress() = userProgressDao.getProgressSync()
    suspend fun updateProgress(progress: UserProgress) = userProgressDao.update(progress)
    suspend fun insertProgress(progress: UserProgress) = userProgressDao.insert(progress)

    // ── Words ─────────────────────────────────
    suspend fun getRandomWords(cefrLevel: String, count: Int) = wordDao.getRandomWords(cefrLevel, count)
    suspend fun getRandomFromPack(packId: String, count: Int) = wordDao.getRandomFromPack(packId, count)
    suspend fun getDueWords(limit: Int) = wordDao.getWordsDueForReview(System.currentTimeMillis(), limit)
    suspend fun updateWord(word: Word) = wordDao.update(word)
    suspend fun getLearnedCount() = wordDao.getLearnedCount()
    suspend fun getAllPackIds() = wordDao.getAllPackIds()
    suspend fun getLearnedCountForPack(packId: String) = wordDao.getLearnedCountForPack(packId)
    suspend fun getTotalCountForPack(packId: String) = wordDao.getTotalCountForPack(packId)

    // ── Badges ────────────────────────────────
    fun getAllBadgesLive() = badgeDao.getAllBadges()
    suspend fun getBadge(id: String) = badgeDao.getBadgeSync(id)
    suspend fun updateBadge(badge: Badge) = badgeDao.update(badge)
    suspend fun getUnlockedBadgeCount() = badgeDao.getUnlockedCount()
}
