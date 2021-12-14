package com.example.projectn1.dto;

import com.google.gson.annotations.SerializedName;

public class Video {
    @SerializedName("id")
    int id;

    @SerializedName("width")
    String width;

    @SerializedName("height")
    String height;

    @SerializedName("src")
    Src src;

    public int getId() {
        return id;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    public Src getSrc() {
        return src;
    }
}
