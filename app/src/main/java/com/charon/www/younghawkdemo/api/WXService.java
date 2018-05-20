package com.charon.www.younghawkdemo.api;

import com.charon.www.younghawkdemo.model.WXToken;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 项目名称：YoungHawkDemo
 * 类描述：
 * 创建人：Charon
 * 创建时间：2018/5/21 0:17
 * 修改人：Charon
 * 修改时间：2018/5/21 0:17
 * 修改备注：
 */

public interface WXService {
    @GET("access_token")
    Observable<WXToken> getToken(@Query("appid") String APPID,
                                 @Query("secret") String SECRET,
                                 @Query("code") String CODE,
                                 @Query("grant_type") String authorization_code
    );
}
