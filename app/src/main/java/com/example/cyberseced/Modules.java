package com.example.cyberseced;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Modules {

    private int mImage;
    private String name;


    public Modules(int image, String text1) {
        mImage = image;
        name = text1;

    }

    public int getmImage() {
        return mImage;
    }


    public String getName() {
        return name;
    }

    public static Modules getModules(String name){
        for (Modules modules : getModules()){
            if(modules.getName().equals(name)){
                return modules;
            }
        }
        return null;
    }

    public static ArrayList<Modules> getModules(){
        ArrayList<Modules> modules = new ArrayList<>();
        modules.add(new Modules(R.drawable.cyber, "Social Engineering Attacks"));
        modules.add(new Modules(R.drawable.cyber, "Psychology Based Attacks"));
        modules.add(new Modules(R.drawable.cyber, "How to stay safe online"));
        modules.add(new Modules(R.drawable.cyber, "Work from Home: How to secure your remote/home workplace"));
        modules.add(new Modules(R.drawable.cyber, "Best practices - Passwords"));
        modules.add(new Modules(R.drawable.cyber, "Physical Security – Shoulder Surfing, Tailgating, Dumpster Diving"));

        return modules;
    }

}
