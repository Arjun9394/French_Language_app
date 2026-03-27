package com.frenchquest.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.frenchquest.data.models.UserProgress

@Dao
interface UserProgressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(progress: UserProgress)

    @Update
    suspend fun update(progress: UserProgress)

    @Query("SELECT * FROM user_progress WHERE id = 1")
    suspend fun getProgressSync(): UserProgress?

    @Query("SELECT * FROM user_progress WHERE id = 1")
    fun getProgress(): LiveData<UserProgress?>
}
