package com.example.cyberseced;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class LearningDetail extends AppCompatActivity {
    public static final String ARG_NAME = " ";
     int CAT = 0 ;

    private Modules module;
    private ImageView cImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning);

        //get module name
        Intent intent = getIntent();
        module = Modules.getModules(intent.getStringExtra(ARG_NAME));



        if (intent.getStringExtra(ARG_NAME).equals("Social Engineering Attacks")) {
            System.out.println(intent.getStringExtra(ARG_NAME));
            CAT = 1;
           }

        else if (intent.getStringExtra(ARG_NAME).equals("Psychology Based Attacks")){
            CAT = 2;

        }
        else if (intent.getStringExtra(ARG_NAME).equals("Stay safe online")){
            CAT = 3;

        }
        else if (intent.getStringExtra(ARG_NAME).equals("Work from Home")){

            CAT = 4;
        }
        else if (intent.getStringExtra(ARG_NAME).equals("Best practices")){
            CAT = 5;

        }
        else if (intent.getStringExtra(ARG_NAME).equals("Physical Security")){

            CAT = 6;
        }





        // add image based on name
        cImage = findViewById(R.id.Info);
        int graphic = module.getGraphic();
        cImage.setImageResource(graphic);


        Button TakeQuiz = (Button) findViewById(R.id.QuizBtn);
        Button MoreInfo = (Button) findViewById(R.id.MoreInfoBtn);


        //change image
        MoreInfo.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Drawable MoreInfo = getResources().getDrawable(R.drawable.graphic7);
                cImage.setImageDrawable(MoreInfo);
            }
        });


        //new activity
        TakeQuiz.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {


                System.out.println(CAT);
                Intent intent = new Intent(LearningDetail.this, QuizEntry.class);
                startActivity(intent);

                Intent intents = getIntent();



            }
        });
    }
}
