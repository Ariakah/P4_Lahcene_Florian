package com.example.mareu.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LocalStorageService extends SQLiteOpenHelper {


    public LocalStorageService(@Nullable Context context) {
        super(context, "meeting.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE meeting (_id INTEGER PRIMARY KEY AUTOINCREMENT,subject TEXT, " +
                "date TEXT, time TEXT, location TEXT, participants TEXT, couleur TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
