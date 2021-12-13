package com.example.projectn1.dto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Videos {
    @GET("/v1/search")
    public Call<SearchVideos> searchVideo(@Query("query")String param);

    static Videos create(){
        return RetrofitSetup.initRetrofit().create(Videos.class);
    }
}
