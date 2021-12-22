package com.example.projectn1.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "image_authors")
public class AuthorsWithImage {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "full_name")
    String firstname;
    @ColumnInfo(name = "image_url")
    String url;

    public AuthorsWithImage(int id, String firstname, String url) {
        this.id = id;
        this.firstname = firstname;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
