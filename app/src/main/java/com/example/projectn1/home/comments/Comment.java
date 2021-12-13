package com.example.projectn1.home.comments;

import com.example.projectn1.profile.images.Images;

import java.util.ArrayList;
import java.util.List;

public class Comment {
    String text;

    public Comment(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static List<Comment> getImages() {
        ArrayList<Comment> imgList = new ArrayList<>();

        imgList.add(new Comment(
                "Comment 1"
        ));

        imgList.add(new Comment(
                "Comment 2"
        ));

        imgList.add(new Comment(
                "Comment 3"
        ));

        imgList.add(new Comment(
                "Comment 4"
        ));

        imgList.add(new Comment(
                "Comment 5"
        ));
        return imgList;
    }
}
