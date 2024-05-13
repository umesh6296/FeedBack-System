package com.example.feedback;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class UserDBHelper extends DBHelper{

    private static final String[] COL_NAMES={"Email","Name","Password","Role"};
    public UserDBHelper(@Nullable Context context) {
        super(context);
    }

    public UserDTO find(String email,String pass){
        UserEntity entity=null;
        SQLiteDatabase db=this.getReadableDatabase();
        String where="Email='"+email+"' and Password='"+pass+"'" ;
        Cursor cursor=db.query(TBL_USER,COL_NAMES,where,null,null,null,null);
        String msg="";
        if(cursor.moveToFirst()) {
            String em = cursor.getString(0);
            String name= cursor.getString(1);
            String passw = cursor.getString(2);
            String role = cursor.getString(3);
            msg="Row Found";
            entity = new UserEntity(em,name,passw,role);
        }
        UserDTO dto=new UserDTO(true,msg,null,entity);
        return dto;
    }

    public boolean insert(UserEntity user){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Email",user.getEmail());
        values.put("Name",user.getName());
        values.put("Password",user.getPassword());
        values.put("Role",user.getRole());
        db.insert("User",null,values);
        db.close();
        return true;
    }
    public boolean checkEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = {email};
        String selection = "Email=?";
        Cursor cursor = db.query("User", null, selection, selectionArgs, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }






}
