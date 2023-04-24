package com.bawp.charismaticprimaryschool.utill;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.bawp.charismaticprimaryschool.R;

public class Prefs {
    SharedPreferences preferences;

    public Prefs(Activity context){
        this.preferences = context.getPreferences(Context.MODE_PRIVATE);
    }


    public void setHighestScore(int score){
        int currentScore = score;
        int lastScore =preferences.getInt("highest_score",0);
        if(currentScore > lastScore){
            //making a new highest score
            preferences.edit().putInt("highest_score", currentScore).apply();
        }

    }

    public int getHighestScore(){
        return preferences.getInt("highest_score", 0);
    }

    public void setStateOfCurrentQuestion(int index){
        preferences.edit().putInt("current_index", index).apply();
    }

    public int getStateOfCurrentQuestion(){
        return preferences.getInt("current_index", 0);
    }

    public void setBackgroundColor(int color){
        preferences.edit().putInt("color",color).apply();
    }

    public int getBackgroundColor(){
        return preferences.getInt("color", R.color.white);
    }

}
