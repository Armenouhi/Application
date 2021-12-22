package com.example.projectn1.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {
        Authors.class
}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME  = "authors_db";
    private static volatile AppDatabase INSTANCE;

    public abstract AuthorDao getAuthorsDao();
    public static synchronized AppDatabase getInstance(Context context) {

        if (INSTANCE == null) {
            Builder<AppDatabase> appDatabaseBuilder = Room.databaseBuilder(
                    context,
                    AppDatabase.class,
                    DB_NAME
            );

            INSTANCE = appDatabaseBuilder
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
            return INSTANCE;

        } else {
            return INSTANCE;
        }
    }
}