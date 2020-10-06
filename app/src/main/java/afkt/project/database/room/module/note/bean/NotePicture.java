package afkt.project.database.room.module.note.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * detail: Note 图片资源
 * @author Ttt
 */
@Entity
public class NotePicture {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "picture")
    private String picture;

    @ColumnInfo(name = "noteId")
    private Long noteId; // 对应的 note id ( 外键 )

    @Ignore
    public NotePicture() {
    }

    public NotePicture(Long id, String picture, Long noteId) {
        this.id = id;
        this.picture = picture;
        this.noteId = noteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }
}