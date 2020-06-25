package com.example.cyberseced;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PostEntity {
    @PrimaryKey
    @NonNull
    public int postID;
    public String PostContent;

    PostEntity(int postID, String PostContent) {
        this.postID = postID;
        this.PostContent = PostContent;
    }

    public int getPostID() {
        return postID;
    }


    public String getPostContent() {
        return PostContent;
    }

    public void setPostContent(String PostContent) {
        this.PostContent = PostContent;
    }
}
