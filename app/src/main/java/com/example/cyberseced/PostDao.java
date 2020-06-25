package com.example.cyberseced;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PostDao {
    //Select notes based on its planetName
    @Query("SELECT * FROM PostEntity where postID = :id")
    PostEntity getPostByID(int id);

    @Insert
    void insert(PostEntity... post);

    @Update
    void update(PostEntity... post);

    @Delete
    void delete(PostEntity...post);

}
