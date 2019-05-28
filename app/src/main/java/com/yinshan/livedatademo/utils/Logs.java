package com.yinshan.livedatademo.utils;

import android.util.Log;

import com.yinshan.livedatademo.BuildConfig;


/**
 * Android自带log打印工具
 * log打印工具类
 */
public class Logs {
    private static boolean debug = BuildConfig.DEBUG;

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        Logs.debug = debug;
    }

    private static final String TAG_I = "====TAG_I====";
    private static final String TAG_D = "====TAG_D====";
    private static final String TAG_E = "====TAG_E====";
    private static final String TAG_W = "====TAG_W====";
    private static final String TAG_V = "====TAG_V====";

    public static void i(String TAG, String msg) {
        if (isDebug()) {
            Log.i(TAG, null == msg ? "null" : msg);
        }
    }

    public static void i(String msg) {
        i(TAG_I, null == msg ? "null" : msg);
    }

    public static void d(String TAG, String msg) {
        if (isDebug()) {
            Log.d(TAG, null == msg ? "null" : msg);
        }
    }

    public static void d(String msg) {
        d(TAG_D, null == msg ? "null" : msg);
    }

    public static void e(String TAG, String msg) {
        if (isDebug()) {
            Log.e(TAG, null == msg ? "null" : msg);
        }
    }

    public static void e(String msg) {
        e(TAG_E, null == msg ? "null" : msg);
    }

    public static void w(String TAG, String msg) {
        if (isDebug()) {
            Log.w(TAG, null == msg ? "null" : msg);
        }
    }

    public static void w(String msg) {
        w(TAG_W, null == msg ? "null" : msg);
    }

    public static void v(String TAG, String msg) {
        if (isDebug()) {
            Log.v(TAG, null == msg ? "null" : msg);
        }
    }

    public static void v(String msg) {
        v(TAG_V, null == msg ? "null" : msg);
    }
}
