package com.yinshan.livedatademo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.yinshan.livedatademo.mvvm.AbsViewModel;
import com.yinshan.livedatademo.utils.TUtil;

/**
 * @author limh
 * @function
 * @date 2019/5/27 10:06
 */
public abstract class BaseActivity<T extends AbsViewModel> extends AppCompatActivity {
    public T viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findView();
        viewModel = VMProviders(this, (Class<T>) TUtil.getInstance(this, 0));
        initViews();
        initDatas();
    }

    protected <Q extends ViewModel> Q VMProviders(AppCompatActivity fragment, @NonNull Class modelClass) {

        return (Q) ViewModelProviders.of(fragment).get(modelClass);

    }

    abstract void findView();

    abstract void initViews();

    abstract void initDatas();
}
