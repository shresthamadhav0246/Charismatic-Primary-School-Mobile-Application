package com.bawp.charismaticprimaryschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bawp.charismaticprimaryschool.data.DatabaseHandler;
import com.bawp.charismaticprimaryschool.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class noteActivity extends AppCompatActivity {
    private EditText edtTitle, edtNote;
    private Button btnSave;
    private FloatingActionButton btnDashboard, btnAddNote, btnMenu, btn_notes;
    private DatabaseHandler db;
    private int noteId = 0;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        edtTitle = findViewById(R.id.edtTitle);
        edtNote = findViewById(R.id.edtAddNote);
        btnSave = findViewById(R.id.btnSave);
        btn_notes = findViewById(R.id.fbaNotes);
        btnDashboard = findViewById(R.id.btnDashboard);
        ConstraintLayout layout = findViewById(R.id.higest_score);

        db = new DatabaseHandler(this);

        Intent getIntent = getIntent();
        String username = getIntent.getStringExtra(MainActivity.USERNAME);

        if(getIntent.hasExtra(noteListActivity.NOTE_ID)){
            noteId = getIntent().getIntExtra(noteListActivity.NOTE_ID, 0);

            Note note =db.getNote(noteId);
            if(note != null){
                edtTitle.setText(note.getNote_name());
                edtNote.setText(note.getDescription());
            }

            isEdit = true;
        }

        btnSave.setOnClickListener(view -> {
            int id = noteId;
            String getTitle = edtTitle.getText().toString().trim();
            String getNote = edtNote.getText().toString().trim();

            if(!TextUtils.isEmpty(getTitle) && !TextUtils.isEmpty(getNote)){
                if(isEdit){

                    Note note = new Note();
                    note.setNoteId(id);
                    note.setNote_name(getTitle);
                    note.setDescription(getNote);
                    note.setCreatedDate(new Date());
                    note.setUserName(username);
                    db.updateNote(note);
                    Intent intent = new Intent(noteActivity.this, noteListActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }else {
                    Note note = new Note(getTitle, getNote, new Date(), username);
                    db.addNote(note);
                    Intent intent = new Intent(noteActivity.this, noteListActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }

            }else{
                Snackbar.make(btnSave, "Empty Title or Note", Snackbar.LENGTH_SHORT).show();
            }
        });

        btn_notes.setOnClickListener(view -> {
            Intent intent = new Intent(noteActivity.this, noteListActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

        btnDashboard.setOnClickListener(view -> {
            Intent intent = new Intent(noteActivity.this, MainActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);
        });

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int colorId = prefs.getInt("backgroundColor", R.color.seconday_white);
        layout.setBackgroundResource(colorId);

    }
}