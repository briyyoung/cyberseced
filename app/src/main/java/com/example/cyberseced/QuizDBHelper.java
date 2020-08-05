package com.example.cyberseced;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
/***************************************************************************************
 *    REFERNCES
 *    Title: SQLite Multiple Choice Quiz - Android Studio Tutorial
 *    Author: Coding in Flow
 *    Date: 10/7/20
 *    Code version: 12/8/2019
 *    Availability: https://www.youtube.com/playlist?list=PLrnPJCHvNZuDCyg4Usq2gHMzz6_CiyQO7
 *
 ***************************************************************************************/
public class QuizDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "quiz.db";
    private static final int DATABASE_VER = 1;
    private SQLiteDatabase db;


    public QuizDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    //create tables
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

    //change table if version changes
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

    //populate category table
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

    //populate question table
    private void fillQuestionsTable() {
        QuizQuestions q1 = new QuizQuestions("When working from home, you must be aware of…", "Desk space", "Computer battery", "Antivirus Software", "Phishing and Whaling", 4, QuizCategories.Work_from_home);
        addQuestion(q1);
        QuizQuestions q2 = new QuizQuestions("What network type should you connect to when working from home?", "Public Wi-Fi", "Secure network", "Neighbours Wi-Fi", "Mobile Hotspot", 2, QuizCategories.Work_from_home);
        addQuestion(q2);
        QuizQuestions q3 = new QuizQuestions("What should you do with work-issued devices?", "Limit access", "Give it to friends and family", "Leave it on the table", "Turn it upside down", 1, QuizCategories.Work_from_home);
        addQuestion(q3);
        QuizQuestions q4 = new QuizQuestions("What rule should you use to backup important files at home?", "The 2-2-2 rule", "The 4-5 rule", "The 3-2-1 rule", "The Copy and Paste rule", 3, QuizCategories.Work_from_home);
        addQuestion(q4);
        QuizQuestions q5 = new QuizQuestions("You should always _____ your software and web browsers", "Recover", "Use", "Update", "Remove", 3, QuizCategories.Work_from_home);
        addQuestion(q5);

        QuizQuestions q6 = new QuizQuestions("Protect your ____", "Hat", "Password", "Lunch", "Keyboard", 2, QuizCategories.Stay_safe_online);
        addQuestion(q6);
        QuizQuestions q7 = new QuizQuestions("____ your computer screen when you leave your desk", "Lock", "Hide", "Smash", "Show", 1, QuizCategories.Stay_safe_online);
        addQuestion(q7);
        QuizQuestions q8 = new QuizQuestions("Cover up ______ & ______ documents when you leave your desk", "Emotional & Sensitive", "Discrete & Quiet", "Confidential & Sensitive", "Empty & Useless", 3, QuizCategories.Stay_safe_online);
        addQuestion(q8);
        QuizQuestions q9 = new QuizQuestions("File documents in _____ cabinets to prevent a privacy breach", "Blue", "Open", "Locked", "Small", 3, QuizCategories.Stay_safe_online);
        addQuestion(q9);
        QuizQuestions q10 = new QuizQuestions("Destroy or ____ old data", "Archive", "Move", "Hide", "Spread", 1, QuizCategories.Stay_safe_online);
        addQuestion(q10);

        QuizQuestions q11 = new QuizQuestions("Which of these is NOT a common technique involved in Social Engineering Attacks?", "Baiting", "Phishing", "Zoomware", "Scareware", 3, QuizCategories.Social_engineering_attacks);
        addQuestion(q11);
        QuizQuestions q12 = new QuizQuestions("In the Social Engineering Lifestyle, what does an attacker typically do?", "They randomly pick a victim", "They identify them with no set vision", "They follow a series of steps to identify and then attack", "They follow steps to become best friends", 3, QuizCategories.Social_engineering_attacks);
        addQuestion(q12);
        QuizQuestions q13 = new QuizQuestions("Which of these is NOT part of the Social Engineering Life Cycle?", "Investigation", "Bait", "Exit", "Hook", 2, QuizCategories.Social_engineering_attacks);
        addQuestion(q13);
        QuizQuestions q14 = new QuizQuestions("In deceiving the victim(s), the attacker usually…", "Takes control of the interaction", "Hides from the victim", "Does not engage that target", "Hacks the pentagon", 1, QuizCategories.Social_engineering_attacks);
        addQuestion(q14);
        QuizQuestions q15 = new QuizQuestions("In preparing for the attack, the attacker usually..", "Has no attacking method", "Identifies and gathers information about the victim", "Introduces themselves to the victim", "Immediately hacks the victim", 2, QuizCategories.Social_engineering_attacks);
        addQuestion(q15);

        QuizQuestions q16 = new QuizQuestions("Psychology based attacks consist of what elements?", "Emotional and cognitive", "Behavioural and cognitive", "Behavioural and emotional", "Cognitive and disruptive", 2, QuizCategories.Psychology_based_attacks);
        addQuestion(q16);
        QuizQuestions q17 = new QuizQuestions("There are how many ‘elements’ that make up psychology-based attacks?", "9", "2", "0", "6", 4, QuizCategories.Psychology_based_attacks);
        addQuestion(q17);
        QuizQuestions q18 = new QuizQuestions("Which of these is an element of psychology-based attacks?", "Disliking", "Social proof", "Mismanagement", "Picture in picture", 2, QuizCategories.Psychology_based_attacks);
       addQuestion(q18);
        QuizQuestions q19 = new QuizQuestions("Which of these is NOT an element of psychology-based attacks?", "Scarcity", "Authority", "Liking", "Social connection", 4, QuizCategories.Psychology_based_attacks);
        addQuestion(q19);
        QuizQuestions q20 = new QuizQuestions("Finish the element “Consistency & ____", "Commitment", "Creativity", "Connection", "Continuation", 1, QuizCategories.Psychology_based_attacks);
        addQuestion(q20);

        QuizQuestions q21 = new QuizQuestions("Which of these resembles a strong password?", "Password1", "paSSword21", "TitAnS2045!", "titan$", 3, QuizCategories.Best_practices);
        addQuestion(q21);
        QuizQuestions q22 = new QuizQuestions("What can be added to help further strengthen your password?", "Two-factor authentication", "Lock and key", "Writing down your password", "Covering your password when entering it", 1, QuizCategories.Best_practices);
        addQuestion(q22);
        QuizQuestions q23 = new QuizQuestions("What is a reasonable length for a password?", "99 characters", "1 character", "8-10 characters", "15-20 characters", 3, QuizCategories.Best_practices);
        addQuestion(q23);
        QuizQuestions q24 = new QuizQuestions("Where should you avoid storing passwords?", "Sticky note on monitor", "Password manager", "Locked document on your personal device", "Private manager on your phone", 1, QuizCategories.Best_practices);
        addQuestion(q24);
        QuizQuestions q25 = new QuizQuestions("What should you steer clear from for passwords?", "Symbols", "Personal Information", "Combination of numbers and letters", "Non-dictionary words", 2, QuizCategories.Best_practices);
        addQuestion(q25);

        QuizQuestions q26 = new QuizQuestions("Which of these is NOT a Physical Security breach?", "Shoulder Surfing", "Frog Hopping", "Tailgating", "Dumpster Diving", 2, QuizCategories.Physical_security);
        addQuestion(q26);
        QuizQuestions q27 = new QuizQuestions("What can easily counter Shoulder Surfing", "A password manager", "Showing someone your phone", "Yelling out your password", "Typing your password quickly", 1, QuizCategories.Physical_security);
        addQuestion(q27);
        QuizQuestions q28 = new QuizQuestions("What can Dumpster Diving reveal about an organisation?", "What is in their rubbish", "Information about a computer network", "Desktop images", "Storage habits", 2, QuizCategories.Physical_security);
        addQuestion(q28);
        QuizQuestions q29 = new QuizQuestions("What can prevent Tailgating", "Having no doors", "Shaking the security guards’ hand", "Biometrics with multifactor authentication", "Presenting loyalty cards upon entrance", 3, QuizCategories.Physical_security);
        addQuestion(q29);
        QuizQuestions q30 = new QuizQuestions("When is Shoulder Surfing most dangerous?", "When employees stand in an open field", "When one employee is alone in the office", "On cramped public transport", "When no one is in the office", 3, QuizCategories.Physical_security);
        addQuestion(q30);
    }

    //insert questions
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

    //get all categories
    public List<QuizCategories> getAllCategories(){
        List<QuizCategories> categoriesList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+ QuizContract.CategoryTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                QuizCategories categories = new QuizCategories();
                categories.setId(c.getInt(c.getColumnIndex(QuizContract.CategoryTable._ID)));
                categories.setName(c.getString(c.getColumnIndex(QuizContract.CategoryTable.COLUMN_NAME)));
                categoriesList.add(categories);
            } while (c.moveToNext());
        }
        c.close();
        return  categoriesList;

    }

    //get questions
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

    //get questions with categories
    public ArrayList<QuizQuestions> getQuestions (int catID){
        ArrayList<QuizQuestions> quizQuestionsArrayList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuizContract.QuestionTable.COLUMN_ID + " = ? ";
        String [] selectionArgs = new String []{String.valueOf(catID)};

        Cursor c = db.query(QuizContract.QuestionTable.TABLE_NAME, null, selection, selectionArgs, null, null, null);


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
                quizQuestionsArrayList.add(questions);

            } while (c.moveToNext());
        }

        c.close();
        return quizQuestionsArrayList;
    }
}
