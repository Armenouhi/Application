package com.example.projectn1.room;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class AuthorsConverter {

    @TypeConverter
    public static List<AuthorsWithImage> toList(String list) {
        Type type = new TypeToken<List<AuthorsWithImage>>(){}.getType();

        return new Gson().fromJson(list, type);
    }

    @TypeConverter
    public static String toStr(List<AuthorsWithImage> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
