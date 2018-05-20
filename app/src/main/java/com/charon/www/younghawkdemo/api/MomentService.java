package com.charon.www.younghawkdemo.api;

import com.charon.www.younghawkdemo.model.API;
import com.charon.www.younghawkdemo.model.Moment;
import com.charon.www.younghawkdemo.model.Plan;
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
 * 创建时间：2018/5/20 1:45
 * 修改人：Charon
 * 修改时间：2018/5/20 1:45
 * 修改备注：
 */

public interface MomentService {
    @FormUrlEncoded
    @POST(API.URI_ADD_MOMENT)
    Observable<Moment> addMoment(@Field("momentData")  String momentData);

    @GET(API.URI_GET_MOMENT)
    Observable<Moment> getMomentById(@Query("momentId") int momentId);

    @GET(API.URI_DEL_MOMENT)
    Observable<Status> delMomentById(@Query("momentId") int momentId);

    @GET(API.URI_GET_MOMENT_LIST)
    Observable<Moment[]> getMomentList();
}
