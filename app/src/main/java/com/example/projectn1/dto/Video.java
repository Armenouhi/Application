package com.example.projectn1.dto;

import com.google.gson.annotations.SerializedName;

public class Video {
    @SerializedName("id")
    int id;

    @SerializedName("width")
    int width;

    @SerializedName("height")
    int height;

    @SerializedName("src")
    Src src;

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Src getSrc() {
        return src;
    }
}
