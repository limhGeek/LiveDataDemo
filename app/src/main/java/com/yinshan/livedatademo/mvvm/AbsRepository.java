package com.yinshan.livedatademo.mvvm;


import androidx.lifecycle.MutableLiveData;

/**
 * @author limh
 * @function
 * @date 2019/5/27 9:40
 */
public abstract class AbsRepository {


    MutableLiveData<Object> retData;

    public AbsRepository() {
        retData = new MutableLiveData<>();
    }

    protected void postValue(Object obj) {
        if (null != retData) {
            retData.postValue(obj);
        }
    }
}
