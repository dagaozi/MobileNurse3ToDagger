package com.wyyy.corelibrary.net;


import com.wyyy.corelibrary.model.HttpResult;

import rx.functions.Func1;

/**
 * Created by haohaibin .（dagaozi@163.com）
 * 创建时间：2016/7/6 11:08
 * 类描述：
 */
public class HttpResultFunc<T> implements Func1<HttpResult<T>,T>
{
    @Override
    public T call(HttpResult<T> httpResult) {
        if(httpResult.getCode()!=0){
            throw new ApiException(httpResult.getCode());
        }
        return httpResult.getData();
    }
}