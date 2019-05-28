package com.yinshan.livedatademo.http;

import com.google.gson.JsonParseException;
import com.hjq.toast.ToastUtils;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yinshan.livedatademo.bean.Result;
import com.yinshan.livedatademo.utils.Logs;
import com.yinshan.livedatademo.utils.NetworkUtils;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * @author limh
 * @function 网络请求回调
 * @date 2019/5/28 18:13
 */
public abstract class ResultCallBack extends AbsCallback<Result> {
    private String TAG = "Http";
    private ResultConverter resultConverter;

    public ResultCallBack() {
        this.resultConverter = new ResultConverter();
    }

    @Override
    public void onStart(Request<Result, ? extends Request> request) {
        super.onStart(request);
        Logs.i(TAG, "请求头：" + request.getHeaders().toString());
        Logs.i(TAG, "请求参数：" + request.getParams().toString());
        if (!NetworkUtils.isNetworkAvailable()) {
            onNoNetWork();
            return;
        }
        showLoading();
    }

    @Override
    public void onError(Response<Result> response) {
        super.onError(response);
        Logs.i(TAG, "请求异常信息：" + response.getException().getMessage());
        if (null == response.getException()) return;
        Throwable e = response.getException();
        String message;
        int code = -1;
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
            code = ((ServerException) e).code;
        } else {
            message = "未知错误";
        }
        onFailure(code, message);
    }

    @Override
    public void onFinish() {
        super.onFinish();
    }

    @Override
    public void onSuccess(Response<Result> response) {
        Logs.i(TAG, "请求结果：" + response.body().toString());
        Result result = response.body();
        if (null == result) return;
        onSuccess(result);
    }

    @Override
    public Result convertResponse(okhttp3.Response response) throws Throwable {
        Result result = resultConverter.convertResponse(response);
        response.close();
        return result;
    }

    protected void showLoading() {

    }

    protected void onNoNetWork() {

    }

    /**
     * success
     *
     * @param result
     */
    public abstract void onSuccess(Result result);

    /**
     * failure
     *
     * @param msg
     */
    public void onFailure(int code, String msg) {
        ToastUtils.show(msg);
    }
}
