package com.example.cyberseced;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract(){}

    public static class QuestionTable implements BaseColumns {
        public static final String TABLE_NAME= "quiz_questions";
        public static final String COLUMN = "questions";
        public static final String COLUMN_OPTIONA = "A";
        public static final String COLUMN_OPTIONB = "B";
        public static final String COLUMN_OPTIONC = "C";
        public static final String COLUMN_OPTIOND = "D";
        public static final String COLUMN_ANSWERS = "answers";


    }





}
