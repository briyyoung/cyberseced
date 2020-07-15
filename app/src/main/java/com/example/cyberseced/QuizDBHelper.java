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

        final String SQL_CREATE_CAT_TABLE = "CREATE TABLE " +
                QuizContract.CategoryTable.TABLE_NAME + "(" +
                QuizContract.CategoryTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.CategoryTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_TABLE = "CREATE TABLE " + QuizContract.QuestionTable.TABLE_NAME + "("
                + QuizContract.QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + QuizContract.QuestionTable.COLUMN + " TEXT, "
                + QuizContract.QuestionTable.COLUMN_OPTIONA + " TEXT, "
                + QuizContract.QuestionTable.COLUMN_OPTIONB + " TEXT, "
                + QuizContract.QuestionTable.COLUMN_OPTIONC + " TEXT, "
                + QuizContract.QuestionTable.COLUMN_OPTIOND + " TEXT, "
                + QuizContract.QuestionTable.COLUMN_ANSWERS + " INTEGER, "
                + QuizContract.QuestionTable.COLUMN_ID + " INTEGER, "
                + "FOREIGN KEY (" + QuizContract.QuestionTable.COLUMN_ID + " ) REFERENCES "
                + QuizContract.CategoryTable.TABLE_NAME + "(" + QuizContract.CategoryTable._ID + ")" + "ON DELETE CASCADE"
                + ")";
        db.execSQL(SQL_CREATE_CAT_TABLE);
        db.execSQL(SQL_CREATE_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.CategoryTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionTable.TABLE_NAME);
        onCreate(db);

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }


    private void fillCategoriesTable() {
        QuizCategories c1 = new QuizCategories("Social_Engineering");
        addQuizCategories(c1);
        QuizCategories c2 = new QuizCategories("Psychology_Based");
        addQuizCategories(c2);
        QuizCategories c3 = new QuizCategories("safe_online");
        addQuizCategories(c3);
        QuizCategories c4 = new QuizCategories("Work_from_Home");
        addQuizCategories(c4);
        QuizCategories c5 = new QuizCategories("Best_practices");
        addQuizCategories(c5);
        QuizCategories c6 = new QuizCategories("Physical_Security");
        addQuizCategories(c6);
    }

    private void addQuizCategories(QuizCategories quizCategories) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.CategoryTable.COLUMN_NAME, quizCategories.getName());
        db.insert(QuizContract.CategoryTable.TABLE_NAME, null, cv);
    }


    private void fillQuestionsTable() {
        QuizQuestions q1 = new QuizQuestions(" Best is right", "Ak", "B", "C", "D", 1, QuizCategories.Best_practices);
        addQuestion(q1);
        QuizQuestions q2 = new QuizQuestions("Phy is right", "A", "B", "C", "D", 2, QuizCategories.Physical_Security);
        addQuestion(q2);
        QuizQuestions q3 = new QuizQuestions("Social is right", "A", "B", "C", "D", 3, QuizCategories.Social_Engineering);
        addQuestion(q3);
        QuizQuestions q4 = new QuizQuestions("Home is right", "A", "B", "C", "D", 4, QuizCategories.Work_from_Home);
        addQuestion(q4);
        QuizQuestions q5 = new QuizQuestions("online is right", "A", "B", "C", "D", 4, QuizCategories.safe_online);
        addQuestion(q5);
        QuizQuestions q6 = new QuizQuestions("doc is right", "A", "B", "C", "D", 4, QuizCategories.Psychology_Based);
        addQuestion(q6);
    }

    private void addQuestion(QuizQuestions quizQuestions) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionTable.COLUMN, quizQuestions.getQuestion());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTIONA, quizQuestions.getOptionA());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTIONB, quizQuestions.getOptionB());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTIONC, quizQuestions.getOptionC());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTIOND, quizQuestions.getOptionD());
        cv.put(QuizContract.QuestionTable.COLUMN_ANSWERS, quizQuestions.getAnswer());
        cv.put(QuizContract.QuestionTable.COLUMN_ID,quizQuestions.getCatID());
        db.insert(QuizContract.QuestionTable.TABLE_NAME, null, cv);
    }


    public List<QuizQuestions> getAllQuestions() {
        List<QuizQuestions> quizQuestionsList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                QuizQuestions questions = new QuizQuestions();
                questions.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN)));
                questions.setOptionA(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTIONA)));
                questions.setOptionB(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTIONB)));
                questions.setOptionC(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTIONC)));
                questions.setOptionD(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTIOND)));
                questions.setAnswer(c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_ANSWERS)));
                questions.setCatID(c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_ID)));
                quizQuestionsList.add(questions);

            } while (c.moveToNext());
        }

        c.close();
        return quizQuestionsList;
    }


}
