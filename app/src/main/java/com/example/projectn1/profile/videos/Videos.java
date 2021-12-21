package com.example.projectn1.profile.videos;

import com.example.projectn1.R;
import com.example.projectn1.dto.VideoFiles;

import java.util.ArrayList;
import java.util.List;

public class Videos {
    String imgUrl;
    String videoUrl;

    public Videos(String imgUrl, String videoUrl, int icon) {
        this.imgUrl = imgUrl;
        this.videoUrl = videoUrl;
    }

    public Videos(String image, String videoFiles) {
        this.imgUrl = image;
        this.videoUrl = videoFiles;
    }


    public String getImgUrl() {
        return imgUrl;
    }


    public String getVideoUrl() {
        return videoUrl;
    }

    public static List<Videos> getVideos() {
        ArrayList<Videos> videoList = new ArrayList<>();
        videoList.add(new Videos(
                "https://media.kidsacademy.mobi/files/LMS%20worksheets/ESL/Coloring%20Pages/images/esl_u1ch1l3_l1purple_orange.jpg",
                "https://media.kidsacademy.mobi/files/LMS%20worksheets/ESL/Coloring%20Pages/images/esl_u1ch1l3_l1purple_orange.jpg"
        ));
        videoList.add(new Videos(
                "https://ecdn.teacherspayteachers.com/thumbitem/COLOR-THEORY-Tertiary-colors-5373416-1586518442/original-5373416-1.jpg",
                "https://ecdn.teacherspayteachers.com/thumbitem/COLOR-THEORY-Tertiary-colors-5373416-1586518442/original-5373416-1.jpg"
        ));
        videoList.add(new Videos(
                "https://i.pinimg.com/originals/b9/62/1b/b9621bd4f83beb6ea71cff5dbacda2a9.jpg",
                "https://i.pinimg.com/originals/b9/62/1b/b9621bd4f83beb6ea71cff5dbacda2a9.jpg"
        ));
        videoList.add(new Videos(
                "https://files.liveworksheets.com/def_files/2021/4/21/1042116280754405/1042116280754405001.jpg",
                "https://files.liveworksheets.com/def_files/2021/4/21/1042116280754405/1042116280754405001.jpg"
        ));

        videoList.add(new Videos(
                "https://media.kidsacademy.mobi/files/LMS%20worksheets/ESL/Coloring%20Pages/images/esl_u1ch1l3_l1purple_orange.jpg",
                "https://media.kidsacademy.mobi/files/LMS%20worksheets/ESL/Coloring%20Pages/images/esl_u1ch1l3_l1purple_orange.jpg"
        ));
        videoList.add(new Videos(
                "https://ecdn.teacherspayteachers.com/thumbitem/COLOR-THEORY-Tertiary-colors-5373416-1586518442/original-5373416-1.jpg",
                "https://ecdn.teacherspayteachers.com/thumbitem/COLOR-THEORY-Tertiary-colors-5373416-1586518442/original-5373416-1.jpg"
        ));

        return videoList;
    }
}
