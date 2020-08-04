package com.example.cyberseced;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/***************************************************************************************
 *    REFERNCES
 *    Title: How to Create Welcome Screen (Splash Screen) in Android Studio
 *    Author: Sabith Pkc Mnr
 *    Date: 14/7/20
 *    Code version: 6/9/16
 *    Availability: https://www.youtube.com/watch?v=jXtof6OUtcE
 *    Availability: https://github.com/SabithPkcMnr/SplashScreen
 *
 ***************************************************************************************/
public class MainActivity extends AppCompatActivity {

private static int splash_time = 4000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeInent = new Intent(MainActivity.this, Home.class);
                startActivity(homeInent);
                finish();
            }
        },splash_time);
    }
}




