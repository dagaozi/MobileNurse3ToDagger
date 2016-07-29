package com.wyyy.corelibrary.utils;

import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by dagaozi on 2016/3/30.
 */
public class RxBus {
    private static  volatile  RxBus defaultInstance;
    //主题 同时充当Observer和Observable的角色
    private final Subject bus;
   //PublishSubject只会把在订阅发生的时间点之后的来自原始Observable的数据给观察者
    public RxBus() {
        this.bus = new SerializedSubject<>(PublishSubject.create());
    }
    //单例
    public static RxBus getDefault(){
        RxBus rxBus=defaultInstance;
        if(defaultInstance==null) {
            synchronized (RxBus.class){
                rxBus=defaultInstance;
                if(defaultInstance==null){
                    rxBus=new RxBus();
                    defaultInstance=rxBus;
                }
            }
        }
        return  rxBus;
    }

    /**
     * 提供一个新的事件
     * @param o
     */
    public void  post(Object o){
        bus.onNext(o);
    }

    /**
     * 根据传递的eventType 类型返回特定类型（eventType）的被观察者
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> rx.Observable<T> toOserverble(final Class<T> eventType){
        return bus.ofType(eventType);
        //ofType=filter+cast;
      /*  return bus.filter(new Func1<Object,Boolean>() {
            @Override
            public Boolean call(Object o) {
                return eventType.isInstance(o);
            }
        }).cast(eventType);*/
    }
}
