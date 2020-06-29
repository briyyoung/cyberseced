package com.example.cyberseced;

import java.util.ArrayList;

public class Topic {

    private String name;
    private int picture;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    //topic constructor
    public Topic(String name, int picture, String description){
        this.name = name;
        this.picture = picture;
        this.description = description;
    }

    //method that would get you one planet.
    public static Topic getTopic(String name){
        for (Topic topic : getTopics()){
            if(topic.getName().equals(name)){
                return topic;
            }
        }
        return null;
    }

    public static ArrayList<Topic> getTopics(){
        ArrayList<Topic> topics = new ArrayList<>();
        topics.add(new Topic("Social Engineering Attacks",1,
                "Social Engineering attacks are blablabla . . . . ."));
        topics.add(new Topic("Psychology Based Attacks",2,
                "Psychology based attacks could be scam blablabla . . . . ."));
        topics.add(new Topic("How to stay safe online",3,
                "To stay safe online blablabla . . . . ."));
        topics.add(new Topic("Work From Home: Secure remote workplace",4,
                "Work from home guide to stay safe blablabla . . . . ."));
        topics.add(new Topic("Best Practices",5,
                "Best practices to stay safe while work from home blablabla . . . . ."));
        topics.add(new Topic("Physical security",6,
                "Physical security from quit pro quo skrta blablabla . . . . ."));
        return topics;
    }

}

