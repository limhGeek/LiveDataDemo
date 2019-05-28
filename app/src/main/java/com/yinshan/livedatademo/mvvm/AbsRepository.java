package com.yinshan.livedatademo.mvvm;


import androidx.lifecycle.MutableLiveData;

import com.yinshan.livedatademo.bean.Result;

/**
 * @author limh
 * @function
 * @date 2019/5/27 9:40
 */
public abstract class AbsRepository {


    public MutableLiveData<Result> retData;

    public AbsRepository() {
        retData = new MutableLiveData<>();
    }

    protected void postValue(Result obj) {
        if (null != retData) {
            retData.postValue(obj);
        }
    }
}
