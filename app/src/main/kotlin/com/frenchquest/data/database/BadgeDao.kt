package com.frenchquest.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.frenchquest.data.models.Badge

@Dao
interface BadgeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(badge: Badge)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(badges: List<Badge>)

    @Update
    suspend fun update(badge: Badge)

    @Query("SELECT * FROM badges ORDER BY isUnlocked DESC, id")
    fun getAllBadges(): LiveData<List<Badge>>

    @Query("SELECT * FROM badges WHERE id = :badgeId")
    suspend fun getBadgeSync(badgeId: String): Badge?

    @Query("SELECT COUNT(*) FROM badges WHERE isUnlocked = 1")
    suspend fun getUnlockedCount(): Int
}
