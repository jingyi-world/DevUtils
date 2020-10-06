package afkt.project.database.room.module.note.bean;

import androidx.room.TypeConverter;

/**
 * detail: Note 类型转换存储实现
 * @author Ttt
 */
public class NoteTypeConverter {

    @TypeConverter
    public NoteType convertToEntityProperty(String databaseValue) {
        return NoteType.valueOf(databaseValue);
    }

    @TypeConverter
    public String convertToDatabaseValue(NoteType entityProperty) {
        return entityProperty.name();
    }
}