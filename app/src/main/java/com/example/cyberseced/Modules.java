package com.example.cyberseced;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Modules {

    private String name;
    private int mImage;
    private String description;
    private int graphic;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGraphic() {
        return graphic;
    }

    public void setGraphic(int graphic) {
        this.graphic = graphic;
    }


    public Modules(String name, int mImage, String description, int graphic) {
        this.name = name;
        this.mImage = mImage;
        this.description = description;
        this.graphic = graphic;

    }

    public static Modules getModules(String name) {
        for (Modules modules : getModules()) {
            if (modules.getName().equals(name)) {
                return modules;
            }
        }
        return null;
    }

    public static ArrayList<Modules> getModules() {
        ArrayList<Modules> modules = new ArrayList<>();
        modules.add(new Modules("Social Engineering Attacks", 1, "", R.drawable.graphic1));
        modules.add(new Modules("Psychology Based Attacks", 2, "", R.drawable.graphic2));
        modules.add(new Modules("How to stay safe online", 3, " ", R.drawable.graphic3));
        modules.add(new Modules("Work from Home: How to secure your remote/home workplace", 4, " ", R.drawable.graphic4));
        modules.add(new Modules("Best practices - Passwords", 5, " ", R.drawable.graphic5));
        modules.add(new Modules("Physical Security – Shoulder Surfing, Tailgating, Dumpster Diving", 6, " ", R.drawable.graphic6));

        return modules;
    }

}
