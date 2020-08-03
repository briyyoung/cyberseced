package com.example.cyberseced;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

/***************************************************************************************
 *    REFERNCES
 *    Title: Animated Gradient Background like Instagram - Android Studio Tutorial
 *    Author: Coding in Flow
 *    Date: 7/7/20
 *    Code version: 8/11/2017
 *    Availability: https://codinginflow.com/tutorials/android/animated-gradient-background
 *    Availability: https://www.youtube.com/watch?v=x_DXXGvyfh8
 *
 ***************************************************************************************/

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);


        ConstraintLayout constraintLayout = findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        Button sign = (Button) findViewById(R.id.signup);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Signup.class);
                startActivity(intent);


            }
        });

        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Login.class);
                startActivity(intent);


            }
        });


    }
}
