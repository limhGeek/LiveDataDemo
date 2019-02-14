package com.yinshan.livedatademo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yinshan.livedatademo.bean.Result;
import com.yinshan.livedatademo.bean.User;
import com.yinshan.livedatademo.model.LoginViewModel;
import com.yinshan.livedatademo.widget.LoadingDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private EditText editName;
    private EditText editPass;
    private TextView btnLogin;
    private LoadingDialog loadingDialog;
    private LoginViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        loadingDialog = new LoadingDialog.Builder(MainActivity.this)
                .setBgDark(true)
                .setMsg("登录中...")
                .create();
        model = ViewModelProviders.of(this).get(LoginViewModel.class);
        model.getModel().observe(this, new Observer<Result<User>>() {
            @Override
            public void onChanged(Result<User> userResult) {
                if (null != loadingDialog && loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
                Toast.makeText(MainActivity.this, userResult.getMsg(), Toast.LENGTH_SHORT).show();
            }
        });
        editName = findViewById(R.id.editName);
        editPass = findViewById(R.id.editPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(editName.getText().toString(), editPass.getText().toString());
            }
        });
    }

    private void login(String name, String password) {
        if (null != loadingDialog && !loadingDialog.isShowing()) {
            loadingDialog.show();
        }
        model.login(name, password);
    }

}
