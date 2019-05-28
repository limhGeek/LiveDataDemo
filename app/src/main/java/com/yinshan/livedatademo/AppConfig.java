package com.yinshan.livedatademo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.hjq.toast.ToastUtils;
import com.hjq.toast.style.ToastQQStyle;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.lang.ref.WeakReference;
import java.util.Stack;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * @author limh
 * @function
 * @date 2019/5/28 15:01
 */
public class AppConfig extends Application implements Application.ActivityLifecycleCallbacks {
    public static AppConfig mInstance;
    private static Stack<Activity> activities = new Stack<>();

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initHttp();
        //初始化Toast
        ToastUtils.init(this, new ToastQQStyle());
        //管理任务栈
        registerActivityLifecycleCallbacks(this);
    }

    /**
     * 初始化Http
     */
    private void initHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.ALL);
        builder.addInterceptor(loggingInterceptor);
        OkGo.getInstance().setOkHttpClient(builder.build()).init(this);
    }

    public static AppConfig getInstance() {
        return mInstance;
    }

    /**
     * 返回栈顶Activity
     *
     * @return activity
     */
    public Activity getCurActivity() {
        return new WeakReference<>(activities.lastElement()).get();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activities.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activities.remove(activity);
    }
}
