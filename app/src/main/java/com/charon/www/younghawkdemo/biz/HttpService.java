package com.charon.www.younghawkdemo.biz;


import android.util.Log;

import com.charon.www.younghawkdemo.api.DiscussService;
import com.charon.www.younghawkdemo.api.LoginService;
import com.charon.www.younghawkdemo.api.MomentService;
import com.charon.www.younghawkdemo.api.PlanService;
import com.charon.www.younghawkdemo.api.UserService;
import com.charon.www.younghawkdemo.model.API;
import com.charon.www.younghawkdemo.model.Discuss;
import com.charon.www.younghawkdemo.model.Moment;
import com.charon.www.younghawkdemo.model.Plan;
import com.charon.www.younghawkdemo.model.Status;
import com.charon.www.younghawkdemo.model.UserBean;
import com.charon.www.younghawkdemo.util.SpUtil;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.charon.www.younghawkdemo.model.Constant.SP_SAVE_USER_ID;

/**
 * 项目名称：YoungHawkDemo
 * 类描述：
 * 创建人：Charon
 * 创建时间：2018/5/20 1:40
 * 修改人：Charon
 * 修改时间：2018/5/20 1:40
 * 修改备注：
 */
public class HttpService {
    private static final int DEFAULT_TIMEOUT = 5;
    private static final String BASE_URL = API.URI_HOME;
    private LoginService loginService;
    private UserService userService;
    private MomentService momentService;
    private PlanService planService;
    private DiscussService discussService;

    private HttpService() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        loginService = retrofit.create(LoginService.class);
        userService = retrofit.create(UserService.class);
        momentService = retrofit.create(MomentService.class);
        planService = retrofit.create(PlanService.class);
        discussService = retrofit.create(DiscussService.class);
    }


    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpService INSTANCE = new HttpService();
    }

    //获取单例
    public static HttpService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void login(Subscriber<UserBean> subscriber, String tel, String pwd) {
        loginService.login(tel, pwd)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void register(Subscriber<Status> subscriber, String tel, String pwd,String nickName) {
        String userData = "{\"userName\":\"" + nickName +"\",\"pwd\":\""+pwd+"\",\"tel\":\""+tel+"\"}";
//        UserBean userBean = new UserBean();
//        userBean.setUserName(nickName);
//        userBean.setTel(tel);
//        userBean.setPwd(pwd);
//        RequestBody body=
//                RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(userBean));
        Log.d("Register", "register: "+userData);
        //RequestBody requestBody= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),userData);
        //Log.d("Register", "requestBody: "+requestBody);
        loginService.register(userData)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getUserById(Subscriber<UserBean> subscriber, int id) {
        userService.getUserById(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getUserList(Subscriber<UserBean> subscriber) {
        userService.getUserList()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getPlanList(Subscriber<Plan[]> subscriber) {
        planService.getPlanList()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getPlanById(Subscriber<Plan> subscriber, int planId) {
        planService.getPlanById(planId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void delPlanById(Subscriber<Status> subscriber, int planId) {
        planService.delPlanById(planId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void addPlan(Subscriber<Plan> subscriber, int userId, String planContent, String sumContent) {
//        Plan plan = new Plan();
//        plan.setUserId(userId);
//        plan.setPlanContent(planContent);
//        plan.setSumContent(sumContent);
//        String planData = new Gson().toJson(plan);
        String planData = "{\"userId\":\"" + userId +"\",\"planContent\":\""+planContent+"\",\"sumContent\":\""+sumContent+"\"}";
        Log.d("planData", "planData: "+planData);
        planService.addPlan(planData)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getMomentList(Subscriber<Moment[]> subscriber) {
        momentService.getMomentList()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getMomentById(Subscriber<Moment> subscriber, int momentId) {
        momentService.getMomentById(momentId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void delMomentById(Subscriber<Status> subscriber, int momentId) {
        momentService.delMomentById(momentId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void addMoment(Subscriber<Moment> subscriber, int userId, String content) {

        String monmentData="{\"userId\":"+userId+",\"content\":\""+content+"\"}";
        Log.d("Moment", "addMoment: "+monmentData);
        momentService.addMoment(monmentData)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getDiscussList(Subscriber<Discuss[]> subscriber) {
        discussService.getDiscussList()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getDiscussById(Subscriber<Discuss> subscriber, int discussId) {
        discussService.getDiscussById(discussId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void delDiscussById(Subscriber<Status> subscriber, int discussId) {
        discussService.delDiscussById(discussId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void addDiscuss(Subscriber<Discuss> subscriber, int userId, String dcContent, String title) {
//        Discuss discuss = new Discuss();
//        discuss.setUserId(userId);
//        discuss.setDcContent(dcContent);
//        discuss.setTitle(title);
//        String dis = new Gson().toJson(discuss);
        String dis = "{\"userId\":\"" + userId +"\",\"dcContent\":\""+dcContent+"\",\"title\":\""+title+"\"}";
        Log.d("dis", "adddis: "+dis);
        discussService.addDiscuss(dis)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}

