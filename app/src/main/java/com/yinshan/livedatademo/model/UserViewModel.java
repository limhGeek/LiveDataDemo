package com.yinshan.livedatademo.model;

import android.app.Application;

import androidx.annotation.NonNull;

import com.yinshan.livedatademo.mvvm.AbsViewModel;
import com.yinshan.livedatademo.repository.UserRepository;

/**
 * @author limh
 * @function
 * @date 2019/2/14 11:20
 */
public class UserViewModel extends AbsViewModel<UserRepository> {

    public UserViewModel(@NonNull Application application) {
        super(application);
    }

    public void login(final String name, final String password) {
        mRepository.login(name, password);
    }

    public void getUserInfo() {
        mRepository.getUserInfo();
    }
}
