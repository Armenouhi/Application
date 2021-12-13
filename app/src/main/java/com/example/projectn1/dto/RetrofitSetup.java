package com.example.projectn1.dto;

import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSetup {
    private static final String BASE_URL = "https://api.pexels.com/";

    public  static retrofit2.Retrofit initRetrofit() {


        retrofit2.Retrofit.Builder builder = new retrofit2.Retrofit.Builder();
        builder.baseUrl(BASE_URL);
        builder.client(getOkHttpClient());
        builder.addConverterFactory(GsonConverterFactory.create());

        return builder.build();
    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();
        okhttpBuilder.addInterceptor(new TokenInterceptor());
        return okhttpBuilder.build();
    }
}
