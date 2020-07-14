package com.example.cyberseced;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "quiz.db";
    private static final int DATABASE_VER = 1;

    private SQLiteDatabase db;


    public QuizDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_TABLE = "CREATE TABLE " + QuizContract.QuestionTable.TABLE_NAME + "("
                + QuizContract.QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + QuizContract.QuestionTable.COLUMN + " TEXT, "
                + QuizContract.QuestionTable.COLUMN_OPTIONA + " TEXT, "
                + QuizContract.QuestionTable.COLUMN_OPTIONB + " TEXT, "
                + QuizContract.QuestionTable.COLUMN_OPTIONC + " TEXT, "
                + QuizContract.QuestionTable.COLUMN_OPTIOND + " TEXT, "
                + QuizContract.QuestionTable.COLUMN_ANSWERS + " INTEGER "
                + ")";

        db.execSQL(SQL_CREATE_TABLE);
        fillQuestionsTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionTable.TABLE_NAME);
        onCreate(db);

    }

    private void fillQuestionsTable() {
        QuizQuestions q1 = new QuizQuestions("A is right", "Ak", "B", "C", "D", 1);
        addQuestion(q1);



    }

    private void addQuestion(QuizQuestions quizQuestions) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionTable.COLUMN, quizQuestions.getQuestion());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTIONA, quizQuestions.getOptionA());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTIONB, quizQuestions.getOptionB());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTIONC, quizQuestions.getOptionC());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTIOND, quizQuestions.getOptionD());
        cv.put(QuizContract.QuestionTable.COLUMN_ANSWERS, quizQuestions.getAnswer());

        db.insert(QuizContract.QuestionTable.TABLE_NAME, null, cv);
    }


    public List<QuizQuestions> getAllQuestions(){
        List<QuizQuestions> quizQuestionsList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionTable.TABLE_NAME, null);

        if (c.moveToFirst()){
            do {
                QuizQuestions questions = new QuizQuestions();
                questions.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN)));
                questions.setOptionA(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTIONA)));
                questions.setOptionB(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTIONB)));
                questions.setOptionC(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTIONC)));
                questions.setOptionD(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTIOND)));
                questions.setAnswer(c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_ANSWERS)));
                quizQuestionsList.add(questions);

            } while (c.moveToNext());
        }

        c.close();
        return quizQuestionsList;
    }





}
