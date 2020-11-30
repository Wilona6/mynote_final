package com.example.mynote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotePadDB extends SQLiteOpenHelper {
    public final static String NAME = "notes";  //数据库名字
    public final static String TITLE = "title"; //记事本主题
    public final static String CONTENT = "content"; //内容
    public final static String ID = "_id"; //id
    public final static String TIME = "time"; //时间
    public final static String AUTHOR = "author"; //作者


    public NotePadDB(Context context) {
        super(context, NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + NAME + "("
                        + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + TITLE + " TITLE,"
                        + AUTHOR + " AUTHOR,"
                        + CONTENT + " CONTENT,"
                        + TIME + " TIME"
                        + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
