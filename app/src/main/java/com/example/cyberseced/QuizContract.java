package com.example.cyberseced;

import android.provider.BaseColumns;
/***************************************************************************************
 *    REFERNCES
 *    Title: SQLite Multiple Choice Quiz - Android Studio Tutorial
 *    Author: Coding in Flow
 *    Date: 10/7/20
 *    Code version: 12/8/2019
 *    Availability: https://www.youtube.com/playlist?list=PLrnPJCHvNZuDCyg4Usq2gHMzz6_CiyQO7
 *
 ***************************************************************************************/
public final class QuizContract {

    private QuizContract(){}
    //create sql tables

    public static class CategoryTable implements  BaseColumns{
        public static final String TABLE_NAME = "quiz_cats";
        public static final String COLUMN_NAME = "name";
    }


    public static class QuestionTable implements BaseColumns {
        public static final String TABLE_NAME= "quiz_questions";
        public static final String COLUMN = "questions";
        public static final String COLUMN_OPTIONA = "A";
        public static final String COLUMN_OPTIONB = "B";
        public static final String COLUMN_OPTIONC = "C";
        public static final String COLUMN_OPTIOND = "D";
        public static final String COLUMN_ANSWERS = "answers";
        public static final String COLUMN_ID = "cat_id";
    }
}
