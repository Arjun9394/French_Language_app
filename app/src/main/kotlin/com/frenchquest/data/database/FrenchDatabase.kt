package com.frenchquest.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.frenchquest.data.models.Badge
import com.frenchquest.data.models.UserProgress
import com.frenchquest.data.models.Word
import com.frenchquest.game.content.VocabContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Word::class, UserProgress::class, Badge::class],
    version = 1,
    exportSchema = false
)
abstract class FrenchDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao
    abstract fun userProgressDao(): UserProgressDao
    abstract fun badgeDao(): BadgeDao

    companion object {
        @Volatile
        private var INSTANCE: FrenchDatabase? = null

        fun getInstance(context: Context): FrenchDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): FrenchDatabase {
            // Bug 2 fix: capture via lateinit var so the callback always holds a valid
            // reference regardless of when Room fires onCreate (which may be before
            // INSTANCE is assigned in the synchronized block).
            lateinit var instance: FrenchDatabase
            instance = Room.databaseBuilder(
                context.applicationContext,
                FrenchDatabase::class.java,
                "frenchquest.db"
            )
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            seedDatabase(instance)
                        }
                    }
                })
                .build()
            return instance
        }

        private suspend fun seedDatabase(db: FrenchDatabase) {
            // 1. Default user progress
            db.userProgressDao().insert(UserProgress())

            // 2. Badges
            db.badgeDao().insertAll(Badge.getDefaultBadges())

            // 3. Vocabulary packs
            db.wordDao().insertAll(VocabContent.getAllWords())
        }
    }
}
