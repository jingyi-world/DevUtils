package afkt.project.database.room.module.note;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import afkt.project.database.room.module.note.bean.Note;
import afkt.project.database.room.module.note.dao.NoteDao;

/**
 * detail: Room 数据库创建
 * @author Ttt
 * <pre>
 *     exportSchema = true 导出 JSON 文件
 * </pre>
 */
@Database(entities = {Note.class,}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    /**
     * 获取 {@link Note} Dao
     * @return {@link NoteDao}
     */
    public abstract NoteDao getNoteDao();
}