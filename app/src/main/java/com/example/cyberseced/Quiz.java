package com.example.cyberseced;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;

public class Quiz extends AppCompatActivity {
    private TextView question, score, countdown, count;
    private RadioGroup radioGroup;
    private RadioButton a, b, c, d;
    private Button confirmnext;

    private ColorStateList colorStateList;

    private List<QuizQuestions> quizQuestionsList;
    private int questionCounter;
    private int questionCountTotal;
    private QuizQuestions currentQuestion;

    private int scores;
    private boolean answered;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        question = findViewById(R.id.text_view_question);
        score = findViewById(R.id.text_view_score);
        count = findViewById(R.id.text_view_question_count);
        countdown = findViewById(R.id.text_view_countdown);

        a = findViewById(R.id.radio_button1);
        b = findViewById(R.id.radio_button2);
        c = findViewById(R.id.radio_button3);
        d = findViewById(R.id.radio_button4);
        radioGroup = findViewById(R.id.radio_group);

        colorStateList = a.getTextColors();


        QuizDBHelper dbHelper = new QuizDBHelper(this);
        quizQuestionsList = dbHelper.getAllQuestions();
        questionCountTotal = quizQuestionsList.size();
        Collections.shuffle(quizQuestionsList);

        showNextQuestion();

    }

    private void showNextQuestion() {
        a.setTextColor(colorStateList);
        b.setTextColor(colorStateList);
        c.setTextColor(colorStateList);
        d.setTextColor(colorStateList);
        radioGroup.clearCheck();
    }


}
