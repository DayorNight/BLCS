package blcs.lwb.utils.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import org.litepal.LitePal;
import org.litepal.crud.callback.SaveCallback;
import org.litepal.crud.callback.UpdateOrDeleteCallback;
import java.util.ArrayList;
import java.util.List;

import blcs.lwb.utils.bean.SqliteDemo;
import blcs.lwb.utils.adapter.SQLiteShowAdapter;

public class LinSQL {
    private static MySQLiteHelper blcs;

    private static String Table="SqliteDemo";

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
    public static void insert(String name, String address, int type, SQLiteShowAdapter mAdapter) {
        if (TextUtils.isEmpty(name) && TextUtils.isEmpty(address)) return;
        if (type == 0) {
            String insert = new StringBuilder()
                    .append("insert into SqliteDemo (name,address) values ('")
                    .append(name).append("','").append(address).append("')")
                    .toString();
            getDb().execSQL(insert);
        } else if (type == 1) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("address", address);
            getDb().insert(Table, null, contentValues);
        } else if (type == 2){
            new SqliteDemo(name,address).saveAsync().listen(new SaveCallback() {
                @Override
                public void onFinish(boolean success) {
                    List<SqliteDemo> all = LitePal.findAll(SqliteDemo.class);
                    mAdapter.setNewData(all);
                }
            });
        }
    }

    /**
     * 删除
     */
    public static void delete(String Name, String Address, int type, SQLiteShowAdapter mAdapter) {
        if (TextUtils.isEmpty(Name) && TextUtils.isEmpty(Address)) return;
        if (type == 0) {
            String delete;
            if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(Address)) {
                delete = new StringBuilder().append("delete from SqliteDemo where name = '").append(Name)
                        .append("' or address = '").append(Address).append("'")
                        .toString();
            } else {
                delete = new StringBuilder().append("delete from SqliteDemo where name = '").append(Name)
                        .append("' and address = '").append(Address).append("'")
                        .toString();
            }
            getDb().execSQL(delete);
        } else if (type == 1) {
            if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(Address)) {
                getDb().delete(Table, "name = ? or address = ?", new String[]{Name, Address});
            } else {
                getDb().delete(Table, "name = ? and address = ?", new String[]{Name, Address});
            }
        } else if (type == 2) {
            if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(Address)) {
                LitePal.deleteAllAsync(SqliteDemo.class,"name = ? or address = ?",Name,Address).listen(new UpdateOrDeleteCallback() {
                    @Override
                    public void onFinish(int rowsAffected) {
                        List<SqliteDemo> all = LitePal.findAll(SqliteDemo.class);
                        mAdapter.setNewData(all);
                    }
                });
            } else {
                LitePal.deleteAllAsync(SqliteDemo.class,"name = ? and address = ?",Name,Address).listen(new UpdateOrDeleteCallback() {
                    @Override
                    public void onFinish(int rowsAffected) {
                        List<SqliteDemo> all = LitePal.findAll(SqliteDemo.class);
                        mAdapter.setNewData(all);
                    }
                });
            }
        }
    }

    /**
     * 修改
     */
    public static void update(int Id, String Name, String Address, int type, SQLiteShowAdapter mAdapter) {
        if (TextUtils.isEmpty(Name) && TextUtils.isEmpty(Address)) return;
        if (type == 0) {
            String update = new StringBuilder().append("update SqliteDemo set name = '").append(Name)
                    .append("' , address = '").append(Address)
                    .append("' where id = ").append(Id)
                    .toString();
            getDb().execSQL(update);
        } else if (type == 1) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", Name);
            contentValues.put("address", Address);
            getDb().update(Table, contentValues, "id = ?", new String[]{String.valueOf(Id)});
        } else if (type == 2) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", Name);
            contentValues.put("address", Address);
            LitePal.updateAsync(SqliteDemo.class,contentValues,Id).listen(new UpdateOrDeleteCallback() {
                @Override
                public void onFinish(int rowsAffected) {
                    List<SqliteDemo> all = LitePal.findAll(SqliteDemo.class);
                    mAdapter.setNewData(all);
                }
            });
        }
    }

    /**
     * 查询
     */
    public static List<SqliteDemo> query(String Name, String Address, int type) {
        String query = null;
        String selection = null;
        String[] str = null;
        if (TextUtils.isEmpty(Name) & TextUtils.isEmpty(Address)) {//查询全部
            query = "select * from SqliteDemo";
            selection = null;
            str = null;
        } else if (TextUtils.isEmpty(Name)) {//根据地址查询
            query = new StringBuilder().append("select * from SqliteDemo where address = '")
                    .append(Address).append("'").toString();
            selection = "address = ?";
            str = new String[]{Address};
        } else if (TextUtils.isEmpty(Address)) {//根据名字查询
            query = new StringBuilder().append("select * from SqliteDemo where name = '")
                    .append(Name).append("'").toString();
            selection = "name = ?";
            str = new String[]{Name};
        } else {//根据名字和地址查询
            query = new StringBuilder().append("select * from SqliteDemo where name = '")
                    .append(Name).append("' and address = '").append(Address).append("'").toString();
            selection = "name = ? and address = ?";
            str = new String[]{Name, Address};
        }

        Cursor cursor = null;
        if (type == 0) {
            cursor = getDb().rawQuery(query, null);
        } else if (type == 1) {
            cursor = getDb().query(Table, null, selection, str, null, null, null);
        } else if (type == 2){
            List<SqliteDemo> allAsync;
            if(str==null){
                allAsync= LitePal.findAll(SqliteDemo.class);
            }else if(str.length ==1){
                allAsync = LitePal.where(selection, str[0]).find(SqliteDemo.class);
            }else{
                allAsync= LitePal.where(selection, Name, Address).find(SqliteDemo.class);
            }
            return allAsync;
        }

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
