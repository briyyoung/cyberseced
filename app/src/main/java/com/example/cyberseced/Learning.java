package com.example.cyberseced;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class Learning extends AppCompatActivity {
    public static final String ARG_NAME = " " ;
    private Modules module;
    private ImageView cImage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning);


        Intent intent = getIntent();
        module = Modules.getModules(intent.getStringExtra(ARG_NAME));

            System.out.println(intent.getStringExtra(ARG_NAME));
    //    cImage = findViewById(R.id.Info);
      //  int picture = getResources().getIdentifier("graphic_" + module.getmImage(),"drawable","com.example.cyberseced");
     //   cImage.setImageResource(picture);

    }
}
