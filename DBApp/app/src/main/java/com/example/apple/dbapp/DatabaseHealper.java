package com.example.apple.dbapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.R.attr.publicKey;
import static android.R.attr.version;


public class DatabaseHealper extends SQLiteOpenHelper {
    private static final String TAG="DatabaseHealper";
    private static final String TABLE_NAME = "employees";
    private static final String COL1="name";

    public DatabaseHealper(Context context){ //}, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,"+COL1+" TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean addData(String item){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL1,item);
        Log.d(TAG,"addData: Adding "+item+" to "+TABLE_NAME);
        Long result=db.insert(TABLE_NAME,null,contentValues);
        if(result == -1) {
            return false;
        }else{
            return true;
        }
    }
    public Cursor getData(){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME;
        Cursor data=db.rawQuery(query,null);
        return data;
    }

}

