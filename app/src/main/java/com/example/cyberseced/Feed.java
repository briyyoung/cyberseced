package com.example.cyberseced;

/***************************************************************************************
 *    REFERNCES
 *
 *    Title: Blogzone
 *    Author: kenny-io
 *    Date: 20/7/20
 *    Code version: 29 Nov 2017
 *    Availability: https://medium.com/@peterekeneeze/build-a-simple-blog-app-with-firebase-in-android-studio-b6482275408
 *    Availability: https://github.com/kenny-io/Blogzone
 *
 ***************************************************************************************/

public class Feed {

    private String title, desc;

    //constructor for feed activity
    public Feed(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public Feed() {
    }

    //setters and getters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

}
