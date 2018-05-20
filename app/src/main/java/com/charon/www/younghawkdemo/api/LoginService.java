package com.charon.www.younghawkdemo.api;

import com.charon.www.younghawkdemo.model.API;
import com.charon.www.younghawkdemo.model.Status;
import com.charon.www.younghawkdemo.model.UserBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 项目名称：YoungHawkDemo
 * 类描述：
 * 创建人：Charon
 * 创建时间：2018/5/20 1:42
 * 修改人：Charon
 * 修改时间：2018/5/20 1:42
 * 修改备注：
 */

public interface LoginService {
    @POST(API.URI_LOGIN)
    Observable<UserBean> login(@Query("tel") String tel, @Query("pwd") String pwd);

    @FormUrlEncoded
    @POST(API.URI_REGISTER)
    Observable<Status> register(@Field("userData") String userData);
}
