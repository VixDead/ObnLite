package com.vickx.obnlite.Models.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.vickx.obnlite.Models.DatabaseHandler;

public abstract class DAOBase<T> {
    private static int VERSION = 1;
    private final String fileName = "obnLite.db";
    protected SQLiteDatabase database = null;
    protected DatabaseHandler handler = null;

    DAOBase(Context context){
        this.handler = new DatabaseHandler(context,fileName,null,VERSION);
    }

    public SQLiteDatabase open(){
        this.database = handler.getWritableDatabase();
        return this.database;
    }

    public void close(){
        this.database.close();
    }

    public SQLiteDatabase getDatabase(){
        return this.database;
    }

}
