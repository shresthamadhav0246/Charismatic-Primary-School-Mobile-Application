package com.bawp.charismaticprimaryschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bawp.charismaticprimaryschool.utill.Prefs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MenuActivity extends AppCompatActivity {
    private TextView name, student_id;
    private LinearLayout calculator, backgroundColor, dice_game, logout, vocabulary;
    private ImageView down_arrow;
    private Button btnRemove;
    private LinearLayout backgroundColorPicker;
    private RadioButton backgroundColorRed,backgroundColorGreen,backgroundColorBlue, backgroundColorAqua;
    private ConstraintLayout menu_layout;
    private FloatingActionButton btnDashboard, btnAddNote, btnNoteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        name = findViewById(R.id.name);
        student_id = findViewById(R.id.student_id);

        calculator = findViewById(R.id.linearLayout_calculator);
        dice_game = findViewById(R.id.linearLayout_dice);
        vocabulary = findViewById(R.id.linearLayout_vocabulary);
        logout = findViewById(R.id.linearLayout_logout);
        backgroundColor = findViewById(R.id.linearLayout_dark_mode);
        down_arrow = findViewById(R.id.down_arrow);
        backgroundColorPicker = findViewById(R.id.backgroundColorRadioGroup);
        backgroundColorRed = findViewById(R.id.backgroundColorRed);
        backgroundColorGreen = findViewById(R.id.backgroundColorGreen);
        backgroundColorBlue = findViewById(R.id.backgroundColorBlue);
        backgroundColorAqua = findViewById(R.id.backgroundColorAqua);
        btnAddNote = findViewById(R.id.btn_add_note);
        btnNoteList = findViewById(R.id.fbaNotes);
        btnDashboard = findViewById(R.id.btnDashboard);
        btnRemove = findViewById(R.id.btnRemove);
        menu_layout = findViewById(R.id.menu_layout);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        Intent intent = getIntent();
        String firstname = intent.getStringExtra("FIRSTNAME");
        String surname = intent.getStringExtra("SURNAME");
        String username = intent.getStringExtra("USERNAME");

        name.setText(firstname + " " + surname);


        calculator.setOnClickListener(view -> {
            Intent intent1 = new Intent(MenuActivity.this, CalculatorActivity.class);
            startActivity(intent1);
            Log.d("TAG", "onCreate: " + "calculator theme");
        });

        dice_game.setOnClickListener(view -> {
            Intent intent2 = new Intent(MenuActivity.this, DiceActivity.class);
            startActivity(intent2);
        });

        down_arrow.setOnClickListener(view -> {
          if(backgroundColorPicker.getVisibility() == View.VISIBLE){
              backgroundColorPicker.setVisibility(View.GONE);
          }else{
              backgroundColorPicker.setVisibility(View.VISIBLE);
          }
        });

        vocabulary.setOnClickListener(view -> {
            Intent intent3 = new Intent(MenuActivity.this, VocabularyActivity.class);
            startActivity(intent3);
        });

        backgroundColorRed.setOnClickListener(view -> {
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
            editor.putInt("backgroundColor", R.color.red);
            editor.apply();
            menu_layout.setBackgroundResource(prefs.getInt("backgroundColor",R.color.white));
        });

        backgroundColorGreen.setOnClickListener(view -> {
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
            editor.putInt("backgroundColor", R.color.green);
            editor.apply();
            menu_layout.setBackgroundResource(prefs.getInt("backgroundColor",R.color.white));
        });

        backgroundColorBlue.setOnClickListener(view -> {

            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
            editor.putInt("backgroundColor", R.color.blue);
            editor.apply();
            menu_layout.setBackgroundResource(prefs.getInt("backgroundColor",R.color.white));
        });

        backgroundColorAqua.setOnClickListener(view -> {
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
            editor.putInt("backgroundColor", R.color.aqua);
            editor.apply();
            menu_layout.setBackgroundResource(prefs.getInt("backgroundColor",R.color.white));
        });

        btnRemove.setOnClickListener(view -> {
            SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(this);
            prefs1.edit().remove("backgroundColor").apply();
            menu_layout.setBackgroundResource(R.color.white);
        });

        logout.setOnClickListener(view -> {
            Intent logoutIntent = new Intent(MenuActivity.this, LoginActivity.class);
            startActivity(logoutIntent);
        });

        btnNoteList.setOnClickListener(view -> {
            Intent intent5 = new Intent(MenuActivity.this, noteListActivity.class);
            intent5.putExtra("username", username);
            startActivity(intent5);
        });

        btnDashboard.setOnClickListener(view -> {
            Intent intent6 = new Intent(MenuActivity.this, MainActivity.class);
            intent6.putExtra("username",username);
            startActivity(intent6);
        });

        btnAddNote.setOnClickListener(view -> {
            Intent intent6 = new Intent(MenuActivity.this, noteActivity.class);
            intent6.putExtra("username",username);
            startActivity(intent6);
        });

    }
}