package com.example.intermediatequizapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context) {
        super(context,"MyDatabase",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable="create table myTable(xValues INTEGER,yValues INTEGER);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    public void insertData(long valX, int valY)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("xValues",valX);
        contentValues.put("yValues",valY);

        /*long result=*/db.insert("myTable",null,contentValues);
        /*if(result== -1) {
            return false;
        }
        else{
            return true;
        }*/
    }

}
