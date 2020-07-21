package com.example.cyberseced;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;

import static com.example.cyberseced.LearningDetail.ARG_NAME;

public class Quiz extends AppCompatActivity {
    private TextView question, score, countdown, count;
    private RadioGroup radioGroup;
    private RadioButton a, b, c, d;
    private Button confirmnext;

    private ColorStateList colorStateList;
    public static final String CAT_NAME = " ";
    public static final String EXTRA_SCORE = "extraScore";

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

        //get module name
        Intent intent = getIntent();
        int quizCategory = intent.getIntExtra(QuizEntry.EXTRA_CATEGORY_ID,0);
        String quizCategoryName = intent.getStringExtra(QuizEntry.EXTRA_CATEGORY_NAME);


        question = findViewById(R.id.text_view_question);
        score = findViewById(R.id.text_view_score);
        count = findViewById(R.id.text_view_question_count);

        a = findViewById(R.id.radio_button1);
        b = findViewById(R.id.radio_button2);
        c = findViewById(R.id.radio_button3);
        d = findViewById(R.id.radio_button4);
        radioGroup = findViewById(R.id.radio_group);
        confirmnext = findViewById(R.id.button_confirm_next);
        colorStateList = a.getTextColors();


        QuizDBHelper dbHelper = new QuizDBHelper(this);
        quizQuestionsList = dbHelper.getQuestions(quizCategory);
        questionCountTotal = quizQuestionsList.size();
        Collections.shuffle(quizQuestionsList);

        showNextQuestion();

        confirmnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if ( a.isChecked() || b.isChecked() || c.isChecked() || d.isChecked()){
                        checkAnswer();
                    } else {
                        Toast.makeText(Quiz.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion() {
        a.setTextColor(colorStateList);
        b.setTextColor(colorStateList);
        c.setTextColor(colorStateList);
        d.setTextColor(colorStateList);
        radioGroup.clearCheck();

        if (questionCounter < questionCountTotal){
            currentQuestion = quizQuestionsList.get(questionCounter);

            question.setText(currentQuestion.getQuestion());
            a.setText(currentQuestion.getOptionA());
            b.setText(currentQuestion.getOptionB());
           c.setText(currentQuestion.getOptionC());
            d.setText(currentQuestion.getOptionD());

            questionCounter++;
            count.setText("Question; " + questionCounter + "/" + questionCountTotal);
            answered = false;
            confirmnext.setText("Confirm");

       } else {
            finishQuiz();
        }

    }

    private void checkAnswer (){
        answered = true;
        RadioButton selected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answernum = radioGroup.indexOfChild(selected) + 1;

        if(answernum == currentQuestion.getAnswer()) {
            scores++;
            score.setText("Score: " + scores);
        }
        showSolution();
    }

    private void showSolution(){
        a.setTextColor(Color.RED);
        b.setTextColor(Color.RED);
       c.setTextColor(Color.RED);
        d.setTextColor(Color.RED);

        switch (currentQuestion.getAnswer()) {
            case 1:
                a.setTextColor(Color.GREEN);
                question.setText("Answer A is correct");
                break;
            case 2:
                b.setTextColor(Color.GREEN);
                question.setText("Answer B is correct");
                break;
            case 3:
                c.setTextColor(Color.GREEN);
                question.setText("Answer C is correct");
                break;
            case 4:
                d.setTextColor(Color.GREEN);
                question.setText("Answer D is correct");
                break;

        }

        if (questionCounter< questionCountTotal){
            confirmnext.setText("Next");
                } else {
            confirmnext.setText("Finish");
        }
    }

    private void finishQuiz(){
        finish();
    }


}
