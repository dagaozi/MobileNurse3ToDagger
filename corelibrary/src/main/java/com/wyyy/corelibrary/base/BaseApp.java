package com.wyyy.corelibrary.base;

import android.app.Application;


/**
 * Created by haohaibin .（dagaozi@163.com）
 * 创建时间：2016/7/26 15:29
 * 类描述：
 */
public class BaseApp extends Application {
    //public static Context appContext=null;
    private static BaseApp app;
    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
    }
    public static BaseApp getAppContext() {
        return app;
    }

}
