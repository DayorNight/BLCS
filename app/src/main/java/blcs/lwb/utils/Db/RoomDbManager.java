package blcs.lwb.utils.Db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import blcs.lwb.utils.R;
import blcs.lwb.utils.bean.Student;
import blcs.lwb.utils.utils.MyUtils;

/**
 * 创建数据库
 * 通过entities 指定数据库中的表
 * version指定当前数据库版本号
 */
@Database(entities = {Student.class}, version = 1)
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
