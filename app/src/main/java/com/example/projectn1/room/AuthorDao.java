package com.example.projectn1.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AuthorDao {
    @Query("SELECT * FROM image_authors")
    List<AuthorsWithImage> getAuthors();

    @Insert
    void insert (AuthorsWithImage image_authors);

    @Insert
    void insertAll(List<AuthorsWithImage> posts);

    @Delete
    void delete (AuthorsWithImage image_authors);
}
