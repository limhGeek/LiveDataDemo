package com.yinshan.livedatademo.repository;

import android.app.Activity;

import com.hjq.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.yinshan.livedatademo.AppConfig;
import com.yinshan.livedatademo.bean.Token;
import com.yinshan.livedatademo.bean.User;
import com.yinshan.livedatademo.http.Comm;
import com.yinshan.livedatademo.http.Geek;
import com.yinshan.livedatademo.http.ResultCallback;
import com.yinshan.livedatademo.mvvm.AbsRepository;
import com.yinshan.livedatademo.utils.Logs;
import com.yinshan.livedatademo.utils.SpUtils;
import com.yinshan.livedatademo.widget.LoadingDialog;

import java.util.HashMap;
import java.util.Map;

/**
 * @author limh
 * @function
 * @date 2019/5/28 14:41
 */
public class UserRepository extends AbsRepository {
    private String TAG = this.getClass().getSimpleName();

    private LoadingDialog loadingDialog;

    public void login(String username, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("phone", username);
        params.put("password", password);
        Geek.post(Comm.URL_LOGIN, params, new ResultCallback<Token>() {
            @Override
            protected void showLoading() {
                super.showLoading();
                Activity activity = AppConfig.getInstance().getCurActivity();
                if (null != activity) {
                    loadingDialog = new LoadingDialog.Builder(activity)
                            .setMsg("登录中...")
                            .create();
                    loadingDialog.show();
                }
            }

            @Override
            protected void hideLoading() {
                super.hideLoading();
                if (null != loadingDialog && loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
            }

            @Override
            public void onSuccess(Token token) {
                SpUtils.put(Comm.SP_TOKEN, token.getToken());
                Logs.d(TAG, "Token=" + token.getToken());
                postValue(token);
            }
        });
    }

    public void getUserInfo() {
        Geek.get(Comm.URL_USERINFO, new ResultCallback<User>() {
            @Override
            public void onSuccess(User result) {
                postValue(result);
            }
        });
    }
}
