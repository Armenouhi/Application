package com.example.projectn1.dto;

import com.google.gson.annotations.SerializedName;

public class Src {
    @SerializedName("original")
    String original;

    @SerializedName("medium")
    String mediumUrl;

    @SerializedName("small")
    String smallUrl;

    @SerializedName("large")
    String largeUrl;

    @SerializedName("large2x")
    String large2xUrl;


    @SerializedName("portrait")
    String portraitUrl;

    @SerializedName("landscape")
    String landscapeUrl;

    public String getOriginal() {
        return original;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public String getLargeUrl() {
        return largeUrl;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public String getLandscapeUrl() {
        return landscapeUrl;
    }

    public String getMediumUrl() {
        return mediumUrl;
    }

    public String getLarge2xUrl() { return large2xUrl; }
}
