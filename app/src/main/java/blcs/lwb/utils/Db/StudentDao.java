package blcs.lwb.utils.Db;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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
