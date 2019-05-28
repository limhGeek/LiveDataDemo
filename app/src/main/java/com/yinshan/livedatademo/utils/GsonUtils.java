package com.yinshan.livedatademo.utils;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class GsonUtils {
    private static Gson gson = null;

    static {
        gson = new Gson();
    }

    private GsonUtils() {
    }

    /**
     * Bean转String
     *
     * @param object Bean
     * @return String Result
     */
    public static String bean2Str(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * String转Bean
     *
     * @param gsonString 要转换的jsonString
     * @param cls        Bean
     * @return 转换结果
     */
    public static <T> T str2Bean(String gsonString, Class<T> cls) {
        T t = null;
        if (null != gson) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * String转List<Bean>
     *
     * @param str 转换的字符串
     * @param cls 实体类型
     * @param <T> 泛型
     * @return list
     */
    public static <T> ArrayList<T> str2List(String str, Class<T> cls) {
        if (TextUtils.isEmpty(str)){
            return null;
        }
        try {
            ArrayList<T> list = new ArrayList<>();
            JSONArray array = new JSONArray(str);
            for (int i = 0; i < array.length(); i++) {
                list.add(str2Bean(array.getString(i), cls));
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * list转String
     *
     * @param allObject Bean
     * @return String Result
     */
    public static String list2Str(List<Object> allObject) {
        JSONArray array = new JSONArray();
        for (Object item : allObject) {
            array.put(bean2Str(item));
        }
        return array.toString();
    }
}