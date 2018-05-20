package com.charon.www.younghawkdemo.api;

import com.charon.www.younghawkdemo.model.API;
import com.charon.www.younghawkdemo.model.Plan;
import com.charon.www.younghawkdemo.model.Status;
import com.charon.www.younghawkdemo.model.UserBean;

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

public interface PlanService {
    @FormUrlEncoded
    @POST(API.URI_ADD_PLAN)
    Observable<Plan> addPlan(@Field("planData") String planData);

    @GET(API.URI_GET_PLAN)
    Observable<Plan> getPlanById(@Query("planId") int planId);

    @GET(API.URI_DEL_PLAN)
    Observable<Status> delPlanById(@Query("planId") int planId);

    @GET(API.URI_GET_PLAN_LIST)
    Observable<Plan[]> getPlanList();
}
