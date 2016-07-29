package com.wyyy.mobilenurse.base;

import com.wyyy.corelibrary.base.BaseApp;

/**
 * Created by haohaibin .（dagaozi@163.com）
 * 创建时间：2016/7/27 9:31
 * 类描述：
 */
public class MnApp extends BaseApp {
  //private static ApiStores apiStores;
    @Override
    public void onCreate() {
        super.onCreate();
       // apiStores= ApiClient.create(ApiStores.class);
    }
  /*  public static ApiStores getApiStores(){
        if(null==apiStores)
            apiStores=ApiClient.create(ApiStores.class);
        return apiStores;
    }*/
}
