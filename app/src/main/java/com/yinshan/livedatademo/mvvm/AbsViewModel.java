package com.yinshan.livedatademo.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.yinshan.livedatademo.utils.TUtil;


/**
 * @author limh
 * @function
 * @date 2019/5/27 9:34
 */
public class AbsViewModel<T extends AbsRepository> extends AndroidViewModel {

    protected T mRepository;

    public MutableLiveData<Object> getModel() {
        return mRepository.retData;
    }

    public AbsViewModel(@NonNull Application application) {
        super(application);
        mRepository = TUtil.getNewInstance(this, 0);
    }
}
