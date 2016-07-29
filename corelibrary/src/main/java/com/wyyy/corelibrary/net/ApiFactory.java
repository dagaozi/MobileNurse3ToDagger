package com.wyyy.corelibrary.net;


import com.wyyy.corelibrary.model.HttpResult;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by dagaozi on 2016/3/30.
 * 线程管理、订阅，获取的数据分析、剥离
 */
public class ApiFactory {

    //线程管理、订阅（不优雅方式）
@SuppressWarnings("unchecked")
    protected Subscription toSubscribe1(Observable o, Subscriber s)
    {
     return   o.subscribeOn(Schedulers.io())
            // .unsubscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }
    //线程管理、订阅 (优雅方式，未抽取过滤操作)
    @SuppressWarnings("unchecked")
    protected <T>Subscription toSubscribe2(Observable<T> o, Subscriber s)
    {
        return o.map((Func1<? super T, ? extends T>) new Func1<HttpResult<T>,T>() {
            @Override
            public T call(HttpResult<T> httpResult) {
                if(httpResult.getCode()!=0){
                    throw new ApiException(httpResult.getCode());
                }
                return httpResult.getData();
            }
        }).compose(applySchedulers()).subscribe(s);
    }



    //线程管理、订阅、数据过滤(最终版)
    @SuppressWarnings("unchecked")
    /**
     *
     *Created by 郝海滨（dagaozi@163.com）
     *创建时间 2016/7/19 10:40
     *描述：
     */
    protected <T>Subscription toSubscribe(Observable<T> o, Subscriber s)
    {
        return o.map((Func1<? super T, ? extends T>) new HttpResultFunc<T>())
                .compose(applySchedulers()).subscribe(s);
    }

    /**
     * 判断ResultCode，获取Data数据
     * @param <T>
     */

    protected class HttpResultFunc<T> implements Func1<HttpResult<T>,T>
    {
        @Override
        public T call(HttpResult<T> httpResult) {
            if(httpResult.getCode()!=0){
                throw new ApiException(httpResult.getCode());
            }
            return httpResult.getData();
        }
    }
    /**
     * 负责线程切换
     * */
    final Observable.Transformer schedulersTransformer = new  Observable.Transformer() {
        @Override public Object call(Object observable) {
            return ((Observable)  observable).subscribeOn(Schedulers.io())
                  //  .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };
    @SuppressWarnings("unchecked")
    <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}
