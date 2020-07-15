package com.example.cyberseced;

public class QuizCategories {
    public static final int Social_Engineering = 1;
    public static final int Psychology_Based = 2;
    public static final int safe_online = 3;
    public static final int Work_from_Home = 4;
    public static final int Best_practices = 5;
    public static final int Physical_Security = 6;


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
}
