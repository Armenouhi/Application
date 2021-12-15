package com.example.projectn1.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Video {
    @SerializedName("id")
    int id;

    @SerializedName("width")
    int width;

    @SerializedName("height")
    int height;

    @SerializedName("url")
    String url;

    @SerializedName("image")
    String image;

    @SerializedName("duration")
    int duration;

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public int getDuration() {
        return duration;
    }


}
