package com.yinshan.livedatademo;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.hjq.toast.ToastUtils;
import com.yinshan.livedatademo.bean.Token;
import com.yinshan.livedatademo.bean.User;
import com.yinshan.livedatademo.model.UserViewModel;

public class MainActivity extends BaseActivity<UserViewModel> {

    private String TAG = "MainActivity";
    private EditText editName;
    private EditText editPass;
    private TextView btnLogin;

    @Override
    void findView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    void initViews() {

        editName = findViewById(R.id.editName);
        editPass = findViewById(R.id.editPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(editName.getText().toString(), editPass.getText().toString());
            }
        });
        viewModel.getModel().observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object result) {
                if (result instanceof Token) {
                    ToastUtils.show("登录成功");
                    //获取用户信息
                    viewModel.getUserInfo();
                } else if (result instanceof User) {
                    ToastUtils.show("用户信息数据：" + result.toString());
                }
            }
        });
    }

    @Override
    void initDatas() {

    }

    /**
     * 开始登录
     * @param name 用户名
     * @param password 密码
     */
    private void login(String name, String password) {
        viewModel.login(name, password);
    }

}
