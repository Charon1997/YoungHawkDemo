package com.charon.www.younghawkdemo.api;

import com.charon.www.younghawkdemo.model.API;
import com.charon.www.younghawkdemo.model.Discuss;
import com.charon.www.younghawkdemo.model.Moment;
import com.charon.www.younghawkdemo.model.Status;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 项目名称：YoungHawkDemo
 * 类描述：
 * 创建人：Charon
 * 创建时间：2018/5/20 1:46
 * 修改人：Charon
 * 修改时间：2018/5/20 1:46
 * 修改备注：
 */

public interface DiscussService {
    @FormUrlEncoded
    @POST(API.URI_ADD_DISCUSS)
    Observable<Discuss> addDiscuss(@Field("discussData") String discussData  );

    @GET(API.URI_GET_DISCUSS)
    Observable<Discuss> getDiscussById(@Query("discussId") int discussId);

    @GET(API.URI_DEL_DISCUSS)
    Observable<Status> delDiscussById(@Query("discussId") int discussId);

    @GET(API.URI_GET_DISCUSS_LIST)
    Observable<Discuss[]> getDiscussList();
}
