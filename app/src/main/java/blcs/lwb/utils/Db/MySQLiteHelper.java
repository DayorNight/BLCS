package blcs.lwb.utils.Db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String TAG = "MySQLiteHelper";
    public static final String sql = "create table SqliteDemo (id integer primary key autoincrement, name text(4),address text(5))";
    public static final String sql1 = "create table test1 (id integer primary key autoincrement, name text(4),address text(5))";
    public MySQLiteHelper(@Nullable Context context, @Nullable String name,  @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    /**
     * 创建表
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCreate: " );
        db.execSQL(sql);
    }
    /**
     * 版本更新的时候调用
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "onUpgrade: " );
        switch (oldVersion){
            case 1:
                db.execSQL(sql1);
                break;
        }
    }
}
