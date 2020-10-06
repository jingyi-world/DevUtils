package afkt.project.database.room.module.note.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "NoteTable"
//        primaryKeys = {"id"}, // 主键
//        indices = { // 索引
//                @Index(value = "id", unique = true)
//        }
)
public class Note {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "text")
    private String text;

    @ColumnInfo(name = "comment")
    private String comment;

    private Long date;

    @ColumnInfo(name = "type")
    private NoteType type; // Note 类型

    @Ignore
    public Note() {
    }

    public Note(Long id, String text, String comment, NoteType type) {
        this.id = id;
        this.text = text;
        this.comment = comment;
        this.type = type;
    }

    @Ignore
    public Note(Long id, String text, String comment, Long date, NoteType type) {
        this.id = id;
        this.text = text;
        this.comment = comment;
        this.date = date;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public NoteType getType() {
        return type;
    }

    public void setType(NoteType type) {
        this.type = type;
    }
}