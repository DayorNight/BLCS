package blcs.lwb.utils.Db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import blcs.lwb.utils.bean.Student;

/**
 * 创建数据库
 * 通过entities 指定数据库中的表
 * version指定当前数据库版本号
 */
@Database(entities = {Student.class}, version = 1,exportSchema = false)
public abstract class RoomDbManager extends RoomDatabase {
    public abstract StudentDao getStudentDao();

    public static RoomDbManager roomDb;

    public static RoomDbManager build(Context context) {
        if (roomDb == null) {
            synchronized (RoomDbManager.class) {
                if (roomDb == null) {
                    roomDb = Room.databaseBuilder(context,
                            RoomDbManager.class, "room_blcs").build();
                }
            }
        }
        return roomDb;
    }
}
