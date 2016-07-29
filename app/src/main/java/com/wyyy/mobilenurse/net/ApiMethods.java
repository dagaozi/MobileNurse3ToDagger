package com.wyyy.mobilenurse.net;

import com.wyyy.corelibrary.net.ApiClient;
import com.wyyy.corelibrary.net.ApiFactory;
import com.wyyy.mobilenurse.model.TaskModel;
import com.wyyy.mobilenurse.model.TestModel;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by dagaozi on 2016/3/30.
 * 网络返回数据加工处理类
 */
public class ApiMethods extends ApiFactory {
    private ApiStores apiStores;
    private ApiMethods(){
        apiStores= ApiClient.create(ApiStores.class);
    }
      private static class SingletonHolder {
        private static final ApiMethods INSTANCE = new ApiMethods();
    }
    public static ApiMethods getInstance()
    {
        return  SingletonHolder.INSTANCE;
    }
    public ApiStores getApiStores(){
        if(null==apiStores)
            apiStores=ApiClient.create(ApiStores.class);
        return apiStores;
    }


    public Subscription getTaoboData(Subscriber<TestModel> subscriber, String ip) {
        Observable observable = apiStores.getTaobaoData(ip);
        return toSubscribe(observable, subscriber);
    }
    public Subscription getTasksData(Subscriber<List<TaskModel>> subscriber) {
        Observable observable = apiStores.getTasksData();
        return toSubscribe(observable, subscriber);
    }
}
