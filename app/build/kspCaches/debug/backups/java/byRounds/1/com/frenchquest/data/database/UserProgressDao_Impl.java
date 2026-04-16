package com.frenchquest.data.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.frenchquest.data.models.UserProgress;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class UserProgressDao_Impl implements UserProgressDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserProgress> __insertionAdapterOfUserProgress;

  private final EntityDeletionOrUpdateAdapter<UserProgress> __updateAdapterOfUserProgress;

  public UserProgressDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserProgress = new EntityInsertionAdapter<UserProgress>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `user_progress` (`id`,`totalXP`,`level`,`cefrLevel`,`currentStreak`,`longestStreak`,`lastPlayedDate`,`listeningAccuracy`,`speakingAccuracy`,`readingAccuracy`,`writingAccuracy`,`wordsLearned`,`totalWordsAttempted`,`dailyXPGoal`,`dailyXPToday`,`dailyWordsGoal`,`dailyWordsToday`,`dailyMinutesGoal`,`dailyMinutesToday`,`difficultyLevel`,`recentCorrect`,`recentTotal`,`hasCompletedPlacement`,`weeklyGamesPlayed`,`weeklyListeningGames`,`weekStartDate`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserProgress entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getTotalXP());
        statement.bindLong(3, entity.getLevel());
        statement.bindString(4, entity.getCefrLevel());
        statement.bindLong(5, entity.getCurrentStreak());
        statement.bindLong(6, entity.getLongestStreak());
        statement.bindLong(7, entity.getLastPlayedDate());
        statement.bindDouble(8, entity.getListeningAccuracy());
        statement.bindDouble(9, entity.getSpeakingAccuracy());
        statement.bindDouble(10, entity.getReadingAccuracy());
        statement.bindDouble(11, entity.getWritingAccuracy());
        statement.bindLong(12, entity.getWordsLearned());
        statement.bindLong(13, entity.getTotalWordsAttempted());
        statement.bindLong(14, entity.getDailyXPGoal());
        statement.bindLong(15, entity.getDailyXPToday());
        statement.bindLong(16, entity.getDailyWordsGoal());
        statement.bindLong(17, entity.getDailyWordsToday());
        statement.bindLong(18, entity.getDailyMinutesGoal());
        statement.bindLong(19, entity.getDailyMinutesToday());
        statement.bindLong(20, entity.getDifficultyLevel());
        statement.bindLong(21, entity.getRecentCorrect());
        statement.bindLong(22, entity.getRecentTotal());
        final int _tmp = entity.getHasCompletedPlacement() ? 1 : 0;
        statement.bindLong(23, _tmp);
        statement.bindLong(24, entity.getWeeklyGamesPlayed());
        statement.bindLong(25, entity.getWeeklyListeningGames());
        statement.bindLong(26, entity.getWeekStartDate());
      }
    };
    this.__updateAdapterOfUserProgress = new EntityDeletionOrUpdateAdapter<UserProgress>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `user_progress` SET `id` = ?,`totalXP` = ?,`level` = ?,`cefrLevel` = ?,`currentStreak` = ?,`longestStreak` = ?,`lastPlayedDate` = ?,`listeningAccuracy` = ?,`speakingAccuracy` = ?,`readingAccuracy` = ?,`writingAccuracy` = ?,`wordsLearned` = ?,`totalWordsAttempted` = ?,`dailyXPGoal` = ?,`dailyXPToday` = ?,`dailyWordsGoal` = ?,`dailyWordsToday` = ?,`dailyMinutesGoal` = ?,`dailyMinutesToday` = ?,`difficultyLevel` = ?,`recentCorrect` = ?,`recentTotal` = ?,`hasCompletedPlacement` = ?,`weeklyGamesPlayed` = ?,`weeklyListeningGames` = ?,`weekStartDate` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserProgress entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getTotalXP());
        statement.bindLong(3, entity.getLevel());
        statement.bindString(4, entity.getCefrLevel());
        statement.bindLong(5, entity.getCurrentStreak());
        statement.bindLong(6, entity.getLongestStreak());
        statement.bindLong(7, entity.getLastPlayedDate());
        statement.bindDouble(8, entity.getListeningAccuracy());
        statement.bindDouble(9, entity.getSpeakingAccuracy());
        statement.bindDouble(10, entity.getReadingAccuracy());
        statement.bindDouble(11, entity.getWritingAccuracy());
        statement.bindLong(12, entity.getWordsLearned());
        statement.bindLong(13, entity.getTotalWordsAttempted());
        statement.bindLong(14, entity.getDailyXPGoal());
        statement.bindLong(15, entity.getDailyXPToday());
        statement.bindLong(16, entity.getDailyWordsGoal());
        statement.bindLong(17, entity.getDailyWordsToday());
        statement.bindLong(18, entity.getDailyMinutesGoal());
        statement.bindLong(19, entity.getDailyMinutesToday());
        statement.bindLong(20, entity.getDifficultyLevel());
        statement.bindLong(21, entity.getRecentCorrect());
        statement.bindLong(22, entity.getRecentTotal());
        final int _tmp = entity.getHasCompletedPlacement() ? 1 : 0;
        statement.bindLong(23, _tmp);
        statement.bindLong(24, entity.getWeeklyGamesPlayed());
        statement.bindLong(25, entity.getWeeklyListeningGames());
        statement.bindLong(26, entity.getWeekStartDate());
        statement.bindLong(27, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final UserProgress progress, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserProgress.insert(progress);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final UserProgress progress, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfUserProgress.handle(progress);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getProgressSync(final Continuation<? super UserProgress> $completion) {
    final String _sql = "SELECT * FROM user_progress WHERE id = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserProgress>() {
      @Override
      @Nullable
      public UserProgress call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTotalXP = CursorUtil.getColumnIndexOrThrow(_cursor, "totalXP");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfCefrLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "cefrLevel");
          final int _cursorIndexOfCurrentStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "currentStreak");
          final int _cursorIndexOfLongestStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "longestStreak");
          final int _cursorIndexOfLastPlayedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastPlayedDate");
          final int _cursorIndexOfListeningAccuracy = CursorUtil.getColumnIndexOrThrow(_cursor, "listeningAccuracy");
          final int _cursorIndexOfSpeakingAccuracy = CursorUtil.getColumnIndexOrThrow(_cursor, "speakingAccuracy");
          final int _cursorIndexOfReadingAccuracy = CursorUtil.getColumnIndexOrThrow(_cursor, "readingAccuracy");
          final int _cursorIndexOfWritingAccuracy = CursorUtil.getColumnIndexOrThrow(_cursor, "writingAccuracy");
          final int _cursorIndexOfWordsLearned = CursorUtil.getColumnIndexOrThrow(_cursor, "wordsLearned");
          final int _cursorIndexOfTotalWordsAttempted = CursorUtil.getColumnIndexOrThrow(_cursor, "totalWordsAttempted");
          final int _cursorIndexOfDailyXPGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyXPGoal");
          final int _cursorIndexOfDailyXPToday = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyXPToday");
          final int _cursorIndexOfDailyWordsGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyWordsGoal");
          final int _cursorIndexOfDailyWordsToday = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyWordsToday");
          final int _cursorIndexOfDailyMinutesGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyMinutesGoal");
          final int _cursorIndexOfDailyMinutesToday = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyMinutesToday");
          final int _cursorIndexOfDifficultyLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "difficultyLevel");
          final int _cursorIndexOfRecentCorrect = CursorUtil.getColumnIndexOrThrow(_cursor, "recentCorrect");
          final int _cursorIndexOfRecentTotal = CursorUtil.getColumnIndexOrThrow(_cursor, "recentTotal");
          final int _cursorIndexOfHasCompletedPlacement = CursorUtil.getColumnIndexOrThrow(_cursor, "hasCompletedPlacement");
          final int _cursorIndexOfWeeklyGamesPlayed = CursorUtil.getColumnIndexOrThrow(_cursor, "weeklyGamesPlayed");
          final int _cursorIndexOfWeeklyListeningGames = CursorUtil.getColumnIndexOrThrow(_cursor, "weeklyListeningGames");
          final int _cursorIndexOfWeekStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "weekStartDate");
          final UserProgress _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpTotalXP;
            _tmpTotalXP = _cursor.getInt(_cursorIndexOfTotalXP);
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpCefrLevel;
            _tmpCefrLevel = _cursor.getString(_cursorIndexOfCefrLevel);
            final int _tmpCurrentStreak;
            _tmpCurrentStreak = _cursor.getInt(_cursorIndexOfCurrentStreak);
            final int _tmpLongestStreak;
            _tmpLongestStreak = _cursor.getInt(_cursorIndexOfLongestStreak);
            final long _tmpLastPlayedDate;
            _tmpLastPlayedDate = _cursor.getLong(_cursorIndexOfLastPlayedDate);
            final float _tmpListeningAccuracy;
            _tmpListeningAccuracy = _cursor.getFloat(_cursorIndexOfListeningAccuracy);
            final float _tmpSpeakingAccuracy;
            _tmpSpeakingAccuracy = _cursor.getFloat(_cursorIndexOfSpeakingAccuracy);
            final float _tmpReadingAccuracy;
            _tmpReadingAccuracy = _cursor.getFloat(_cursorIndexOfReadingAccuracy);
            final float _tmpWritingAccuracy;
            _tmpWritingAccuracy = _cursor.getFloat(_cursorIndexOfWritingAccuracy);
            final int _tmpWordsLearned;
            _tmpWordsLearned = _cursor.getInt(_cursorIndexOfWordsLearned);
            final int _tmpTotalWordsAttempted;
            _tmpTotalWordsAttempted = _cursor.getInt(_cursorIndexOfTotalWordsAttempted);
            final int _tmpDailyXPGoal;
            _tmpDailyXPGoal = _cursor.getInt(_cursorIndexOfDailyXPGoal);
            final int _tmpDailyXPToday;
            _tmpDailyXPToday = _cursor.getInt(_cursorIndexOfDailyXPToday);
            final int _tmpDailyWordsGoal;
            _tmpDailyWordsGoal = _cursor.getInt(_cursorIndexOfDailyWordsGoal);
            final int _tmpDailyWordsToday;
            _tmpDailyWordsToday = _cursor.getInt(_cursorIndexOfDailyWordsToday);
            final int _tmpDailyMinutesGoal;
            _tmpDailyMinutesGoal = _cursor.getInt(_cursorIndexOfDailyMinutesGoal);
            final int _tmpDailyMinutesToday;
            _tmpDailyMinutesToday = _cursor.getInt(_cursorIndexOfDailyMinutesToday);
            final int _tmpDifficultyLevel;
            _tmpDifficultyLevel = _cursor.getInt(_cursorIndexOfDifficultyLevel);
            final int _tmpRecentCorrect;
            _tmpRecentCorrect = _cursor.getInt(_cursorIndexOfRecentCorrect);
            final int _tmpRecentTotal;
            _tmpRecentTotal = _cursor.getInt(_cursorIndexOfRecentTotal);
            final boolean _tmpHasCompletedPlacement;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasCompletedPlacement);
            _tmpHasCompletedPlacement = _tmp != 0;
            final int _tmpWeeklyGamesPlayed;
            _tmpWeeklyGamesPlayed = _cursor.getInt(_cursorIndexOfWeeklyGamesPlayed);
            final int _tmpWeeklyListeningGames;
            _tmpWeeklyListeningGames = _cursor.getInt(_cursorIndexOfWeeklyListeningGames);
            final long _tmpWeekStartDate;
            _tmpWeekStartDate = _cursor.getLong(_cursorIndexOfWeekStartDate);
            _result = new UserProgress(_tmpId,_tmpTotalXP,_tmpLevel,_tmpCefrLevel,_tmpCurrentStreak,_tmpLongestStreak,_tmpLastPlayedDate,_tmpListeningAccuracy,_tmpSpeakingAccuracy,_tmpReadingAccuracy,_tmpWritingAccuracy,_tmpWordsLearned,_tmpTotalWordsAttempted,_tmpDailyXPGoal,_tmpDailyXPToday,_tmpDailyWordsGoal,_tmpDailyWordsToday,_tmpDailyMinutesGoal,_tmpDailyMinutesToday,_tmpDifficultyLevel,_tmpRecentCorrect,_tmpRecentTotal,_tmpHasCompletedPlacement,_tmpWeeklyGamesPlayed,_tmpWeeklyListeningGames,_tmpWeekStartDate);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<UserProgress> getProgress() {
    final String _sql = "SELECT * FROM user_progress WHERE id = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"user_progress"}, false, new Callable<UserProgress>() {
      @Override
      @Nullable
      public UserProgress call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTotalXP = CursorUtil.getColumnIndexOrThrow(_cursor, "totalXP");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfCefrLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "cefrLevel");
          final int _cursorIndexOfCurrentStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "currentStreak");
          final int _cursorIndexOfLongestStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "longestStreak");
          final int _cursorIndexOfLastPlayedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastPlayedDate");
          final int _cursorIndexOfListeningAccuracy = CursorUtil.getColumnIndexOrThrow(_cursor, "listeningAccuracy");
          final int _cursorIndexOfSpeakingAccuracy = CursorUtil.getColumnIndexOrThrow(_cursor, "speakingAccuracy");
          final int _cursorIndexOfReadingAccuracy = CursorUtil.getColumnIndexOrThrow(_cursor, "readingAccuracy");
          final int _cursorIndexOfWritingAccuracy = CursorUtil.getColumnIndexOrThrow(_cursor, "writingAccuracy");
          final int _cursorIndexOfWordsLearned = CursorUtil.getColumnIndexOrThrow(_cursor, "wordsLearned");
          final int _cursorIndexOfTotalWordsAttempted = CursorUtil.getColumnIndexOrThrow(_cursor, "totalWordsAttempted");
          final int _cursorIndexOfDailyXPGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyXPGoal");
          final int _cursorIndexOfDailyXPToday = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyXPToday");
          final int _cursorIndexOfDailyWordsGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyWordsGoal");
          final int _cursorIndexOfDailyWordsToday = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyWordsToday");
          final int _cursorIndexOfDailyMinutesGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyMinutesGoal");
          final int _cursorIndexOfDailyMinutesToday = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyMinutesToday");
          final int _cursorIndexOfDifficultyLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "difficultyLevel");
          final int _cursorIndexOfRecentCorrect = CursorUtil.getColumnIndexOrThrow(_cursor, "recentCorrect");
          final int _cursorIndexOfRecentTotal = CursorUtil.getColumnIndexOrThrow(_cursor, "recentTotal");
          final int _cursorIndexOfHasCompletedPlacement = CursorUtil.getColumnIndexOrThrow(_cursor, "hasCompletedPlacement");
          final int _cursorIndexOfWeeklyGamesPlayed = CursorUtil.getColumnIndexOrThrow(_cursor, "weeklyGamesPlayed");
          final int _cursorIndexOfWeeklyListeningGames = CursorUtil.getColumnIndexOrThrow(_cursor, "weeklyListeningGames");
          final int _cursorIndexOfWeekStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "weekStartDate");
          final UserProgress _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpTotalXP;
            _tmpTotalXP = _cursor.getInt(_cursorIndexOfTotalXP);
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpCefrLevel;
            _tmpCefrLevel = _cursor.getString(_cursorIndexOfCefrLevel);
            final int _tmpCurrentStreak;
            _tmpCurrentStreak = _cursor.getInt(_cursorIndexOfCurrentStreak);
            final int _tmpLongestStreak;
            _tmpLongestStreak = _cursor.getInt(_cursorIndexOfLongestStreak);
            final long _tmpLastPlayedDate;
            _tmpLastPlayedDate = _cursor.getLong(_cursorIndexOfLastPlayedDate);
            final float _tmpListeningAccuracy;
            _tmpListeningAccuracy = _cursor.getFloat(_cursorIndexOfListeningAccuracy);
            final float _tmpSpeakingAccuracy;
            _tmpSpeakingAccuracy = _cursor.getFloat(_cursorIndexOfSpeakingAccuracy);
            final float _tmpReadingAccuracy;
            _tmpReadingAccuracy = _cursor.getFloat(_cursorIndexOfReadingAccuracy);
            final float _tmpWritingAccuracy;
            _tmpWritingAccuracy = _cursor.getFloat(_cursorIndexOfWritingAccuracy);
            final int _tmpWordsLearned;
            _tmpWordsLearned = _cursor.getInt(_cursorIndexOfWordsLearned);
            final int _tmpTotalWordsAttempted;
            _tmpTotalWordsAttempted = _cursor.getInt(_cursorIndexOfTotalWordsAttempted);
            final int _tmpDailyXPGoal;
            _tmpDailyXPGoal = _cursor.getInt(_cursorIndexOfDailyXPGoal);
            final int _tmpDailyXPToday;
            _tmpDailyXPToday = _cursor.getInt(_cursorIndexOfDailyXPToday);
            final int _tmpDailyWordsGoal;
            _tmpDailyWordsGoal = _cursor.getInt(_cursorIndexOfDailyWordsGoal);
            final int _tmpDailyWordsToday;
            _tmpDailyWordsToday = _cursor.getInt(_cursorIndexOfDailyWordsToday);
            final int _tmpDailyMinutesGoal;
            _tmpDailyMinutesGoal = _cursor.getInt(_cursorIndexOfDailyMinutesGoal);
            final int _tmpDailyMinutesToday;
            _tmpDailyMinutesToday = _cursor.getInt(_cursorIndexOfDailyMinutesToday);
            final int _tmpDifficultyLevel;
            _tmpDifficultyLevel = _cursor.getInt(_cursorIndexOfDifficultyLevel);
            final int _tmpRecentCorrect;
            _tmpRecentCorrect = _cursor.getInt(_cursorIndexOfRecentCorrect);
            final int _tmpRecentTotal;
            _tmpRecentTotal = _cursor.getInt(_cursorIndexOfRecentTotal);
            final boolean _tmpHasCompletedPlacement;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasCompletedPlacement);
            _tmpHasCompletedPlacement = _tmp != 0;
            final int _tmpWeeklyGamesPlayed;
            _tmpWeeklyGamesPlayed = _cursor.getInt(_cursorIndexOfWeeklyGamesPlayed);
            final int _tmpWeeklyListeningGames;
            _tmpWeeklyListeningGames = _cursor.getInt(_cursorIndexOfWeeklyListeningGames);
            final long _tmpWeekStartDate;
            _tmpWeekStartDate = _cursor.getLong(_cursorIndexOfWeekStartDate);
            _result = new UserProgress(_tmpId,_tmpTotalXP,_tmpLevel,_tmpCefrLevel,_tmpCurrentStreak,_tmpLongestStreak,_tmpLastPlayedDate,_tmpListeningAccuracy,_tmpSpeakingAccuracy,_tmpReadingAccuracy,_tmpWritingAccuracy,_tmpWordsLearned,_tmpTotalWordsAttempted,_tmpDailyXPGoal,_tmpDailyXPToday,_tmpDailyWordsGoal,_tmpDailyWordsToday,_tmpDailyMinutesGoal,_tmpDailyMinutesToday,_tmpDifficultyLevel,_tmpRecentCorrect,_tmpRecentTotal,_tmpHasCompletedPlacement,_tmpWeeklyGamesPlayed,_tmpWeeklyListeningGames,_tmpWeekStartDate);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
