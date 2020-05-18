package com.vickx.obnlite.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String key = "id";
    public static final String sujet ="sujet";
    public static final String verbe = "verbe";
    public static final String complement = "complement";
    public static final String time = "time";
    public static final String tale_name = "ObnLite";

    public static final String table_create =
            "CREATE TABLE " + tale_name + " (" +
                    key + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    sujet + " TEXT, "+
                    verbe + " TEXT, " +
                    complement + " TEXT," +
                    time + " TEXT" +
                    ");";
    public static final String table_drop = "DROP TABLE IF EXISTS " + tale_name + ";";

    public static final String table_truncate = "TRUNCATE TABLE IF EXISTS" + tale_name + ";";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(table_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(table_drop);
        onCreate(db);
    }

    public void truncateTable(SQLiteDatabase db){
        db.execSQL(table_drop);
    }
}
