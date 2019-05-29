package com.yinshan.livedatademo.http;

import com.lzy.okgo.OkGo;

import java.util.Map;

/**
 * @author limh
 * @function
 * @date 2019/5/29 16:30
 */
public class Geek {

    public static void post(String url, Map<String, String> params, ResultCallback<?> callback) {
        OkGo.<String>post(url).params(params).execute(callback);
    }

    public static void post(String url, ResultCallback<?> callback) {
        OkGo.<String>post(url).execute(callback);
    }

    public static void get(String url, Map<String, String> params, ResultCallback<?> callback) {
        OkGo.<String>get(url).params(params).execute(callback);
    }

    public static void get(String url, ResultCallback<?> callback) {
        OkGo.<String>get(url).execute(callback);
    }
}
