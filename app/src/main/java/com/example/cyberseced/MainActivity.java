package com.example.cyberseced;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {
    //https://www.youtube.com/watch?v=jXtof6OUtcE
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




