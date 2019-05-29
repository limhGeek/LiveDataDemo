package com.yinshan.livedatademo.http;

import android.app.Activity;
import android.os.Build;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.hjq.toast.ToastUtils;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yinshan.livedatademo.AppConfig;
import com.yinshan.livedatademo.BuildConfig;
import com.yinshan.livedatademo.bean.Result;
import com.yinshan.livedatademo.utils.GsonUtils;
import com.yinshan.livedatademo.utils.Logs;
import com.yinshan.livedatademo.utils.NetworkUtils;
import com.yinshan.livedatademo.utils.SpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * @author limh
 * @function
 * @date 2019/5/29 15:26
 */
public abstract class MyCallBack<T> extends StringCallback {
    private String TAG = "Http";
    private Class<T> clazz;

    public MyCallBack() {
        ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        this.clazz = (Class<T>) type.getActualTypeArguments()[0];
    }

    @Override
    public void onStart(Request<String, ? extends Request> request) {
        super.onStart(request);
        if (!NetworkUtils.isNetworkAvailable()) {
            onNoNetWork();
            return;
        }
        //添加公共请求头
        Activity activity = AppConfig.getInstance().getCurActivity();
        if (null != activity && SpUtils.contains(Comm.SP_TOKEN)) {
            request.headers("Token", (String) SpUtils.get(Comm.SP_TOKEN, ""));
        }
        request.headers("version", BuildConfig.VERSION_NAME);
        request.headers("model", Build.MODEL.replace(" ", "_") + Build.VERSION.RELEASE);
        Logs.d(TAG, "=========================================================");
        Logs.d(TAG, "请求链接：" + request.getMethod() + "  " + request.getBaseUrl());
        Logs.i(TAG, "请求头：" + request.getHeaders().toString());
        Logs.i(TAG, "请求参数：" + request.getParams().toString());
        showLoading();
    }

    @Override
    public void onSuccess(Response<String> response) {
        hideLoading();
        try {
            JSONObject object = new JSONObject(response.body());
            int code = object.optInt("code");
            if (code != 0) {
                onFailure(object.optString("msg"));
            } else {
                onSuccess(GsonUtils.str2Bean(object.optString("data"), clazz));
            }
        } catch (JSONException e) {
            Logs.d(TAG, "onSuccess:" + e.getMessage());
            onFailure("解析错误");
        }
    }

    @Override
    public void onError(Response<String> response) {
        super.onError(response);
        hideLoading();
        Logs.i(TAG, "请求异常信息：" + response.getException().getMessage());
        Logs.d(TAG, "=========================================================");
        if (null == response.getException()) return;
        Throwable e = response.getException();
        String message;
        if (e instanceof UnknownHostException) {
            message = "没有网络";
        } else if (e instanceof HttpException) {
            message = "网络错误";
        } else if (e instanceof SocketTimeoutException) {
            message = "网络连接超时";
        } else if (e instanceof JsonParseException
                || e instanceof JSONException) {
            message = "解析错误";
        } else if (e instanceof ConnectException) {
            message = "连接失败";
        } else if (e instanceof ServerException) {
            message = ((ServerException) e).message;
        } else {
            message = "未知错误";
        }
        onFailure(message);
    }

    protected void showLoading() {
    }

    protected void hideLoading() {
    }

    protected void onNoNetWork() {
    }

    /**
     * success
     *
     * @param result
     */
    public abstract void onSuccess(T result);

    /**
     * failure
     *
     * @param msg
     */
    public void onFailure(String msg) {
        ToastUtils.show(msg);
    }
}
