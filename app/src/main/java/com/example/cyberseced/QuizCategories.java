package com.example.cyberseced;
/***************************************************************************************
 *    REFERNCES
 *    Title: SQLite Multiple Choice Quiz - Android Studio Tutorial
 *    Author: Coding in Flow
 *    Date: 10/7/20
 *    Code version: 12/8/2019
 *    Availability: https://www.youtube.com/playlist?list=PLrnPJCHvNZuDCyg4Usq2gHMzz6_CiyQO7
 *
 ***************************************************************************************/
public class QuizCategories {
    public static final int Social_engineering_attacks = 1;
    public static final int Psychology_based_attacks = 2;
    public static final int Stay_safe_online = 3;
    public static final int Work_from_home = 4;
    public static final int Best_practices = 5;
    public static final int Physical_security = 6;


    private int id;
    private String name;

    public QuizCategories(){
    }

    public QuizCategories(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return  getName();
    }
}
