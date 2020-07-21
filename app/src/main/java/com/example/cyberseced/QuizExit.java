package com.example.cyberseced;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class QuizExit extends AppCompatActivity {

        private static final int REQUEST_CODE_QUIZ = 1;

        public static final String SHARED_PREFS = "sharedPrefs";
        public static final String KEY_HIGHSCORE = "keyHighscore";
        private TextView textViewHighscore;

        private int highscore;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.quiz_exit);
            textViewHighscore = findViewById(R.id.text_view_highscore);


              loadHighscore();

            Button buttonStartQuiz = findViewById(R.id.exitQuizbtn);
            buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(QuizExit.this, LearningEntry.class);
                     startActivity(intent);
                }
            });
        }


        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_CODE_QUIZ) {
                if (resultCode == RESULT_OK) {
                   int score = data.getIntExtra(Quiz.EXTRA_SCORE, 0);
                    if (score > highscore) {
                        updateHighscore(score);
                    }
                }
            }
        }


        private void loadHighscore() {
            SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            highscore = prefs.getInt(KEY_HIGHSCORE, 0);
            textViewHighscore.setText("Highscore: " + highscore);
        }

        private void updateHighscore(int highscoreNew) {
            highscore = highscoreNew;
            textViewHighscore.setText("Highscore: " + highscore);
            SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(KEY_HIGHSCORE, highscore);
            editor.apply();
        }

    }







