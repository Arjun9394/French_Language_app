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
import com.frenchquest.data.models.Badge;
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
public final class BadgeDao_Impl implements BadgeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Badge> __insertionAdapterOfBadge;

  private final EntityDeletionOrUpdateAdapter<Badge> __updateAdapterOfBadge;

  public BadgeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBadge = new EntityInsertionAdapter<Badge>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `badges` (`id`,`title`,`description`,`iconResName`,`isUnlocked`,`unlockedAt`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Badge entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getDescription());
        statement.bindString(4, entity.getIconResName());
        final int _tmp = entity.isUnlocked() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getUnlockedAt());
      }
    };
    this.__updateAdapterOfBadge = new EntityDeletionOrUpdateAdapter<Badge>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `badges` SET `id` = ?,`title` = ?,`description` = ?,`iconResName` = ?,`isUnlocked` = ?,`unlockedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Badge entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getDescription());
        statement.bindString(4, entity.getIconResName());
        final int _tmp = entity.isUnlocked() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getUnlockedAt());
        statement.bindString(7, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final Badge badge, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBadge.insert(badge);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<Badge> badges, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBadge.insert(badges);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final Badge badge, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfBadge.handle(badge);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Badge>> getAllBadges() {
    final String _sql = "SELECT * FROM badges ORDER BY isUnlocked DESC, id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"badges"}, false, new Callable<List<Badge>>() {
      @Override
      @Nullable
      public List<Badge> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfIconResName = CursorUtil.getColumnIndexOrThrow(_cursor, "iconResName");
          final int _cursorIndexOfIsUnlocked = CursorUtil.getColumnIndexOrThrow(_cursor, "isUnlocked");
          final int _cursorIndexOfUnlockedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "unlockedAt");
          final List<Badge> _result = new ArrayList<Badge>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Badge _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpIconResName;
            _tmpIconResName = _cursor.getString(_cursorIndexOfIconResName);
            final boolean _tmpIsUnlocked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsUnlocked);
            _tmpIsUnlocked = _tmp != 0;
            final long _tmpUnlockedAt;
            _tmpUnlockedAt = _cursor.getLong(_cursorIndexOfUnlockedAt);
            _item = new Badge(_tmpId,_tmpTitle,_tmpDescription,_tmpIconResName,_tmpIsUnlocked,_tmpUnlockedAt);
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
  public Object getBadgeSync(final String badgeId, final Continuation<? super Badge> $completion) {
    final String _sql = "SELECT * FROM badges WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, badgeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Badge>() {
      @Override
      @Nullable
      public Badge call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfIconResName = CursorUtil.getColumnIndexOrThrow(_cursor, "iconResName");
          final int _cursorIndexOfIsUnlocked = CursorUtil.getColumnIndexOrThrow(_cursor, "isUnlocked");
          final int _cursorIndexOfUnlockedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "unlockedAt");
          final Badge _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpIconResName;
            _tmpIconResName = _cursor.getString(_cursorIndexOfIconResName);
            final boolean _tmpIsUnlocked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsUnlocked);
            _tmpIsUnlocked = _tmp != 0;
            final long _tmpUnlockedAt;
            _tmpUnlockedAt = _cursor.getLong(_cursorIndexOfUnlockedAt);
            _result = new Badge(_tmpId,_tmpTitle,_tmpDescription,_tmpIconResName,_tmpIsUnlocked,_tmpUnlockedAt);
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
  public Object getUnlockedCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM badges WHERE isUnlocked = 1";
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
