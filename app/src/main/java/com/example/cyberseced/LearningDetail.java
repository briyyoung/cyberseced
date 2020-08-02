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

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class LearningDetail extends YouTubeBaseActivity {
    public static final String ARG_NAME = " ";
     public static int CAT = 0 ;
YouTubePlayerView playerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    Button play;
    private Modules module;
    private ImageView cImage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning);
        playerView = findViewById(R.id.Vidview);
        play = findViewById(R.id.play);





        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            playerView.initialize(Config.getAPI(), onInitializedListener);
            }
        });

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

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {


                if (CAT==1){
                youTubePlayer.loadVideo("Vo1urF6S4u0");
                }
                else if (CAT==2) {
                    youTubePlayer.loadVideo("FrNLE1Ixgak");
                }
                else if (CAT==3) {
                    youTubePlayer.loadVideo("GCWBf7WKYyA");
                }
                else if (CAT==4) {
                    youTubePlayer.loadVideo("qskRQchLjHQ");
                }
                else if (CAT==5) {
                    youTubePlayer.loadVideo("_C7sNvIGQzM");
                }
                else if (CAT==6) {
                    youTubePlayer.loadVideo("ORS9DPKJlks");
                }


            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        Button TakeQuiz = (Button) findViewById(R.id.QuizBtn);



        //new activity
        TakeQuiz.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {


                System.out.println(CAT);
                Intent intent = new Intent(LearningDetail.this, Quiz.class);
                intent.putExtra("CAT", CAT);
                startActivity(intent);





            }
        });
    }
}
