package com.example.userInterface;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String NAME = "history.db";
    public static final String TABLE_NAME1 = "history";
    public static final String TABLE_NAME2 = "count";
    public static final String COLUMN1_1 = "date";
    public static final String COLUMN1_2 = "challengeName";
    public static final String COLUMN2_1 = "challengeName";
    public static final String COLUMN2_2 = "count";
    public static int VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME1 + "(" +
                COLUMN1_1 + " integer," +
                COLUMN1_2 + " varchar(40))");

        db.execSQL("create table " + TABLE_NAME2 + "(" +
                COLUMN2_1 + " varchar(40)," +
                COLUMN2_2 + " integer");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
