package com.example.cyberseced;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PostEntity.class}, version = 1)
public abstract class PostDatabase extends RoomDatabase {
    public abstract PostDao getPostDao();
}
