package com.silence.weixin.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @desc:
 * @author: cao_wencao
 * @date: 2019-09-02 17:13
 */
public class JsonUtils {
    public static String toJson(Object obj) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        return gson.toJson(obj);
    }
}
