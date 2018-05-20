package com.charon.www.younghawkdemo.api;

import com.charon.www.younghawkdemo.model.API;
import com.charon.www.younghawkdemo.model.UserBean;

import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 项目名称：YoungHawkDemo
 * 类描述：
 * 创建人：Charon
 * 创建时间：2018/5/20 1:43
 * 修改人：Charon
 * 修改时间：2018/5/20 1:43
 * 修改备注：
 */

public interface UserService {
    @GET(API.URI_GET_USER)
    Observable<UserBean> getUserById(@Query("userId") int userId);

    @GET(API.URI_GET_USER_LIST)
    Observable<UserBean> getUserList();


    @Multipart
    @POST(API.URI_ADD_HEAD_IMG)
    Observable<Response> updateAvatar (@Part("uploadFile\"; filename=\"test.jpg\"") RequestBody imgs );


}
