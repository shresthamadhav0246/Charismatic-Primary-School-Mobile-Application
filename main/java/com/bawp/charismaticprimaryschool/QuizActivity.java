package com.bawp.charismaticprimaryschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.bawp.charismaticprimaryschool.model.Question;
import com.bawp.charismaticprimaryschool.model.Score;
import com.bawp.charismaticprimaryschool.utill.Prefs;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView displayScore, question, question_status, highestScore;
    private RadioButton option1, option2, option3,option4;
    private RadioGroup radioGroup;
    private Button btnPrevious, btnNext;
    private ImageView imageView;
    private VideoView videoView;
    private int questionIndex = 0;
    private boolean answered;
    private Question[] questions;
    private Score score;
    private int scoreCounter = 0;
    private Prefs prefs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        question = findViewById(R.id.question);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        radioGroup = findViewById(R.id.radioGroup);
        displayScore = findViewById(R.id.score);
        question_status = findViewById(R.id.question_status);
        highestScore = findViewById(R.id.highestScore);
        imageView = findViewById(R.id.imageView);
        videoView = findViewById(R.id.videoView);
        ConstraintLayout layout = findViewById(R.id.quiz);

        score = new Score();
        prefs = new Prefs(QuizActivity.this);

        questionIndex = prefs.getStateOfCurrentQuestion();

        Intent intent = getIntent();
     questions = (Question[]) intent.getSerializableExtra("question");

        updateQuestion();
        updateCounter();

        option1.setOnClickListener(view ->{
            checkAnswer(1);
            updateQuestion();
        });
        option2.setOnClickListener(view ->{
            checkAnswer(2);
          updateQuestion();
        });

        option3.setOnClickListener(view ->{
            checkAnswer(3);
            updateQuestion();
        });

        option4.setOnClickListener(view ->{
            checkAnswer(4);
            updateQuestion();
        });


        btnNext.setOnClickListener(view -> {
            getNextQuestion();
        });

        btnPrevious.setOnClickListener(view ->{
           if(questionIndex > 0){
               questionIndex = (questionIndex - 1) % questions.length;
               updateQuestion();
           }
        });


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int colorId = prefs.getInt("backgroundColor", R.color.seconday_white);
        layout.setBackgroundResource(colorId);



    }

    private void getNextQuestion() {
        questionIndex = (questionIndex + 1) % questions.length;
        updateQuestion();
    }

    private void updateCounter() {
        question_status.setText("Question:"+ questionIndex +"/" +questions.length);
        displayScore.setText("Score:" + scoreCounter);
        highestScore.setText("Highest Score: "+prefs.getHighestScore());
    }

    private void checkAnswer(int userChoseAnswerId){
        int correctAnswerId = questions[questionIndex].getCorrectOptionId();
        String messageID;
        if( userChoseAnswerId == correctAnswerId){
            messageID = "Correct answer";
            fadeAnimation();
            addScore();
        }else{
            messageID = "Incorrect answer";
            shakeAnimation();
        }
        Snackbar.make(btnNext, messageID, Snackbar.LENGTH_SHORT).show();
    }

    private void updateQuestion() {
        if (questions != null) {
            if(questionIndex == 3){
                imageView.setVisibility(View.VISIBLE);
                question.setVisibility(View.GONE);
                imageView.setImageResource(questions[questionIndex].getQuestionId());
            } else if(questionIndex == 4){
                imageView.setVisibility(View.GONE);
                question.setVisibility(View.GONE);
                videoView.setVisibility(View.VISIBLE);
             videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + questions[questionIndex].getQuestionId()));
                MediaController mediaController = new MediaController(this);
                videoView.setMediaController(mediaController);
                mediaController.setAnchorView(videoView);

            }
            else {
                imageView.setVisibility(View.GONE);
                videoView.setVisibility(View.GONE);
                question.setVisibility(View.VISIBLE);
                question.setText(questions[questionIndex].getQuestionId());
                option1.setText(questions[questionIndex].getOption1Id());
                option2.setText(questions[questionIndex].getOption2Id());
                option3.setText(questions[questionIndex].getOption3Id());
                option4.setText(questions[questionIndex].getOption4Id());
                answered = false;
            }
        }else {
            Log.d("TAG", "updatePhysicsQuestion: NULL");
        }
        updateCounter();
        }

        private void addScore(){
        scoreCounter += 10;
        score.setScore(scoreCounter);

        }

        private void shakeAnimation(){
        Animation shake = AnimationUtils.loadAnimation(QuizActivity.this, R.anim.shake_animation);
        if(questionIndex == 3){
            imageView.startAnimation(shake);
        }else if(questionIndex == 4){
            videoView.startAnimation(shake);
        }else {
            question.startAnimation(shake);
        }

        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                question.setTextColor(Color.RED);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                question.setTextColor(Color.BLACK);
                getNextQuestion();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void fadeAnimation(){
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setRepeatMode(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        if(questionIndex == 3){
            imageView.startAnimation(alphaAnimation);
        }else if(questionIndex == 4){
            videoView.startAnimation(alphaAnimation);
        }else {
            question.startAnimation(alphaAnimation);
        }
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                question.setTextColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                question.setTextColor(Color.BLACK);
                getNextQuestion();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        prefs.setHighestScore(score.getScore());
        prefs.setStateOfCurrentQuestion(questionIndex);

    }
}

