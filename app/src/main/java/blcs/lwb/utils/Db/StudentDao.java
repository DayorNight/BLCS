package blcs.lwb.utils.Db;

import android.arch.paging.DataSource;
import android.arch.paging.PagedList;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import blcs.lwb.utils.bean.Student;

/**
 * 创建访问数据库的方法
 */
@Dao
public interface StudentDao {

    @Insert
    void insertOne(Student student);

    @Delete
    void deleteOne(Student student);

    @Update
    void update(Student student);

    @Query("SELECT * FROM Student")
    List<Student> getAll();

    @Query("SELECT * FROM Student")
    DataSource.Factory<Integer, Student> getAllStudent();
}
