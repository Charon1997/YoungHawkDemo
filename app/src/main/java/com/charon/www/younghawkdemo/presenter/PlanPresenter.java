package com.charon.www.younghawkdemo.presenter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.charon.www.younghawkdemo.biz.HttpService;
import com.charon.www.younghawkdemo.model.Plan;
import com.charon.www.younghawkdemo.model.Status;
import com.charon.www.younghawkdemo.ui.Fragments.PlanFragment;
import com.charon.www.younghawkdemo.view.IPlanView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/7/21.
 */

public class PlanPresenter {
    private ArrayList<Plan> planList = new ArrayList<>();
    private IPlanView planView;
    Handler handler = new Handler();
    private Subscriber<Plan[]> subscriberList;
    private Subscriber<Status> subscriber;
    private Context mContext;
    public PlanPresenter(Context mContext,IPlanView planView) {
        this.mContext = mContext;
        this.planView = planView;
    }


    /**
     * [得到所有条目的信息]
     */
    public void getPlanInf() {
        planView.loading(true);
        subscriberList= new Subscriber<Plan[]>() {
            @Override
            public void onCompleted() {
                planView.loading(false);
                planView.addView(planList);
                Log.d("Plan", "onCompleted ");
            }

            @Override
            public void onError(Throwable e) {
                planView.loading(false);
                Log.d("Plan", "onError: "+e);
            }

            @Override
            public void onNext(Plan[] plans) {
                Collections.addAll(planList, plans);
            }
        };
        HttpService.getInstance().getPlanList(subscriberList);
    }

    /**
     * [上拉得到更多]
     */
    public void getMoreInf() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //planView.addView(addDate(5));
                PlanFragment.loading = false;
            }
        },1500);

    }

    /**
     * [下拉刷新]
     */
    public void getHeadInf() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //planView.refreshList(addDate(1));
                planView.refresh(false);
                PlanFragment.loading = false;
            }
        },500);
    }

    /**
     * [删除条目]
     * @param position
     */
    public void deleteItem(final int position) {
        //先判断身份，可以删除，上传服务器，再删除
        if (true){//momnetList[position].getUserId() == (Integer)SpUtil.get(mContext,"userId",1)){
            planView.loading(true);
            subscriber = new Subscriber<Status>() {
                @Override
                public void onCompleted() {
                    planView.loading(false);
                }

                @Override
                public void onError(Throwable e) {
                    planView.loading(false);
                    Log.d("Plan", "onError: "+e);
                }

                @Override
                public void onNext(Status status) {
                    if (status.getCode().equals("200")){
                        //删除成功
                        planView.toDelete(position);
                    }else {
                        planView.showToastMsg("删除失败");
                    }
                }
            };

            HttpService.getInstance().delMomentById(subscriber,planList.get(position).getPlanId());
        } else {
            planView.showToastMsg("只能删除自己的哟！");
        }
    }

    /**
     * [编辑条目]
     * @param position
     */
    public void editItem(int position) {
        //先判断身份，可以编辑，上传服务器，再编辑
        planView.editItem(position);
    }
}
