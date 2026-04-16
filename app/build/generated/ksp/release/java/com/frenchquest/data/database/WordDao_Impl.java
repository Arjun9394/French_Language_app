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
import com.frenchquest.data.models.Word;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class WordDao_Impl implements WordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Word> __insertionAdapterOfWord;

  private final EntityDeletionOrUpdateAdapter<Word> __updateAdapterOfWord;

  public WordDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWord = new EntityInsertionAdapter<Word>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `words` (`id`,`french`,`english`,`phonetic`,`exampleSentence`,`exampleTranslation`,`imageResName`,`packId`,`cefrLevel`,`category`,`easeFactor`,`interval`,`repetitions`,`nextReviewDate`,`timesCorrect`,`timesWrong`,`isLearned`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Word entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getFrench());
        statement.bindString(3, entity.getEnglish());
        if (entity.getPhonetic() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPhonetic());
        }
        if (entity.getExampleSentence() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getExampleSentence());
        }
        if (entity.getExampleTranslation() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getExampleTranslation());
        }
        if (entity.getImageResName() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getImageResName());
        }
        statement.bindString(8, entity.getPackId());
        statement.bindString(9, entity.getCefrLevel());
        if (entity.getCategory() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getCategory());
        }
        statement.bindDouble(11, entity.getEaseFactor());
        statement.bindLong(12, entity.getInterval());
        statement.bindLong(13, entity.getRepetitions());
        statement.bindLong(14, entity.getNextReviewDate());
        statement.bindLong(15, entity.getTimesCorrect());
        statement.bindLong(16, entity.getTimesWrong());
        final int _tmp = entity.isLearned() ? 1 : 0;
        statement.bindLong(17, _tmp);
      }
    };
    this.__updateAdapterOfWord = new EntityDeletionOrUpdateAdapter<Word>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `words` SET `id` = ?,`french` = ?,`english` = ?,`phonetic` = ?,`exampleSentence` = ?,`exampleTranslation` = ?,`imageResName` = ?,`packId` = ?,`cefrLevel` = ?,`category` = ?,`easeFactor` = ?,`interval` = ?,`repetitions` = ?,`nextReviewDate` = ?,`timesCorrect` = ?,`timesWrong` = ?,`isLearned` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Word entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getFrench());
        statement.bindString(3, entity.getEnglish());
        if (entity.getPhonetic() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPhonetic());
        }
        if (entity.getExampleSentence() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getExampleSentence());
        }
        if (entity.getExampleTranslation() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getExampleTranslation());
        }
        if (entity.getImageResName() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getImageResName());
        }
        statement.bindString(8, entity.getPackId());
        statement.bindString(9, entity.getCefrLevel());
        if (entity.getCategory() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getCategory());
        }
        statement.bindDouble(11, entity.getEaseFactor());
        statement.bindLong(12, entity.getInterval());
        statement.bindLong(13, entity.getRepetitions());
        statement.bindLong(14, entity.getNextReviewDate());
        statement.bindLong(15, entity.getTimesCorrect());
        statement.bindLong(16, entity.getTimesWrong());
        final int _tmp = entity.isLearned() ? 1 : 0;
        statement.bindLong(17, _tmp);
        statement.bindLong(18, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final Word word, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWord.insert(word);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<Word> words, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWord.insert(words);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final Word word, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfWord.handle(word);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Word>> getWordsByPack(final String packId) {
    final String _sql = "SELECT * FROM words WHERE packId = ? ORDER BY id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, packId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"words"}, false, new Callable<List<Word>>() {
      @Override
      @Nullable
      public List<Word> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFrench = CursorUtil.getColumnIndexOrThrow(_cursor, "french");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfPhonetic = CursorUtil.getColumnIndexOrThrow(_cursor, "phonetic");
          final int _cursorIndexOfExampleSentence = CursorUtil.getColumnIndexOrThrow(_cursor, "exampleSentence");
          final int _cursorIndexOfExampleTranslation = CursorUtil.getColumnIndexOrThrow(_cursor, "exampleTranslation");
          final int _cursorIndexOfImageResName = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResName");
          final int _cursorIndexOfPackId = CursorUtil.getColumnIndexOrThrow(_cursor, "packId");
          final int _cursorIndexOfCefrLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "cefrLevel");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfRepetitions = CursorUtil.getColumnIndexOrThrow(_cursor, "repetitions");
          final int _cursorIndexOfNextReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewDate");
          final int _cursorIndexOfTimesCorrect = CursorUtil.getColumnIndexOrThrow(_cursor, "timesCorrect");
          final int _cursorIndexOfTimesWrong = CursorUtil.getColumnIndexOrThrow(_cursor, "timesWrong");
          final int _cursorIndexOfIsLearned = CursorUtil.getColumnIndexOrThrow(_cursor, "isLearned");
          final List<Word> _result = new ArrayList<Word>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Word _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpFrench;
            _tmpFrench = _cursor.getString(_cursorIndexOfFrench);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpPhonetic;
            if (_cursor.isNull(_cursorIndexOfPhonetic)) {
              _tmpPhonetic = null;
            } else {
              _tmpPhonetic = _cursor.getString(_cursorIndexOfPhonetic);
            }
            final String _tmpExampleSentence;
            if (_cursor.isNull(_cursorIndexOfExampleSentence)) {
              _tmpExampleSentence = null;
            } else {
              _tmpExampleSentence = _cursor.getString(_cursorIndexOfExampleSentence);
            }
            final String _tmpExampleTranslation;
            if (_cursor.isNull(_cursorIndexOfExampleTranslation)) {
              _tmpExampleTranslation = null;
            } else {
              _tmpExampleTranslation = _cursor.getString(_cursorIndexOfExampleTranslation);
            }
            final String _tmpImageResName;
            if (_cursor.isNull(_cursorIndexOfImageResName)) {
              _tmpImageResName = null;
            } else {
              _tmpImageResName = _cursor.getString(_cursorIndexOfImageResName);
            }
            final String _tmpPackId;
            _tmpPackId = _cursor.getString(_cursorIndexOfPackId);
            final String _tmpCefrLevel;
            _tmpCefrLevel = _cursor.getString(_cursorIndexOfCefrLevel);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final float _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getFloat(_cursorIndexOfEaseFactor);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final int _tmpRepetitions;
            _tmpRepetitions = _cursor.getInt(_cursorIndexOfRepetitions);
            final long _tmpNextReviewDate;
            _tmpNextReviewDate = _cursor.getLong(_cursorIndexOfNextReviewDate);
            final int _tmpTimesCorrect;
            _tmpTimesCorrect = _cursor.getInt(_cursorIndexOfTimesCorrect);
            final int _tmpTimesWrong;
            _tmpTimesWrong = _cursor.getInt(_cursorIndexOfTimesWrong);
            final boolean _tmpIsLearned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsLearned);
            _tmpIsLearned = _tmp != 0;
            _item = new Word(_tmpId,_tmpFrench,_tmpEnglish,_tmpPhonetic,_tmpExampleSentence,_tmpExampleTranslation,_tmpImageResName,_tmpPackId,_tmpCefrLevel,_tmpCategory,_tmpEaseFactor,_tmpInterval,_tmpRepetitions,_tmpNextReviewDate,_tmpTimesCorrect,_tmpTimesWrong,_tmpIsLearned);
            _result.add(_item);
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

  @Override
  public Object getWordsByPackSync(final String packId,
      final Continuation<? super List<Word>> $completion) {
    final String _sql = "SELECT * FROM words WHERE packId = ? ORDER BY id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, packId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Word>>() {
      @Override
      @NonNull
      public List<Word> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFrench = CursorUtil.getColumnIndexOrThrow(_cursor, "french");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfPhonetic = CursorUtil.getColumnIndexOrThrow(_cursor, "phonetic");
          final int _cursorIndexOfExampleSentence = CursorUtil.getColumnIndexOrThrow(_cursor, "exampleSentence");
          final int _cursorIndexOfExampleTranslation = CursorUtil.getColumnIndexOrThrow(_cursor, "exampleTranslation");
          final int _cursorIndexOfImageResName = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResName");
          final int _cursorIndexOfPackId = CursorUtil.getColumnIndexOrThrow(_cursor, "packId");
          final int _cursorIndexOfCefrLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "cefrLevel");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfRepetitions = CursorUtil.getColumnIndexOrThrow(_cursor, "repetitions");
          final int _cursorIndexOfNextReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewDate");
          final int _cursorIndexOfTimesCorrect = CursorUtil.getColumnIndexOrThrow(_cursor, "timesCorrect");
          final int _cursorIndexOfTimesWrong = CursorUtil.getColumnIndexOrThrow(_cursor, "timesWrong");
          final int _cursorIndexOfIsLearned = CursorUtil.getColumnIndexOrThrow(_cursor, "isLearned");
          final List<Word> _result = new ArrayList<Word>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Word _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpFrench;
            _tmpFrench = _cursor.getString(_cursorIndexOfFrench);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpPhonetic;
            if (_cursor.isNull(_cursorIndexOfPhonetic)) {
              _tmpPhonetic = null;
            } else {
              _tmpPhonetic = _cursor.getString(_cursorIndexOfPhonetic);
            }
            final String _tmpExampleSentence;
            if (_cursor.isNull(_cursorIndexOfExampleSentence)) {
              _tmpExampleSentence = null;
            } else {
              _tmpExampleSentence = _cursor.getString(_cursorIndexOfExampleSentence);
            }
            final String _tmpExampleTranslation;
            if (_cursor.isNull(_cursorIndexOfExampleTranslation)) {
              _tmpExampleTranslation = null;
            } else {
              _tmpExampleTranslation = _cursor.getString(_cursorIndexOfExampleTranslation);
            }
            final String _tmpImageResName;
            if (_cursor.isNull(_cursorIndexOfImageResName)) {
              _tmpImageResName = null;
            } else {
              _tmpImageResName = _cursor.getString(_cursorIndexOfImageResName);
            }
            final String _tmpPackId;
            _tmpPackId = _cursor.getString(_cursorIndexOfPackId);
            final String _tmpCefrLevel;
            _tmpCefrLevel = _cursor.getString(_cursorIndexOfCefrLevel);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final float _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getFloat(_cursorIndexOfEaseFactor);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final int _tmpRepetitions;
            _tmpRepetitions = _cursor.getInt(_cursorIndexOfRepetitions);
            final long _tmpNextReviewDate;
            _tmpNextReviewDate = _cursor.getLong(_cursorIndexOfNextReviewDate);
            final int _tmpTimesCorrect;
            _tmpTimesCorrect = _cursor.getInt(_cursorIndexOfTimesCorrect);
            final int _tmpTimesWrong;
            _tmpTimesWrong = _cursor.getInt(_cursorIndexOfTimesWrong);
            final boolean _tmpIsLearned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsLearned);
            _tmpIsLearned = _tmp != 0;
            _item = new Word(_tmpId,_tmpFrench,_tmpEnglish,_tmpPhonetic,_tmpExampleSentence,_tmpExampleTranslation,_tmpImageResName,_tmpPackId,_tmpCefrLevel,_tmpCategory,_tmpEaseFactor,_tmpInterval,_tmpRepetitions,_tmpNextReviewDate,_tmpTimesCorrect,_tmpTimesWrong,_tmpIsLearned);
            _result.add(_item);
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
  public Object getWordsByLevel(final String level,
      final Continuation<? super List<Word>> $completion) {
    final String _sql = "SELECT * FROM words WHERE cefrLevel = ? ORDER BY id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, level);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Word>>() {
      @Override
      @NonNull
      public List<Word> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFrench = CursorUtil.getColumnIndexOrThrow(_cursor, "french");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfPhonetic = CursorUtil.getColumnIndexOrThrow(_cursor, "phonetic");
          final int _cursorIndexOfExampleSentence = CursorUtil.getColumnIndexOrThrow(_cursor, "exampleSentence");
          final int _cursorIndexOfExampleTranslation = CursorUtil.getColumnIndexOrThrow(_cursor, "exampleTranslation");
          final int _cursorIndexOfImageResName = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResName");
          final int _cursorIndexOfPackId = CursorUtil.getColumnIndexOrThrow(_cursor, "packId");
          final int _cursorIndexOfCefrLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "cefrLevel");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfRepetitions = CursorUtil.getColumnIndexOrThrow(_cursor, "repetitions");
          final int _cursorIndexOfNextReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewDate");
          final int _cursorIndexOfTimesCorrect = CursorUtil.getColumnIndexOrThrow(_cursor, "timesCorrect");
          final int _cursorIndexOfTimesWrong = CursorUtil.getColumnIndexOrThrow(_cursor, "timesWrong");
          final int _cursorIndexOfIsLearned = CursorUtil.getColumnIndexOrThrow(_cursor, "isLearned");
          final List<Word> _result = new ArrayList<Word>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Word _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpFrench;
            _tmpFrench = _cursor.getString(_cursorIndexOfFrench);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpPhonetic;
            if (_cursor.isNull(_cursorIndexOfPhonetic)) {
              _tmpPhonetic = null;
            } else {
              _tmpPhonetic = _cursor.getString(_cursorIndexOfPhonetic);
            }
            final String _tmpExampleSentence;
            if (_cursor.isNull(_cursorIndexOfExampleSentence)) {
              _tmpExampleSentence = null;
            } else {
              _tmpExampleSentence = _cursor.getString(_cursorIndexOfExampleSentence);
            }
            final String _tmpExampleTranslation;
            if (_cursor.isNull(_cursorIndexOfExampleTranslation)) {
              _tmpExampleTranslation = null;
            } else {
              _tmpExampleTranslation = _cursor.getString(_cursorIndexOfExampleTranslation);
            }
            final String _tmpImageResName;
            if (_cursor.isNull(_cursorIndexOfImageResName)) {
              _tmpImageResName = null;
            } else {
              _tmpImageResName = _cursor.getString(_cursorIndexOfImageResName);
            }
            final String _tmpPackId;
            _tmpPackId = _cursor.getString(_cursorIndexOfPackId);
            final String _tmpCefrLevel;
            _tmpCefrLevel = _cursor.getString(_cursorIndexOfCefrLevel);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final float _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getFloat(_cursorIndexOfEaseFactor);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final int _tmpRepetitions;
            _tmpRepetitions = _cursor.getInt(_cursorIndexOfRepetitions);
            final long _tmpNextReviewDate;
            _tmpNextReviewDate = _cursor.getLong(_cursorIndexOfNextReviewDate);
            final int _tmpTimesCorrect;
            _tmpTimesCorrect = _cursor.getInt(_cursorIndexOfTimesCorrect);
            final int _tmpTimesWrong;
            _tmpTimesWrong = _cursor.getInt(_cursorIndexOfTimesWrong);
            final boolean _tmpIsLearned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsLearned);
            _tmpIsLearned = _tmp != 0;
            _item = new Word(_tmpId,_tmpFrench,_tmpEnglish,_tmpPhonetic,_tmpExampleSentence,_tmpExampleTranslation,_tmpImageResName,_tmpPackId,_tmpCefrLevel,_tmpCategory,_tmpEaseFactor,_tmpInterval,_tmpRepetitions,_tmpNextReviewDate,_tmpTimesCorrect,_tmpTimesWrong,_tmpIsLearned);
            _result.add(_item);
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
  public Object getWordsDueForReview(final long now, final int limit,
      final Continuation<? super List<Word>> $completion) {
    final String _sql = "SELECT * FROM words WHERE nextReviewDate <= ? ORDER BY nextReviewDate LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, now);
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Word>>() {
      @Override
      @NonNull
      public List<Word> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFrench = CursorUtil.getColumnIndexOrThrow(_cursor, "french");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfPhonetic = CursorUtil.getColumnIndexOrThrow(_cursor, "phonetic");
          final int _cursorIndexOfExampleSentence = CursorUtil.getColumnIndexOrThrow(_cursor, "exampleSentence");
          final int _cursorIndexOfExampleTranslation = CursorUtil.getColumnIndexOrThrow(_cursor, "exampleTranslation");
          final int _cursorIndexOfImageResName = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResName");
          final int _cursorIndexOfPackId = CursorUtil.getColumnIndexOrThrow(_cursor, "packId");
          final int _cursorIndexOfCefrLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "cefrLevel");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfRepetitions = CursorUtil.getColumnIndexOrThrow(_cursor, "repetitions");
          final int _cursorIndexOfNextReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewDate");
          final int _cursorIndexOfTimesCorrect = CursorUtil.getColumnIndexOrThrow(_cursor, "timesCorrect");
          final int _cursorIndexOfTimesWrong = CursorUtil.getColumnIndexOrThrow(_cursor, "timesWrong");
          final int _cursorIndexOfIsLearned = CursorUtil.getColumnIndexOrThrow(_cursor, "isLearned");
          final List<Word> _result = new ArrayList<Word>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Word _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpFrench;
            _tmpFrench = _cursor.getString(_cursorIndexOfFrench);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpPhonetic;
            if (_cursor.isNull(_cursorIndexOfPhonetic)) {
              _tmpPhonetic = null;
            } else {
              _tmpPhonetic = _cursor.getString(_cursorIndexOfPhonetic);
            }
            final String _tmpExampleSentence;
            if (_cursor.isNull(_cursorIndexOfExampleSentence)) {
              _tmpExampleSentence = null;
            } else {
              _tmpExampleSentence = _cursor.getString(_cursorIndexOfExampleSentence);
            }
            final String _tmpExampleTranslation;
            if (_cursor.isNull(_cursorIndexOfExampleTranslation)) {
              _tmpExampleTranslation = null;
            } else {
              _tmpExampleTranslation = _cursor.getString(_cursorIndexOfExampleTranslation);
            }
            final String _tmpImageResName;
            if (_cursor.isNull(_cursorIndexOfImageResName)) {
              _tmpImageResName = null;
            } else {
              _tmpImageResName = _cursor.getString(_cursorIndexOfImageResName);
            }
            final String _tmpPackId;
            _tmpPackId = _cursor.getString(_cursorIndexOfPackId);
            final String _tmpCefrLevel;
            _tmpCefrLevel = _cursor.getString(_cursorIndexOfCefrLevel);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final float _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getFloat(_cursorIndexOfEaseFactor);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final int _tmpRepetitions;
            _tmpRepetitions = _cursor.getInt(_cursorIndexOfRepetitions);
            final long _tmpNextReviewDate;
            _tmpNextReviewDate = _cursor.getLong(_cursorIndexOfNextReviewDate);
            final int _tmpTimesCorrect;
            _tmpTimesCorrect = _cursor.getInt(_cursorIndexOfTimesCorrect);
            final int _tmpTimesWrong;
            _tmpTimesWrong = _cursor.getInt(_cursorIndexOfTimesWrong);
            final boolean _tmpIsLearned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsLearned);
            _tmpIsLearned = _tmp != 0;
            _item = new Word(_tmpId,_tmpFrench,_tmpEnglish,_tmpPhonetic,_tmpExampleSentence,_tmpExampleTranslation,_tmpImageResName,_tmpPackId,_tmpCefrLevel,_tmpCategory,_tmpEaseFactor,_tmpInterval,_tmpRepetitions,_tmpNextReviewDate,_tmpTimesCorrect,_tmpTimesWrong,_tmpIsLearned);
            _result.add(_item);
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
  public Object getRandomWords(final String level, final int count,
      final Continuation<? super List<Word>> $completion) {
    final String _sql = "SELECT * FROM words WHERE cefrLevel = ? ORDER BY RANDOM() LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, level);
    _argIndex = 2;
    _statement.bindLong(_argIndex, count);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Word>>() {
      @Override
      @NonNull
      public List<Word> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFrench = CursorUtil.getColumnIndexOrThrow(_cursor, "french");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfPhonetic = CursorUtil.getColumnIndexOrThrow(_cursor, "phonetic");
          final int _cursorIndexOfExampleSentence = CursorUtil.getColumnIndexOrThrow(_cursor, "exampleSentence");
          final int _cursorIndexOfExampleTranslation = CursorUtil.getColumnIndexOrThrow(_cursor, "exampleTranslation");
          final int _cursorIndexOfImageResName = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResName");
          final int _cursorIndexOfPackId = CursorUtil.getColumnIndexOrThrow(_cursor, "packId");
          final int _cursorIndexOfCefrLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "cefrLevel");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfRepetitions = CursorUtil.getColumnIndexOrThrow(_cursor, "repetitions");
          final int _cursorIndexOfNextReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewDate");
          final int _cursorIndexOfTimesCorrect = CursorUtil.getColumnIndexOrThrow(_cursor, "timesCorrect");
          final int _cursorIndexOfTimesWrong = CursorUtil.getColumnIndexOrThrow(_cursor, "timesWrong");
          final int _cursorIndexOfIsLearned = CursorUtil.getColumnIndexOrThrow(_cursor, "isLearned");
          final List<Word> _result = new ArrayList<Word>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Word _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpFrench;
            _tmpFrench = _cursor.getString(_cursorIndexOfFrench);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpPhonetic;
            if (_cursor.isNull(_cursorIndexOfPhonetic)) {
              _tmpPhonetic = null;
            } else {
              _tmpPhonetic = _cursor.getString(_cursorIndexOfPhonetic);
            }
            final String _tmpExampleSentence;
            if (_cursor.isNull(_cursorIndexOfExampleSentence)) {
              _tmpExampleSentence = null;
            } else {
              _tmpExampleSentence = _cursor.getString(_cursorIndexOfExampleSentence);
            }
            final String _tmpExampleTranslation;
            if (_cursor.isNull(_cursorIndexOfExampleTranslation)) {
              _tmpExampleTranslation = null;
            } else {
              _tmpExampleTranslation = _cursor.getString(_cursorIndexOfExampleTranslation);
            }
            final String _tmpImageResName;
            if (_cursor.isNull(_cursorIndexOfImageResName)) {
              _tmpImageResName = null;
            } else {
              _tmpImageResName = _cursor.getString(_cursorIndexOfImageResName);
            }
            final String _tmpPackId;
            _tmpPackId = _cursor.getString(_cursorIndexOfPackId);
            final String _tmpCefrLevel;
            _tmpCefrLevel = _cursor.getString(_cursorIndexOfCefrLevel);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final float _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getFloat(_cursorIndexOfEaseFactor);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final int _tmpRepetitions;
            _tmpRepetitions = _cursor.getInt(_cursorIndexOfRepetitions);
            final long _tmpNextReviewDate;
            _tmpNextReviewDate = _cursor.getLong(_cursorIndexOfNextReviewDate);
            final int _tmpTimesCorrect;
            _tmpTimesCorrect = _cursor.getInt(_cursorIndexOfTimesCorrect);
            final int _tmpTimesWrong;
            _tmpTimesWrong = _cursor.getInt(_cursorIndexOfTimesWrong);
            final boolean _tmpIsLearned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsLearned);
            _tmpIsLearned = _tmp != 0;
            _item = new Word(_tmpId,_tmpFrench,_tmpEnglish,_tmpPhonetic,_tmpExampleSentence,_tmpExampleTranslation,_tmpImageResName,_tmpPackId,_tmpCefrLevel,_tmpCategory,_tmpEaseFactor,_tmpInterval,_tmpRepetitions,_tmpNextReviewDate,_tmpTimesCorrect,_tmpTimesWrong,_tmpIsLearned);
            _result.add(_item);
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
  public Object getRandomFromPack(final String packId, final int count,
      final Continuation<? super List<Word>> $completion) {
    final String _sql = "SELECT * FROM words WHERE packId = ? ORDER BY RANDOM() LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, packId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, count);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Word>>() {
      @Override
      @NonNull
      public List<Word> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFrench = CursorUtil.getColumnIndexOrThrow(_cursor, "french");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfPhonetic = CursorUtil.getColumnIndexOrThrow(_cursor, "phonetic");
          final int _cursorIndexOfExampleSentence = CursorUtil.getColumnIndexOrThrow(_cursor, "exampleSentence");
          final int _cursorIndexOfExampleTranslation = CursorUtil.getColumnIndexOrThrow(_cursor, "exampleTranslation");
          final int _cursorIndexOfImageResName = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResName");
          final int _cursorIndexOfPackId = CursorUtil.getColumnIndexOrThrow(_cursor, "packId");
          final int _cursorIndexOfCefrLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "cefrLevel");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfRepetitions = CursorUtil.getColumnIndexOrThrow(_cursor, "repetitions");
          final int _cursorIndexOfNextReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewDate");
          final int _cursorIndexOfTimesCorrect = CursorUtil.getColumnIndexOrThrow(_cursor, "timesCorrect");
          final int _cursorIndexOfTimesWrong = CursorUtil.getColumnIndexOrThrow(_cursor, "timesWrong");
          final int _cursorIndexOfIsLearned = CursorUtil.getColumnIndexOrThrow(_cursor, "isLearned");
          final List<Word> _result = new ArrayList<Word>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Word _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpFrench;
            _tmpFrench = _cursor.getString(_cursorIndexOfFrench);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpPhonetic;
            if (_cursor.isNull(_cursorIndexOfPhonetic)) {
              _tmpPhonetic = null;
            } else {
              _tmpPhonetic = _cursor.getString(_cursorIndexOfPhonetic);
            }
            final String _tmpExampleSentence;
            if (_cursor.isNull(_cursorIndexOfExampleSentence)) {
              _tmpExampleSentence = null;
            } else {
              _tmpExampleSentence = _cursor.getString(_cursorIndexOfExampleSentence);
            }
            final String _tmpExampleTranslation;
            if (_cursor.isNull(_cursorIndexOfExampleTranslation)) {
              _tmpExampleTranslation = null;
            } else {
              _tmpExampleTranslation = _cursor.getString(_cursorIndexOfExampleTranslation);
            }
            final String _tmpImageResName;
            if (_cursor.isNull(_cursorIndexOfImageResName)) {
              _tmpImageResName = null;
            } else {
              _tmpImageResName = _cursor.getString(_cursorIndexOfImageResName);
            }
            final String _tmpPackId;
            _tmpPackId = _cursor.getString(_cursorIndexOfPackId);
            final String _tmpCefrLevel;
            _tmpCefrLevel = _cursor.getString(_cursorIndexOfCefrLevel);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final float _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getFloat(_cursorIndexOfEaseFactor);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final int _tmpRepetitions;
            _tmpRepetitions = _cursor.getInt(_cursorIndexOfRepetitions);
            final long _tmpNextReviewDate;
            _tmpNextReviewDate = _cursor.getLong(_cursorIndexOfNextReviewDate);
            final int _tmpTimesCorrect;
            _tmpTimesCorrect = _cursor.getInt(_cursorIndexOfTimesCorrect);
            final int _tmpTimesWrong;
            _tmpTimesWrong = _cursor.getInt(_cursorIndexOfTimesWrong);
            final boolean _tmpIsLearned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsLearned);
            _tmpIsLearned = _tmp != 0;
            _item = new Word(_tmpId,_tmpFrench,_tmpEnglish,_tmpPhonetic,_tmpExampleSentence,_tmpExampleTranslation,_tmpImageResName,_tmpPackId,_tmpCefrLevel,_tmpCategory,_tmpEaseFactor,_tmpInterval,_tmpRepetitions,_tmpNextReviewDate,_tmpTimesCorrect,_tmpTimesWrong,_tmpIsLearned);
            _result.add(_item);
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
  public Object getUnlearnedWords(final String level, final int count,
      final Continuation<? super List<Word>> $completion) {
    final String _sql = "SELECT * FROM words WHERE isLearned = 0 AND cefrLevel = ? ORDER BY RANDOM() LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, level);
    _argIndex = 2;
    _statement.bindLong(_argIndex, count);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Word>>() {
      @Override
      @NonNull
      public List<Word> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFrench = CursorUtil.getColumnIndexOrThrow(_cursor, "french");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfPhonetic = CursorUtil.getColumnIndexOrThrow(_cursor, "phonetic");
          final int _cursorIndexOfExampleSentence = CursorUtil.getColumnIndexOrThrow(_cursor, "exampleSentence");
          final int _cursorIndexOfExampleTranslation = CursorUtil.getColumnIndexOrThrow(_cursor, "exampleTranslation");
          final int _cursorIndexOfImageResName = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResName");
          final int _cursorIndexOfPackId = CursorUtil.getColumnIndexOrThrow(_cursor, "packId");
          final int _cursorIndexOfCefrLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "cefrLevel");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfRepetitions = CursorUtil.getColumnIndexOrThrow(_cursor, "repetitions");
          final int _cursorIndexOfNextReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewDate");
          final int _cursorIndexOfTimesCorrect = CursorUtil.getColumnIndexOrThrow(_cursor, "timesCorrect");
          final int _cursorIndexOfTimesWrong = CursorUtil.getColumnIndexOrThrow(_cursor, "timesWrong");
          final int _cursorIndexOfIsLearned = CursorUtil.getColumnIndexOrThrow(_cursor, "isLearned");
          final List<Word> _result = new ArrayList<Word>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Word _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpFrench;
            _tmpFrench = _cursor.getString(_cursorIndexOfFrench);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpPhonetic;
            if (_cursor.isNull(_cursorIndexOfPhonetic)) {
              _tmpPhonetic = null;
            } else {
              _tmpPhonetic = _cursor.getString(_cursorIndexOfPhonetic);
            }
            final String _tmpExampleSentence;
            if (_cursor.isNull(_cursorIndexOfExampleSentence)) {
              _tmpExampleSentence = null;
            } else {
              _tmpExampleSentence = _cursor.getString(_cursorIndexOfExampleSentence);
            }
            final String _tmpExampleTranslation;
            if (_cursor.isNull(_cursorIndexOfExampleTranslation)) {
              _tmpExampleTranslation = null;
            } else {
              _tmpExampleTranslation = _cursor.getString(_cursorIndexOfExampleTranslation);
            }
            final String _tmpImageResName;
            if (_cursor.isNull(_cursorIndexOfImageResName)) {
              _tmpImageResName = null;
            } else {
              _tmpImageResName = _cursor.getString(_cursorIndexOfImageResName);
            }
            final String _tmpPackId;
            _tmpPackId = _cursor.getString(_cursorIndexOfPackId);
            final String _tmpCefrLevel;
            _tmpCefrLevel = _cursor.getString(_cursorIndexOfCefrLevel);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final float _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getFloat(_cursorIndexOfEaseFactor);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final int _tmpRepetitions;
            _tmpRepetitions = _cursor.getInt(_cursorIndexOfRepetitions);
            final long _tmpNextReviewDate;
            _tmpNextReviewDate = _cursor.getLong(_cursorIndexOfNextReviewDate);
            final int _tmpTimesCorrect;
            _tmpTimesCorrect = _cursor.getInt(_cursorIndexOfTimesCorrect);
            final int _tmpTimesWrong;
            _tmpTimesWrong = _cursor.getInt(_cursorIndexOfTimesWrong);
            final boolean _tmpIsLearned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsLearned);
            _tmpIsLearned = _tmp != 0;
            _item = new Word(_tmpId,_tmpFrench,_tmpEnglish,_tmpPhonetic,_tmpExampleSentence,_tmpExampleTranslation,_tmpImageResName,_tmpPackId,_tmpCefrLevel,_tmpCategory,_tmpEaseFactor,_tmpInterval,_tmpRepetitions,_tmpNextReviewDate,_tmpTimesCorrect,_tmpTimesWrong,_tmpIsLearned);
            _result.add(_item);
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
  public Object getLearnedCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM words WHERE isLearned = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
  public Object getLearnedCountForPack(final String packId,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM words WHERE packId = ? AND isLearned = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, packId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
  public Object getTotalCountForPack(final String packId,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM words WHERE packId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, packId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
  public Object getAllPackIds(final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT DISTINCT packId FROM words";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            _item = _cursor.getString(0);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
