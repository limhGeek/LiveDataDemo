package com.yinshan.livedatademo.http;

import com.lzy.okgo.convert.Converter;
import com.yinshan.livedatademo.bean.Result;
import com.yinshan.livedatademo.utils.GsonUtils;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author limh
 * @function
 * @date 2019/5/28 18:15
 */
public class ResultConverter implements Converter<Result> {
    @Override
    public Result convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) return null;
        return GsonUtils.str2Bean(body.string(), Result.class);
    }
}
