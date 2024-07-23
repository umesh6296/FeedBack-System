package com.example.feedback;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

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

    public ArrayList<FeedbackEntity> getFeedbackForCourse(String course) {
        ArrayList<FeedbackEntity> feedbackList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT f.Email, f.Course, f.Suggestion, f.Rating, u.Name FROM " + TBL_FEEDBACK + " f " +
                "JOIN " + TBL_USER + " u ON f.Email = u.Email WHERE f.Course = ?";
        Cursor cursor = db.rawQuery(query, new String[]{course});

        if (cursor.moveToFirst()) {
            do {
                String email = cursor.getString(0);
                String courseName = cursor.getString(1);
                String suggestion = cursor.getString(2);
                float rating = cursor.getFloat(3);
                String studentName = cursor.getString(4);

                FeedbackEntity feedback = new FeedbackEntity(email, courseName, suggestion, rating);
                feedbackList.add(feedback);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return feedbackList;
    }

    public String getStudentNameByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TBL_USER, new String[]{"Name"}, "Email = ?", new String[]{email}, null, null, null);
        if (cursor.moveToFirst()) {
            String name = cursor.getString(0);
            cursor.close();
            return name;
        }
        cursor.close();
        return null;
    }

}
