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
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_RUT = "rut";
    private static final String KEY_DESCRIPTION = "description";


    private static final String CREATE_TABLE_INCIDENTES = "CREATE TABLE "
            + TABLE_REPORT + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_HORA + " TEXT, " + KEY_FECHA + " TEXT, " + KEY_LAB + " TEXT, "+ KEY_NOMBRE + " TEXT, "+ KEY_RUT + " TEXT, "+ KEY_DESCRIPTION + " TEXT );";

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

    public long addUserDetail(String hora, String fecha, String laboratorio, String nombre, String rut, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_HORA, nombre);
        values.put(KEY_FECHA, fecha);
        values.put(KEY_LAB, laboratorio);
        values.put(KEY_NOMBRE, nombre);
        values.put(KEY_RUT, rut);
        values.put(KEY_DESCRIPTION, description);
        long insert = db.insert(TABLE_REPORT, null, values);

        return insert;
    }

    public ArrayList<UserModel> getAllUsers() {
        ArrayList<UserModel> userModelArrayList = new ArrayList<UserModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_REPORT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                UserModel userModel = new UserModel();
                userModel.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                userModel.setFecha
                userModel.setName(c.getString(c.getColumnIndex(KEY_NOMBRE)));
                userModel.setHobby(c.getString(c.getColumnIndex(KEY_HOBBY)));
                userModel.setFavnumber(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));

                userModelArrayList.add(userModel);
            } while (c.moveToNext());
         }
        return userModelArrayList;
    }

    public int updateUser(int id, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DESCRIPTION, description);

        return db.update(TABLE_REPORT, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteUSer(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REPORT, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

}

