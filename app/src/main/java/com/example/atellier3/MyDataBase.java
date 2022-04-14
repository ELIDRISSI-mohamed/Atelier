package com.example.atellier3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.atellier3.beans.User;

public class MyDataBase extends SQLiteOpenHelper {
    private Context context;
    private static String DATABASE_NAME = "Andoroid_db";
    private static int DATABASE_VERSION = 1;

    private static String TABLE_USER = "users";
    private static String COLUMN_ID = "id";
    private static String COLUMN_NAME = "name";
    private static String COLUMN_PWD = "password";

    public MyDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT,"
                + COLUMN_PWD + " TEXT" + ")";
        // Execute script.
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        // Recreate
        onCreate(db);
    }

    // Ajouter un user
    public Long addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, user.getName());
        cv.put(COLUMN_PWD, user.getPwd());
        Long res = db.insert(TABLE_USER, null, cv);

        return res;
    }
    //get user
    public User getUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_USER, null);
        if(c != null){
            c.moveToFirst();
            User user = new User(c.getLong(0), c.getString(1), c.getString(2));
            return user;
        } else {
            return null;
        }
    }
}
