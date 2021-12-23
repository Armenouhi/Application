package com.example.projectn1.home;

import java.util.ArrayList;
import java.util.List;

public class Image {
    int user;
    String fullName;
    String imageUrl;
    int i;

    public Image(String fullName, String imageUrl) {
        this.fullName = fullName;
        this.imageUrl = imageUrl;
    }

    public Image(int user, String s1, String s2, String largeUrl, int i) {
        this.user = user; fullName = s1 + s2; imageUrl = largeUrl; this.i = i;
    }

    public Image(int photographer, String s, String largeUrl, int i) {
        user = photographer; fullName = s;
        imageUrl = largeUrl; this.i = i;
    }

    public String getFullName() {
        return fullName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public static List<Image> getImages() {
        ArrayList<Image> imgList = new ArrayList<>();

        imgList.add(new Image(
                "Հունոտի Կիրճ",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTpj4zCmVYyto3gFkvt9KxXyn8Yu8KujSNEIQ&usqp=CAU"
        ));

        imgList.add(new Image(
                "Շաքիի ջրվեժ",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4a/Hovhannes_Aivazovsky_-_The_Ninth_Wave_-_Google_Art_Project.jpg/350px-Hovhannes_Aivazovsky_-_The_Ninth_Wave_-_Google_Art_Project.jpg"
        ));

        imgList.add(new Image(
                "Հունոտի Կիրճ",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTpj4zCmVYyto3gFkvt9KxXyn8Yu8KujSNEIQ&usqp=CAU"
        ));

        return imgList;
    }
}
