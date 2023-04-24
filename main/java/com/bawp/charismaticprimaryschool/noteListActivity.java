package com.bawp.charismaticprimaryschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;

import com.bawp.charismaticprimaryschool.adpater.NoteRecycleViewAdpater;
import com.bawp.charismaticprimaryschool.adpater.noteItemClickListener;
import com.bawp.charismaticprimaryschool.data.DatabaseHandler;
import com.bawp.charismaticprimaryschool.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class noteListActivity extends AppCompatActivity implements noteItemClickListener {
    public static final String NOTE_ID = "note_id";
    private  NoteRecycleViewAdpater noteRecycleViewAdpater;
    private FloatingActionButton btnDashboard, btnAddNote, btnMenu, btn_notes;
   private Note note;
   private DatabaseHandler db;
   private String username;
   private ImageView delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        btnDashboard = findViewById(R.id.btnDashboard);
        btnAddNote = findViewById(R.id.btn_add_note);

        ConstraintLayout layout = findViewById(R.id.notes);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        db = new DatabaseHandler(noteListActivity.this);

        RecyclerView recyclerView = findViewById(R.id.note_recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(noteListActivity.this));


        List<Note> noteList = db.getAllNoteByUsername(username);

        noteRecycleViewAdpater = new NoteRecycleViewAdpater(noteList, this, this);
        recyclerView.setAdapter(noteRecycleViewAdpater);

        btnAddNote.setOnClickListener(view -> {
            Intent intent1 = new Intent(noteListActivity.this, noteActivity.class);
            intent1.putExtra("username",username);
            startActivity(intent1);
        });
        btnDashboard.setOnClickListener(view -> {
            Intent intent2 = new Intent(noteListActivity.this, MainActivity.class);
            intent2.putExtra("username",username);
            startActivity(intent2);
        });

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int colorId = prefs.getInt("backgroundColor", R.color.seconday_white);
        layout.setBackgroundResource(colorId);

    }

    @Override
    public void noteItemClick(int position, Note currentNote) {
       Intent intent = new Intent(noteListActivity.this, noteActivity.class);
       intent.putExtra(NOTE_ID, currentNote.getNoteId());
       intent.putExtra("username",username);
      startActivity(intent);
    }
}