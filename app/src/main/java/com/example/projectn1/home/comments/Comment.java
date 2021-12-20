package com.example.projectn1.home.comments;

import com.example.projectn1.profile.images.Images;

import java.util.ArrayList;
import java.util.List;

public class Comment {
    String comment;

    public Comment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public static List<Comment> getComments() {
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
