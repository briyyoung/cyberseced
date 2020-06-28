package com.example.cyberseced;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class Learning extends AppCompatActivity {
    public static final String ARG_NAME = " " ;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Intent intent = getIntent();
        String moduleName = intent.getStringExtra(MainActivity.CHOICE);

        launchmodule(moduleName);



    }

    public void launchmodule (String moduleName){
        Intent intent = new Intent(this, LearningDetail.class);
        intent.putExtra(LearningDetail.ARG_NAME, moduleName);
        startActivity(intent);
        finish();
    }
}
