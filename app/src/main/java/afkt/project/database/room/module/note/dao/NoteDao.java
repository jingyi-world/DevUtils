package afkt.project.database.room.module.note.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import afkt.project.database.room.module.note.bean.Note;

/**
 * detail: Room DAO 访问数据库方法
 * @author Ttt
 */
@Dao
public interface NoteDao {

    @Insert
    void insertAll(Note... notes);
}