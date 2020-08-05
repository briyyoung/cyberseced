package com.example.cyberseced;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Modules {

    private String name;
    private int mImage;
    private int description;
    private int graphic;

    //getters and setters
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

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public int getGraphic() {
        return graphic;
    }

    public void setGraphic(int graphic) {
        this.graphic = graphic;
    }

    //constructor
    public Modules(String name, int mImage, int description, int graphic) {
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

    //fill in data for modules
    public static ArrayList<Modules> getModules() {
        ArrayList<Modules> modules = new ArrayList<>();
        modules.add(new Modules("Social Engineering Attacks", 1, 1, R.drawable.graphic1));
        modules.add(new Modules("Psychology Based Attacks", 2, 2, R.drawable.graphic2));
        modules.add(new Modules("Stay safe online", 3, 3, R.drawable.graphic3));
        modules.add(new Modules("Work from Home", 4, 4, R.drawable.graphic4));
        modules.add(new Modules("Best practices", 5, 5, R.drawable.graphic5));
        modules.add(new Modules("Physical Security", 6,6, R.drawable.graphic6));

        return modules;
    }

}
