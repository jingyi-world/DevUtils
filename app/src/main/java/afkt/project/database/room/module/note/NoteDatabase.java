package afkt.project.database.room.module.note;

import android.text.TextUtils;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import afkt.project.database.room.able.AbsRoomDatabase;
import afkt.project.database.room.module.note.bean.Note;
import afkt.project.database.room.module.note.dao.NoteDao;
import dev.DevUtils;

/**
 * detail: Room 数据库创建
 * @author Ttt
 * <pre>
 *     exportSchema = true 导出 JSON 文件
 * </pre>
 */
@Database(entities = {Note.class,}, version = 1) // , exportSchema = false
public abstract class NoteDatabase extends AbsRoomDatabase {

    /**
     * 获取 {@link Note} Dao
     * @return {@link NoteDao}
     */
    public abstract NoteDao getNoteDao();

    // ============
    // = database =
    // ============

    // 日志 TAG
    public static final String TAG = NoteDatabase.class.getSimpleName();

    /**
     * 创建数据库
     * @param dbName 数据库名
     * @return {@link RoomDatabase}
     */
    public static NoteDatabase database(final String dbName) {
        return database(dbName, null);
    }

    /**
     * 创建数据库
     * @param dbName   数据库名
     * @param password 数据库解密密码
     * @return {@link RoomDatabase}
     */
    public static NoteDatabase database(final String dbName, final String password) {
        if (TextUtils.isEmpty(dbName)) return null;

        NoteDatabase database = Room.databaseBuilder(
                DevUtils.getContext(), NoteDatabase.class, dbName
        ).build();
        return database;
    }
}