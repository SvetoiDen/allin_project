package com.example.allin_project;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BDNotes extends SQLiteOpenHelper {
    private static final int VERSION_DATABASE = 2;
    private Context context;
    private static final String DATABASE_NAME = "notesdb";
    private static final String TABLE_NAME = "notes";
    private static final String APP_NAME = "apps";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESC = "description";

    BDNotes(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_DATABASE);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String execuse = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT, " + KEY_DESC + " TEXT);";
        db.execSQL(execuse);
        String execuse_1 = "CREATE TABLE " + APP_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT, " + KEY_DESC + " TEXT);";
        db.execSQL(execuse_1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + APP_NAME);
        onCreate(db);
    }

    void appDate() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_NAME, "Настройки");
        contentValues.put(KEY_DESC, "com.android.settings");

        db.insert(APP_NAME, null, contentValues);

        contentValues.put(KEY_NAME, "Браузер");
        contentValues.put(KEY_DESC, "com.android.chrome");

        db.insert(APP_NAME, null, contentValues);

        contentValues.put(KEY_NAME, "YouTube");
        contentValues.put(KEY_DESC, "com.google.android.youtube");

        db.insert(APP_NAME, null, contentValues);

        contentValues.put(KEY_NAME, "Whatsapp");
        contentValues.put(KEY_DESC, "com.whatsapp");

        db.insert(APP_NAME, null, contentValues);

        contentValues.put(KEY_NAME, "Discord");
        contentValues.put(KEY_DESC, "com.discord");

        db.insert(APP_NAME, null, contentValues);

        contentValues.put(KEY_NAME, "Шахматы");
        contentValues.put(KEY_DESC, "com.chess");

        db.insert(APP_NAME, null, contentValues);
    }

    void deleteApp() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + APP_NAME);
    }

    void addNotes(String title, String desc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_NAME, title);
        contentValues.put(KEY_DESC, desc);

        db.insert(TABLE_NAME, null, contentValues);
    }

    Cursor readAllDate() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllDate_App() {
        String query = "SELECT * FROM " + APP_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String id, String name, String desc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Log.i("id", id);

        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_DESC, desc);
        db.update(TABLE_NAME, contentValues, " id=?", new String[]{id});
        context.startActivity(new Intent(context, NotesAcrivity.class));
    }

    void deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, " id=?", new String[]{id});
        context.startActivity(new Intent(context, NotesAcrivity.class));
    }
}
