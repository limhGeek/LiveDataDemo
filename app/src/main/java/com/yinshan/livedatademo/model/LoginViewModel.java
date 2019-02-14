package com.yinshan.livedatademo.model;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.yinshan.livedatademo.bean.Result;
import com.yinshan.livedatademo.bean.User;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author limh
 * @function
 * @date 2019/2/14 11:20
 */
public class LoginViewModel extends ViewModel {

    private String TAG = "LoginViewModel";

    private MutableLiveData<Result<User>> result;
    private Handler handler = new Handler(Looper.getMainLooper());

    public LiveData<Result<User>> getModel() {
        if (null == result) {
            result = new MutableLiveData<>();
        }
        return result;
    }

    public void login(final String name, final String password) {
        Log.i(TAG, "----开始登录：name=" + name + "  password=" + password);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "----登录完成-----");
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
                    result.setValue(new Result<User>(0, "用户名或密码不能为空"));
                } else {
                    result.setValue(new Result<User>(0, "登录成功"));
                }
            }
        }, 2000);
    }
}
