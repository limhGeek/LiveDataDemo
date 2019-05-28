package com.yinshan.livedatademo.repository;

import android.app.Activity;

import com.hjq.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.yinshan.livedatademo.AppConfig;
import com.yinshan.livedatademo.bean.Result;
import com.yinshan.livedatademo.http.Comm;
import com.yinshan.livedatademo.http.ResultCallBack;
import com.yinshan.livedatademo.mvvm.AbsRepository;
import com.yinshan.livedatademo.utils.Logs;
import com.yinshan.livedatademo.utils.SpUtils;
import com.yinshan.livedatademo.widget.LoadingDialog;

/**
 * @author limh
 * @function
 * @date 2019/5/28 14:41
 */
public class UserRepository extends AbsRepository {
    private String TAG = this.getClass().getSimpleName();

    private LoadingDialog loadingDialog;

    public void login(String username, String password) {

        OkGo.<Result>post(Comm.URL_LOGIN)
                .params("phone", username)
                .params("password", password)
                .execute(new ResultCallBack() {
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
                    public void onSuccess(Result t) {
                        if (null != loadingDialog && loadingDialog.isShowing()) {
                            loadingDialog.dismiss();
                        }
                        if (0 != t.getCode()) {
                            ToastUtils.show(t.getMsg());
                        } else {
                            SpUtils.put(AppConfig.getInstance().getCurActivity(), Comm.SP_TOKEN, t.getData().toString());
                            postValue(t);
                        }
                    }
                });
    }

    public void getUserInfo() {
        String token = (String) SpUtils.get(AppConfig.getInstance().getCurActivity(), Comm.SP_TOKEN, "");
        OkGo.<Result>get(Comm.URL_USERINFO)
                .headers("Token", token)
                .execute(new ResultCallBack() {
                    @Override
                    public void onSuccess(Result t) {
                        Logs.d(TAG, "USER=" + t.toString());
                    }
                });
    }
}
