package blcs.lwb.utils.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * 通过@Entity 注解 建立一个表
 */
@Entity
public class Student{
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo
    String name;
    @ColumnInfo
    String sex;
    @ColumnInfo
    int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
