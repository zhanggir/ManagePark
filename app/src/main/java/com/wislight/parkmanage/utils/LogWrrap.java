package com.wislight.parkmanage.utils;

import android.support.compat.BuildConfig;
import android.util.Log;

/**
 * Created by Administrator on 2017/6/14
 * @author zzx
 * expalin:日志工具类
 */

public class LogWrrap {
    /**
     * is or not debug
     */
    private static final boolean isDeBug = true;

    /**
     * print verbose message
     *
     * @param tag tag
     * @param msg message
     */
    public static void v(String tag, String msg) {
        if (BuildConfig.DEBUG && isDeBug)
            Log.v(tag, msg);
    }

    /**
     * print debug message
     *
     * @param tag tag
     * @param msg message
     */
    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG && isDeBug)
            Log.d(tag, msg);
    }

    /**
     * print info message
     *
     * @param tag tag
     * @param msg message
     */
    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG && isDeBug)
            Log.i(tag, msg);
    }

    /**
     * print warn message
     *
     * @param tag tag
     * @param msg message
     */
    public static void w(String tag, String msg) {
        if (BuildConfig.DEBUG && isDeBug)
            Log.w(tag, msg);
    }

    /**
     * print error message
     *
     * @param tag tag
     * @param msg message
     */
    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG && isDeBug)
            Log.e(tag, msg);
    }
}
