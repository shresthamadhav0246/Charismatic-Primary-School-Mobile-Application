package com.bawp.charismaticprimaryschool.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.bawp.charismaticprimaryschool.R;
import com.bawp.charismaticprimaryschool.adpater.NoteRecycleViewAdpater;
import com.bawp.charismaticprimaryschool.model.Note;
import com.bawp.charismaticprimaryschool.utill.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME , null, Util.VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTE_TABLE = "CREATE TABLE " + Util.TABLE_NAME + " ("
                + Util.KEY_ID + " INTEGER PRIMARY KEY,"
                + Util.KEY_NAME + " TEXT,"
                + Util.KEY_DESCRIPTION + " TEXT,"
                + Util.KEY_USER_NAME + " TEXT,"
                + Util.KEY_CREATED_DATE + " DATE"
                + ")";

        db.execSQL(CREATE_NOTE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + Util.TABLE_NAME;
        db.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});

onCreate(db);

    }

    public void addNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, note.getNote_name());
        values.put(Util.KEY_DESCRIPTION, note.getDescription());
        values.put(Util.KEY_CREATED_DATE, note.getCreatedDate().getTime());
        values.put(Util.KEY_USER_NAME, note.getUserName());

        db.insert(Util.TABLE_NAME, null, values);
        db.close();

    }

    public Note getNote(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.KEY_ID, Util.KEY_NAME, Util.KEY_DESCRIPTION,Util.KEY_CREATED_DATE, Util.KEY_USER_NAME},
                Util.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if(cursor !=null)
            cursor.moveToFirst();

            Note note = new Note();
            note.setNoteId(Integer.parseInt(cursor.getString(0)));
            note.setNote_name(cursor.getString(1));
            note.setDescription(cursor.getString(2));
            note.setUserName(cursor.getString(3));
        Date createdDate = new Date(cursor.getLong(4));
        note.setCreatedDate(createdDate);

            return note;

    }
    public List<Note> getAllNote(){
        List<Note> noteList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if(cursor.moveToFirst()){
            do{
                Note note = new Note();
                note.setNoteId(Integer.parseInt(cursor.getString(0)));
                note.setNote_name(cursor.getString(1));
                note.setDescription(cursor.getString(2));
                note.setUserName(cursor.getString(3));
                Date createdDate = new Date(cursor.getLong(4));
                note.setCreatedDate(createdDate);

                noteList.add(note);
            }while(cursor.moveToNext());
        }
        return noteList;
    }
    public int updateNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, note.getNote_name());
        values.put(Util.KEY_DESCRIPTION, note.getDescription());
        values.put(Util.KEY_CREATED_DATE, note.getCreatedDate().getTime());
        values.put(Util.KEY_USER_NAME, note.getUserName());

        return db.update(Util.TABLE_NAME, values, Util.KEY_ID + "=?", new String[]{String.valueOf(note.getNoteId())});
    }

    public void deleteNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?", new String[]{String.valueOf(note.getNoteId())});
        db.close();
    }

    public List<Note> getAllNoteByUsername(String username) {
        List<Note> noteList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Util.TABLE_NAME + " WHERE " + Util.KEY_USER_NAME + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{username});

        if(cursor.moveToFirst()){
            do{
                Note note = new Note();
                note.setNoteId(Integer.parseInt(cursor.getString(0)));
                note.setNote_name(cursor.getString(1));
                note.setDescription(cursor.getString(2));
                note.setUserName(cursor.getString(3));
                Date createdDate = new Date(cursor.getLong(4));
                note.setCreatedDate(createdDate);

                noteList.add(note);
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return noteList;
    }

        public int getCount(){
        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();

    }

}
