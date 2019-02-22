package com.swx.jetpackxbasic.db;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import androidx.room.TypeConverter;

/**
 * Created by swx on 2019/2/22.
 * Mail: dlut_frank@163.com
 * Copyright (c) 2019 .
 */
public class Converters {
    @TypeConverter
    public static List<String> fromJsonString(String jsonString) {
        List<String> result = new ArrayList<>();
        if(jsonString != null && !jsonString.isEmpty()) {
            Gson gson = new Gson();
            result = gson.fromJson(jsonString, new TypeToken<List<String>>(){}.getType());
        }
        return  result;
    }

    @TypeConverter
    public static String toJsonString(List<String> list) {
        if(list != null) {
            Gson gson = new Gson();
            return gson.toJson(list);
        }
        return "";
    }
}
