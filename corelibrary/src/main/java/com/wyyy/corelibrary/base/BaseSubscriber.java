package com.wyyy.corelibrary.base;

import rx.Subscriber;

/**
 * Created by haohaibin .（dagaozi@163.com）
 * 创建时间：2016/7/26 15:29
 * 类描述：
 */
public class BaseSubscriber<T> extends Subscriber<T> {
    private  IBaseSubscriber iBaseSubscriber;
    private int flag;

    public BaseSubscriber(IBaseSubscriber iBaseSubscriber,int flag) {
        this.iBaseSubscriber = iBaseSubscriber;
        this.flag=flag;
    }

    /*  @Override
      public void onStart() {
          iBaseSubscriber.preStart();
      }*/
    @Override
    public void onNext(T t) {
        iBaseSubscriber.onNext(t,flag);
    }

    @Override
    public void onCompleted() {
        iBaseSubscriber.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        iBaseSubscriber.onError(e);
    }
}
