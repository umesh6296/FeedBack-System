package com.example.feedback;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class FeedbackDBHelper extends DBHelper{

    private static final String[] COL_NAMES={"Email","Course","Suggestion","Rating"};
    public FeedbackDBHelper(@Nullable Context context) {
        super(context);
    }

    public boolean insert(FeedbackEntity entity){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Email",entity.getEmail());
        values.put("Course",entity.getCourse());
        values.put("Suggestion",entity.getSuggestion());
        values.put("Rating",entity.getRating());
        db.insert("FEEDBACK",null,values);
        db.close();
        return true;
    }

    public FeedbackDTO find(String email){
        FeedbackEntity entity=null;
        SQLiteDatabase db=this.getReadableDatabase();
        String where="Email='"+email+"'" ;
        Cursor cursor=db.query(TBL_FEEDBACK,COL_NAMES,where,null,null,null,null);
        String msg="";
        if(cursor.moveToFirst()) {
            String em = cursor.getString(0);
            String cour= cursor.getString(1);
            String sugg = cursor.getString(2);
            float rat = cursor.getFloat(3);
            msg="Row Found";
            entity = new FeedbackEntity(em,cour,sugg,rat);
        }
        FeedbackDTO dto=new FeedbackDTO(true,msg,null,entity);
        return dto;
    }

    public boolean checkFeedbackExists(String email, String course) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "Email = ? AND Course = ?";
        String[] selectionArgs = {email, course};
        Cursor cursor = db.query("FEEDBACK", null, selection, selectionArgs, null, null, null);
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }




}
