package blcs.lwb.utils.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.utils.bean.SqliteDemo;

public class LinSQL {
    private static MySQLiteHelper blcs;

    public static void init(Context context) {
        if (blcs == null) {
            blcs = new MySQLiteHelper(context, "Blcs", null, 1);
            blcs.getWritableDatabase();
        }
    }

    public static SQLiteDatabase getDb() {
        return blcs.getWritableDatabase();
    }

    /**
     * 插入语句
     */
    public static void insert(String Name, String address,int type) {
        if (TextUtils.isEmpty(Name) && TextUtils.isEmpty(address))  return;

        if(type == 0){
            String insert = new StringBuilder()
                    .append("insert into test (name,address) values ('")
                    .append(Name).append("','").append(address).append("')")
                    .toString();
            getDb().execSQL(insert);
        }else if(type == 1){
            ContentValues contentValues = new ContentValues();
            contentValues.put("name",Name);
            contentValues.put("address",address);
            getDb().insert("test",null,contentValues);
        }
    }

    /**
     * 删除
     */
    public static void delete(String Name, String Address,int type) {
        if (TextUtils.isEmpty(Name) && TextUtils.isEmpty(Address)) return;

        if(type == 0){
            String delete;
            if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(Address)) {
                delete = new StringBuilder().append("delete from test where name = '").append(Name)
                        .append("' or address = '").append(Address).append("'")
                        .toString();
            } else {
                delete = new StringBuilder().append("delete from test where name = '").append(Name)
                        .append("' and address = '").append(Address).append("'")
                        .toString();
            }
            getDb().execSQL(delete);
        }else if (type == 1){
            if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(Address)) {
                getDb().delete("test", "name = ? or address = ?", new String[]{Name,Address});
            } else {
                getDb().delete("test", "name = ? and address = ?", new String[]{Name,Address});
            }
        }
    }

    /**
     * 修改
     */
    public static void update(long Id, String Name, String Address,int type) {
        if (TextUtils.isEmpty(Name) && TextUtils.isEmpty(Address))  return;

        if(type == 0){
            String update = new StringBuilder().append("update test set name = '").append(Name)
                    .append("' , address = '").append(Address)
                    .append("' where id = ").append(Id)
                    .toString();
            getDb().execSQL(update);
        }else if(type == 1){
            ContentValues contentValues = new ContentValues();
            contentValues.put("name",Name);
            contentValues.put("address",Address);
            getDb().update("test", contentValues, "where id = ? ", new String[]{String.valueOf(Id)});
        }
    }

    /**
     * 查询
     */
    public static List<SqliteDemo> query(String Name, String Address,int type) {
        String query = null;
        if (TextUtils.isEmpty(Name) & TextUtils.isEmpty(Address)) {
            query = "select * from test";
        } else if (TextUtils.isEmpty(Name)) {
            query = new StringBuilder().append("select * from test where address = '")
                    .append(Address).append("'").toString();
        } else if (TextUtils.isEmpty(Address)) {
            query = new StringBuilder().append("select * from test where name = '")
                    .append(Name).append("'").toString();
        } else {
            query = new StringBuilder().append("select * from test where name = '")
                    .append(Name).append("' and address = '").append(Address).append("'").toString();
        }

        Cursor cursor = null;
        if(type == 0){
            cursor = getDb().rawQuery(query, null);
        }else if(type == 0){
            cursor = getDb().query("test", null,null,null,null,null,null);
        }

        LogUtils.e("cursor " + cursor.isFirst());
        ArrayList<SqliteDemo> dats = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            dats.add(new SqliteDemo(id, name, address));
        }
        return dats;
    }
}
