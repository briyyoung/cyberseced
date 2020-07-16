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
    public static String CAT = " ";

    private Modules module;
    private ImageView cImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning);

        //get module name
        Intent intent = getIntent();
        module = Modules.getModules(intent.getStringExtra(ARG_NAME));
        CAT = intent.getStringExtra(ARG_NAME);

        System.out.println(intent.getStringExtra(ARG_NAME));
        System.out.println(intent.getStringExtra(CAT));



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
                Intent intent = new Intent(LearningDetail.this, Quiz.class);
                startActivity(intent);

                Intent intents = getIntent();
                module = Modules.getModules(intents.getStringExtra(CAT));

               intent.putExtra(CAT, module.getName());
                intent.putExtra("CATEGORY_NAME", intents.getStringExtra(CAT));

                System.out.println("fsdfsdfs "+ intents.getStringExtra(CAT));



            }
        });
    }
}
