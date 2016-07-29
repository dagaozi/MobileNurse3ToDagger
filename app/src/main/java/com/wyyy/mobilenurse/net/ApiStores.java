package com.wyyy.mobilenurse.net;


import com.wyyy.corelibrary.model.HttpResult;
import com.wyyy.mobilenurse.model.TaskModel;
import com.wyyy.mobilenurse.model.TestModel;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dagaozi on 2016/3/25.
 */
public interface ApiStores {
    @GET("http://ip.taobao.com/service/getIpInfo.php")
    Observable<HttpResult<TestModel>> getTaobaoData(@Query("ip") String ip);

    @GET( "http://10.11.74.13/api/Test/GetTasks")
    Observable<HttpResult<List<TaskModel>>> getTasksData();
}
