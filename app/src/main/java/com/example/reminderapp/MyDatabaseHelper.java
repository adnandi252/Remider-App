package com.example.reminderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_notes";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SUBJECT = "subject";
    private static final String COLUMN_CONTENT = "content";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_STATUS = "status";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SUBJECT + " VARCHAR(255), " +
                COLUMN_CONTENT + " VARCHAR(1000) NOT NULL, " +
                COLUMN_LOCATION + " VARCHAR, " +
                COLUMN_STATUS + " VARCHAR(20) DEFAULT 'Belum Selesai' CHECK (status IN ('Belum Selesai', 'Selesai')));";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    void createNote(String subject, String content, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_SUBJECT, subject);
        cv.put(COLUMN_CONTENT, content);
        cv.put(COLUMN_LOCATION, location);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1) {
            Toast.makeText(context, "Gagal Menambahkan", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil Menambahkan", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_STATUS + " = 'Belum Selesai'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String subject, String content, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SUBJECT, subject);
        cv.put(COLUMN_CONTENT, content);
        cv.put(COLUMN_LOCATION, location);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Gagal Mengupdate", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil Mengupdate", Toast.LENGTH_SHORT).show();
        }
    }

    void updateDone(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_STATUS, "Selesai");

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Gagal Menandai Selesai", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil Menandai Selesai", Toast.LENGTH_SHORT).show();

        }
    }

    Cursor readDoneData() {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_STATUS + " = 'Selesai'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
