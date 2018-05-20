package com.charon.www.younghawkdemo.util;

import com.charon.www.younghawkdemo.api.DiscussService;
import com.charon.www.younghawkdemo.api.LoginService;
import com.charon.www.younghawkdemo.api.MomentService;
import com.charon.www.younghawkdemo.api.PlanService;
import com.charon.www.younghawkdemo.api.UserService;
import com.charon.www.younghawkdemo.api.WXService;
import com.charon.www.younghawkdemo.biz.HttpService;
import com.charon.www.younghawkdemo.model.API;
import com.charon.www.younghawkdemo.model.UserBean;
import com.charon.www.younghawkdemo.model.WXToken;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：YoungHawkDemo
 * 类描述：
 * 创建人：Charon
 * 创建时间：2018/5/21 0:19
 * 修改人：Charon
 * 修改时间：2018/5/21 0:19
 * 修改备注：
 */

public class WXUtil {
    private WXService wxService;
    private static final int DEFAULT_TIMEOUT = 5;
    private static final String BASE_URL = API.WX_BASE;

    private WXUtil() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        wxService = retrofit.create(WXService.class);
    }


    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final WXUtil INSTANCE = new WXUtil();
    }

    //获取单例
    public static WXUtil getInstance() {
        return WXUtil.SingletonHolder.INSTANCE;
    }

    public void getToken(Subscriber<WXToken> subscriber, String code) {
        wxService.getToken("wxc5d8b5ac6f79d666","aefe4a44d22ce78d0b24ac29eaf7f597",code, "authorization_code")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
