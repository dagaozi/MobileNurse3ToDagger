package com.wyyy.corelibrary.base;

/**
 * Created by haohaibin .（dagaozi@163.com）
 * 创建时间：2016/7/26 15:30
 * 类描述：
 */
public interface IBaseSubscriber<T> {
    // void preStart();
    void onNext(T t, int flag);

    void onCompleted();

    void onError(Throwable e);
}
