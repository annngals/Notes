package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    final static String DB_NAME = "data";
    final static String TABLE_NAME = "notes";
    SQLiteDatabase musicDB;
    final static String CREATE = "CREATE TABLE "+TABLE_NAME+ "( `_id` INTEGER PRIMARY KEY AUTOINCREMENT, `title` TEXT NOT NULL, `note` TEXT NOT NULL )";
    // при изменении структуры БД меняется и версия
    private static final int DATABASE_VERSION = 10;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // выполняется, если базы данных нет
        db.execSQL(CREATE);

        ContentValues cv = new ContentValues();
        db.insert(TABLE_NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // выполняется, если версия базы данных отличается
        db.execSQL("DROP DATABASE "+DB_NAME);
        this.onCreate(db);
    }
}