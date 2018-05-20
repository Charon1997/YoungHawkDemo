package com.charon.www.younghawkdemo.presenter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.charon.www.younghawkdemo.biz.HttpService;
import com.charon.www.younghawkdemo.model.Discuss;
import com.charon.www.younghawkdemo.model.Moment;
import com.charon.www.younghawkdemo.model.Plan;
import com.charon.www.younghawkdemo.util.SpUtil;
import com.charon.www.younghawkdemo.view.IBaseFabView;

import rx.Subscriber;

import static com.charon.www.younghawkdemo.model.Constant.SP_SAVE_USER_ID;

/**
 * 项目名称：YoungHawkDemo
 * 类描述：
 * 创建人：Charon
 * 创建时间：2017/7/27 16:56
 * 修改人：Charon
 * 修改时间：2017/7/27 16:56
 * 修改备注：
 */

public class FabPresenter {
    private IBaseFabView baseFabView;
    private Subscriber<Moment> subscriberMoment;
    private Subscriber<Plan> subscriberPlan;
    private Subscriber<Discuss> subscriberDiscuss;
    private Moment moment;
    private Plan plan;
    private Discuss discuss;
    private Context mContext;
    int userId;

    public FabPresenter(Context mContext,IBaseFabView baseFabView) {
        this.mContext = mContext;
        this.baseFabView = baseFabView;
    }

    /**
     * [发送动态]
     * @param moment
     */
    public void sendMoment(String moment) {
        baseFabView.loading(true);
        subscriberMoment = new Subscriber<Moment>() {
            @Override
            public void onCompleted() {
                baseFabView.loading(false);
                baseFabView.finishActivity();
                Log.d("Fab", "onCompleted ");
            }

            @Override
            public void onError(Throwable e) {
                baseFabView.loading(false);
                Log.d("FabM", "onError: "+e);
            }

            @Override
            public void onNext(Moment moment) {
            }
        };
        userId = (int) SpUtil.get(mContext,SP_SAVE_USER_ID,1);
        HttpService.getInstance().addMoment(subscriberMoment, userId,moment);
    }

    /**
     * [发送计划与总结]
     * @param plan
     * @param summary
     */
    public void sendPlan(String plan,String summary){
        baseFabView.loading(true);
        subscriberPlan = new Subscriber<Plan>() {
            @Override
            public void onCompleted() {
                baseFabView.loading(false);
                baseFabView.finishActivity();
            }

            @Override
            public void onError(Throwable e) {
                baseFabView.loading(false);
                Log.d("FabM", "onError: "+e);
            }

            @Override
            public void onNext(Plan plan) {

            }
        };
        userId = (int) SpUtil.get(mContext,SP_SAVE_USER_ID,1);
        HttpService.getInstance().addPlan(subscriberPlan, userId,plan,summary);
    }

    /**
     * [发起讨论]
     * @param title
     * @param comment
     */
    public void sendDiscuss(String title,String comment) {
        baseFabView.loading(true);
        subscriberDiscuss = new Subscriber<Discuss>() {
            @Override
            public void onCompleted() {
                baseFabView.loading(false);
                baseFabView.finishActivity();
            }

            @Override
            public void onError(Throwable e) {
                baseFabView.loading(false);
                Log.d("FabM", "onError: "+e);
            }

            @Override
            public void onNext(Discuss discuss) {

            }
        };
        userId = (int) SpUtil.get(mContext,SP_SAVE_USER_ID,1);
        HttpService.getInstance().addDiscuss(subscriberDiscuss, userId,comment,title);
    }
}
