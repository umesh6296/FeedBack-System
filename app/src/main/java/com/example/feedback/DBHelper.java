package com.example.feedback;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="feedback.db";
    public static final String TBL_USER = "User";
    Context context;
    public static final String TBL_FEEDBACK = "FEEDBACK";
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TBL_USER+"(Email int,Name Text not null,Password Text not null,Role Text not null,primary key(Email))");

        db.execSQL("CREATE TABLE if not exists " + TBL_FEEDBACK + "(Email Text,Course Text not null,Suggestion text not null,Rating real not null) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+TBL_USER);
        db.execSQL("Drop table if exists "+TBL_FEEDBACK);
        onCreate(db);
    }
    public Context getContext() {
        return context;
    }
}
