package com.bawp.charismaticprimaryschool;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class DiceActivity extends AppCompatActivity {
    ImageView diceImage;
    Button btnRoll;
    TextView result, winningMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        diceImage = findViewById(R.id.diceImage);
        btnRoll = findViewById(R.id.btnRoll);
        result = findViewById(R.id.displayRoll);
        winningMessage = findViewById(R.id.winMessage);

        Random random = new Random();

        btnRoll.setOnClickListener(view -> {
            int diceNumber = random.nextInt(6) + 1;

            switch (diceNumber){
                case 1 : diceImage.setImageResource(R.drawable.dice1);
                break;
                case 2 : diceImage.setImageResource(R.drawable.dice2);
                    break;
                case 3 : diceImage.setImageResource(R.drawable.dice3);
                    break;
                case 4 : diceImage.setImageResource(R.drawable.dice4);
                    break;
                case 5 : diceImage.setImageResource(R.drawable.dice5);
                    break;
                case 6 : {
                    diceImage.setImageResource(R.drawable.dice6);
                    winningMessage.setVisibility(View.VISIBLE);
                    ObjectAnimator fadeOut = ObjectAnimator.ofFloat(winningMessage, "alpha", 1f, 0f);
                    fadeOut.setDuration(500); // Set animation duration in milliseconds
                    fadeOut.setStartDelay(2500); // Set delay before fade-out animation starts
                    fadeOut.start(); // Start the fade-out animation
                    new Handler().postDelayed(() -> {
                        winningMessage.setVisibility(View.GONE); // Hide the winningMessage after 3 seconds
                    }, 3000);
                }
                    break;
                default: diceImage.setImageResource(R.drawable.dice1);
            }

            ObjectAnimator rotate = ObjectAnimator.ofFloat(diceImage, "rotation", 0f, 360f);
            rotate.setDuration(1000); // Set animation duration in milliseconds

            // Create fade-in animation
            ObjectAnimator fadeIn = ObjectAnimator.ofFloat(diceImage, "alpha", 0f, 1f);
            fadeIn.setDuration(500); // Set animation duration in milliseconds


            // Create animation set
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(fadeIn, rotate); // Add animations in sequence
            animatorSet.start(); // Start the animation

            result.setText("You are rolled "+ diceNumber);

        });
    }
}