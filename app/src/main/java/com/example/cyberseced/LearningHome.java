package com.example.cyberseced;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
/***************************************************************************************
 *    REFERENCES
 *    Title: Android Beginner Tutorial #16 - Play YouTube Videos using Android Player API in Android Studio
 *    Author: CodingWithMitch
 *    Date: 24/3/2017
 *    Code version: 24/3/2017
 *    Availability: https://www.youtube.com/watch?v=W4hTJybfU7s
 *
 ***************************************************************************************/
public class LearningHome extends AppCompatActivity {
    public static final String ARG_NAME = " " ;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //pass to module page based on user click
        Intent intent = getIntent();
        String moduleName = intent.getStringExtra(LearningEntry.CHOICE);
        launchmodule(moduleName);
    }

    public void launchmodule (String moduleName){
        Intent intent = new Intent(this, LearningDetail.class);
        intent.putExtra(LearningDetail.ARG_NAME, moduleName);
        startActivity(intent);
        finish();
    }
}
