package qst.com.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2017/4/25.
 */

public class DBOpenHelper extends SQLiteOpenHelper{
    public static final String TABLE_NAME="personInfo";
    public static final String ID="ID";
    public static final String NAME="Name";
    public static final String PHONE_NUMBER="Phone_number";
    public static final String ADDRESS="Address";
    public static final String EMAIL="Email";
    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        db.execSQL("create table if not exists "+TABLE_NAME+"("+ID+" integer primary key  " +
                " autoincrement,"+NAME+" varchar,"+PHONE_NUMBER+" varchar,"+ADDRESS+" varchar,"+
        EMAIL+" varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
