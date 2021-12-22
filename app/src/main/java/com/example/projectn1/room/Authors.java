package com.example.projectn1.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "authors")
public class Authors {
    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo(name = "firstname")
    String firstname;
    @ColumnInfo(name = "lastname")
    String lastname;
    @ColumnInfo(name = "imageURL")
    String imageUrl;

    public Integer getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
