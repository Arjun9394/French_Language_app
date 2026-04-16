package com.frenchquest.data.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class FrenchDatabase_Impl extends FrenchDatabase {
  private volatile WordDao _wordDao;

  private volatile UserProgressDao _userProgressDao;

  private volatile BadgeDao _badgeDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `words` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `french` TEXT NOT NULL, `english` TEXT NOT NULL, `phonetic` TEXT, `exampleSentence` TEXT, `exampleTranslation` TEXT, `imageResName` TEXT, `packId` TEXT NOT NULL, `cefrLevel` TEXT NOT NULL, `category` TEXT, `easeFactor` REAL NOT NULL, `interval` INTEGER NOT NULL, `repetitions` INTEGER NOT NULL, `nextReviewDate` INTEGER NOT NULL, `timesCorrect` INTEGER NOT NULL, `timesWrong` INTEGER NOT NULL, `isLearned` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_progress` (`id` INTEGER NOT NULL, `totalXP` INTEGER NOT NULL, `level` INTEGER NOT NULL, `cefrLevel` TEXT NOT NULL, `currentStreak` INTEGER NOT NULL, `longestStreak` INTEGER NOT NULL, `lastPlayedDate` INTEGER NOT NULL, `listeningAccuracy` REAL NOT NULL, `speakingAccuracy` REAL NOT NULL, `readingAccuracy` REAL NOT NULL, `writingAccuracy` REAL NOT NULL, `wordsLearned` INTEGER NOT NULL, `totalWordsAttempted` INTEGER NOT NULL, `dailyXPGoal` INTEGER NOT NULL, `dailyXPToday` INTEGER NOT NULL, `dailyWordsGoal` INTEGER NOT NULL, `dailyWordsToday` INTEGER NOT NULL, `dailyMinutesGoal` INTEGER NOT NULL, `dailyMinutesToday` INTEGER NOT NULL, `difficultyLevel` INTEGER NOT NULL, `recentCorrect` INTEGER NOT NULL, `recentTotal` INTEGER NOT NULL, `hasCompletedPlacement` INTEGER NOT NULL, `weeklyGamesPlayed` INTEGER NOT NULL, `weeklyListeningGames` INTEGER NOT NULL, `weekStartDate` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `badges` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `iconResName` TEXT NOT NULL, `isUnlocked` INTEGER NOT NULL, `unlockedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'd78e3a26250d1907146f1863455376b2')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `words`");
        db.execSQL("DROP TABLE IF EXISTS `user_progress`");
        db.execSQL("DROP TABLE IF EXISTS `badges`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsWords = new HashMap<String, TableInfo.Column>(17);
        _columnsWords.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("french", new TableInfo.Column("french", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("english", new TableInfo.Column("english", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("phonetic", new TableInfo.Column("phonetic", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("exampleSentence", new TableInfo.Column("exampleSentence", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("exampleTranslation", new TableInfo.Column("exampleTranslation", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("imageResName", new TableInfo.Column("imageResName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("packId", new TableInfo.Column("packId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("cefrLevel", new TableInfo.Column("cefrLevel", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("easeFactor", new TableInfo.Column("easeFactor", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("interval", new TableInfo.Column("interval", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("repetitions", new TableInfo.Column("repetitions", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("nextReviewDate", new TableInfo.Column("nextReviewDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("timesCorrect", new TableInfo.Column("timesCorrect", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("timesWrong", new TableInfo.Column("timesWrong", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("isLearned", new TableInfo.Column("isLearned", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWords = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWords = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWords = new TableInfo("words", _columnsWords, _foreignKeysWords, _indicesWords);
        final TableInfo _existingWords = TableInfo.read(db, "words");
        if (!_infoWords.equals(_existingWords)) {
          return new RoomOpenHelper.ValidationResult(false, "words(com.frenchquest.data.models.Word).\n"
                  + " Expected:\n" + _infoWords + "\n"
                  + " Found:\n" + _existingWords);
        }
        final HashMap<String, TableInfo.Column> _columnsUserProgress = new HashMap<String, TableInfo.Column>(26);
        _columnsUserProgress.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("totalXP", new TableInfo.Column("totalXP", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("level", new TableInfo.Column("level", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("cefrLevel", new TableInfo.Column("cefrLevel", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("currentStreak", new TableInfo.Column("currentStreak", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("longestStreak", new TableInfo.Column("longestStreak", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("lastPlayedDate", new TableInfo.Column("lastPlayedDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("listeningAccuracy", new TableInfo.Column("listeningAccuracy", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("speakingAccuracy", new TableInfo.Column("speakingAccuracy", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("readingAccuracy", new TableInfo.Column("readingAccuracy", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("writingAccuracy", new TableInfo.Column("writingAccuracy", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("wordsLearned", new TableInfo.Column("wordsLearned", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("totalWordsAttempted", new TableInfo.Column("totalWordsAttempted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("dailyXPGoal", new TableInfo.Column("dailyXPGoal", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("dailyXPToday", new TableInfo.Column("dailyXPToday", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("dailyWordsGoal", new TableInfo.Column("dailyWordsGoal", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("dailyWordsToday", new TableInfo.Column("dailyWordsToday", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("dailyMinutesGoal", new TableInfo.Column("dailyMinutesGoal", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("dailyMinutesToday", new TableInfo.Column("dailyMinutesToday", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("difficultyLevel", new TableInfo.Column("difficultyLevel", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("recentCorrect", new TableInfo.Column("recentCorrect", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("recentTotal", new TableInfo.Column("recentTotal", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("hasCompletedPlacement", new TableInfo.Column("hasCompletedPlacement", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("weeklyGamesPlayed", new TableInfo.Column("weeklyGamesPlayed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("weeklyListeningGames", new TableInfo.Column("weeklyListeningGames", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProgress.put("weekStartDate", new TableInfo.Column("weekStartDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserProgress = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserProgress = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserProgress = new TableInfo("user_progress", _columnsUserProgress, _foreignKeysUserProgress, _indicesUserProgress);
        final TableInfo _existingUserProgress = TableInfo.read(db, "user_progress");
        if (!_infoUserProgress.equals(_existingUserProgress)) {
          return new RoomOpenHelper.ValidationResult(false, "user_progress(com.frenchquest.data.models.UserProgress).\n"
                  + " Expected:\n" + _infoUserProgress + "\n"
                  + " Found:\n" + _existingUserProgress);
        }
        final HashMap<String, TableInfo.Column> _columnsBadges = new HashMap<String, TableInfo.Column>(6);
        _columnsBadges.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBadges.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBadges.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBadges.put("iconResName", new TableInfo.Column("iconResName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBadges.put("isUnlocked", new TableInfo.Column("isUnlocked", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBadges.put("unlockedAt", new TableInfo.Column("unlockedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBadges = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBadges = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBadges = new TableInfo("badges", _columnsBadges, _foreignKeysBadges, _indicesBadges);
        final TableInfo _existingBadges = TableInfo.read(db, "badges");
        if (!_infoBadges.equals(_existingBadges)) {
          return new RoomOpenHelper.ValidationResult(false, "badges(com.frenchquest.data.models.Badge).\n"
                  + " Expected:\n" + _infoBadges + "\n"
                  + " Found:\n" + _existingBadges);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "d78e3a26250d1907146f1863455376b2", "281a1abc356b67a1cd1a9f59da6ee990");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "words","user_progress","badges");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `words`");
      _db.execSQL("DELETE FROM `user_progress`");
      _db.execSQL("DELETE FROM `badges`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(WordDao.class, WordDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserProgressDao.class, UserProgressDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(BadgeDao.class, BadgeDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public WordDao wordDao() {
    if (_wordDao != null) {
      return _wordDao;
    } else {
      synchronized(this) {
        if(_wordDao == null) {
          _wordDao = new WordDao_Impl(this);
        }
        return _wordDao;
      }
    }
  }

  @Override
  public UserProgressDao userProgressDao() {
    if (_userProgressDao != null) {
      return _userProgressDao;
    } else {
      synchronized(this) {
        if(_userProgressDao == null) {
          _userProgressDao = new UserProgressDao_Impl(this);
        }
        return _userProgressDao;
      }
    }
  }

  @Override
  public BadgeDao badgeDao() {
    if (_badgeDao != null) {
      return _badgeDao;
    } else {
      synchronized(this) {
        if(_badgeDao == null) {
          _badgeDao = new BadgeDao_Impl(this);
        }
        return _badgeDao;
      }
    }
  }
}
