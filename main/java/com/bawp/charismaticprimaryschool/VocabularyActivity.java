package com.bawp.charismaticprimaryschool;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.bawp.charismaticprimaryschool.model.Question;

public class VocabularyActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);

        Button btnStart = findViewById(R.id.btnStart);

        Question[] questions = new Question[]{
          new Question(R.string.question11, R.string.option11,R.string.option12, R.string.option13,R.string.option14,1),
                new Question(R.string.question12, R.string.option11,R.string.option12, R.string.option13,R.string.option14,4),
                new Question(R.string.question13, R.string.option11,R.string.option12, R.string.option13,R.string.option14,3),
        };

        btnStart.setOnClickListener(view ->{
            Intent intent = new Intent(VocabularyActivity.this, QuizActivity.class);
            intent.putExtra("question", questions);
            startActivity(intent);
        });

    }





}
