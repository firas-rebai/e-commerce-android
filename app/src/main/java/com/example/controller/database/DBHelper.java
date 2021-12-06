package com.example.controller.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import model.user;

public class DBHelper extends SQLiteOpenHelper {
    //DATABASE NAME
    public static final String DATABASE_NAME = "books";
    //DATABASE VERSION
    public static final int DATABASE_VERSION = 1;
    private static final String SQL_TABLE_USERS = "CREATE TABLE user (" +
            "iduser INTEGER PRIMARY KEY," +
            "username TEXT," +
            "password TEXT," +
            "email TEXT," +
            "isadmin INTEGER)";
    private static final String SQL_TABLE_BOOK = "CREATE TABLE book (" +
            "idbook INTEGER PRIMARY KEY," +
            "title TEXT," +
            "author TEXT," +
            "genre TEXT," +
            "year TEXT," +
            "price REAL," +
            "description TEXT," +
            "quantity INTEGER," +
            "imageurl TEXT)";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_TABLE_USERS);
        db.execSQL(SQL_TABLE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS user");
        db.execSQL(" DROP TABLE IF EXISTS book");
    }

    public void Authenticate(user user){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("user",// Selecting Table
                new String[]{"iduser", "username", "email","password","isadmin" },
//Selecting columns want to query
                "email" + "=?",
                new String[]{user.getEmail()},//Where clause
                null, null, null);
    }

    public void addUser(user user){
    }

}
