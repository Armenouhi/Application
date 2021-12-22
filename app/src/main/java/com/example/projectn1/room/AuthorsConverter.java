package com.example.projectn1.room;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class AuthorsConverter {

    @TypeConverter
    public static List<Authors> toList(String list) {
        Type type = new TypeToken<List<Authors>>(){}.getType();

        Gson gson = new Gson();
        Object o = gson.fromJson(list, type);
        return (List<Authors>) o;
    }

    @TypeConverter
    public static String toStr(List<Authors> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
