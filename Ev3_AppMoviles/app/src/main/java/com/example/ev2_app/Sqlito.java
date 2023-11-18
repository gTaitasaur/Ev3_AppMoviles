package com.example.ev2_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class Sqlito extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "user_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_REPORT = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_HORA = "hora";
    private static final String KEY_FECHA = "fecha";
    private static final String KEY_LAB = "laboratorio";
    private static final String KEY_FIRSTNAME = "nombre";
    private static final String KEY_RUT = "rut";
    private static final String KEY_DESCRIPTION = "descripcion";


    private static final String CREATE_TABLE_INCIDENTES = "CREATE TABLE "
            + TABLE_REPORT + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FIRSTNAME + " TEXT, "+ KEY_HOBBY + " TEXT, "+ KEY_FAVNUMBER + " TEXT );";

    public Sqlito(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_INCIDENTES);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(CREATE_TABLE_INCIDENTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_REPORT + "'");
        onCreate(db);
    }

    public long addUserDetail(String name, String hobby, String favnumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, name);
        values.put(KEY_HOBBY, hobby);
        values.put(KEY_FAVNUMBER, favnumber);
       // insert row in students table
        long insert = db.insert(TABLE_REPORT, null, values);

        return insert;
    }

    public ArrayList<UserModel> getAllUsers() {
        ArrayList<UserModel> userModelArrayList = new ArrayList<UserModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_REPORT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                UserModel userModel = new UserModel();
                userModel.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                userModel.setName(c.getString(c.getColumnIndex(KEY_FIRSTNAME)));
                userModel.setHobby(c.getString(c.getColumnIndex(KEY_HOBBY)));
                userModel.setFavnumber(c.getString(c.getColumnIndex(KEY_FAVNUMBER)));
               // adding to Students list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
         }
        return userModelArrayList;
    }

    public int updateUser(int id, String name, String hobby, String favnumber) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, name);
        values.put(KEY_HOBBY, hobby);
        values.put(KEY_FAVNUMBER, favnumber);
       // update row in students table base on students.is value
        return db.update(TABLE_REPORT, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteUSer(int id) {

        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REPORT, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

}

