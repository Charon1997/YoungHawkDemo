package com.charon.www.younghawkdemo.app;

import android.app.Application;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * 项目名称：YoungHawkDemo
 * 类描述：
 * 创建人：Charon
 * 创建时间：2018/5/21 0:03
 * 修改人：Charon
 * 修改时间：2018/5/21 0:03
 * 修改备注：
 */

public class MyApplication extends Application {
    private static final String APP_ID = "wxc5d8b5ac6f79d666";
    public static IWXAPI api;


    @Override
    public void onCreate() {
        super.onCreate();
        regToWx();
    }

    private void regToWx() {
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        api.registerApp(APP_ID);
    }
}
