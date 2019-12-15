package blcs.lwb.utils.Db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import blcs.lwb.utils.bean.Student;

/**
 * 创建数据库
 * 通过entities 指定数据库中的表
 * version指定当前数据库版本号
 */
@Database(entities = {Student.class},version = 1)
public abstract class RoomDbManager extends RoomDatabase {
    public abstract StudentDao getStudentDao();
}
