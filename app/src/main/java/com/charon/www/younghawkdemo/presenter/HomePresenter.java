package com.charon.www.younghawkdemo.presenter;


import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.charon.www.younghawkdemo.biz.HttpService;
import com.charon.www.younghawkdemo.model.Moment;
import com.charon.www.younghawkdemo.model.Status;
import com.charon.www.younghawkdemo.ui.Fragments.HomeFragment;
import com.charon.www.younghawkdemo.util.SpUtil;
import com.charon.www.younghawkdemo.view.IHomeView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/7/17.
 */

public class HomePresenter {
    private ArrayList<Moment> momnetList = new ArrayList<>();
    private IHomeView homeView;
    private Subscriber<Moment[]> subscriberList;

    private Subscriber<Status> subscriber;
    private Context mContext;
    Handler handler = new Handler();
    public HomePresenter(Context mContext,IHomeView homeView) {
        this.mContext = mContext;
        this.homeView = homeView;
    }


    public void getHomeInf() {
        homeView.loading(true);
        subscriberList = new Subscriber<Moment[]>() {
            @Override
            public void onCompleted() {
                homeView.loading(false);
                homeView.addView(momnetList);
                Log.d("Home", "onCompleted ");
            }

            @Override
            public void onError(Throwable e) {
                homeView.loading(false);
                Log.d("Home", "onError: "+e);
            }

            @Override
            public void onNext(Moment[] moments) {
                Collections.addAll(momnetList, moments);
            }
        };


        HttpService.getInstance().getMomentList(subscriberList);
    }

    public void getMoreInf() {
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                homeView.addView(addDate(5));
//                HomeFragment.loading = false;
//            }
//        },1500);
    }

    public void getHeadInf() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //homeView.refreshList(addDate(1));
                HomeFragment.loading = false;
                homeView.refresh(false);
            }
        },500);
    }

    public void deleteItem(final int position) {
        if (true){//momnetList[position].getUserId() == (Integer)SpUtil.get(mContext,"userId",1)){
            homeView.loading(true);
            subscriber = new Subscriber<Status>() {
                @Override
                public void onCompleted() {
                    homeView.loading(false);
                }

                @Override
                public void onError(Throwable e) {
                    homeView.loading(false);
                    Log.d("Home", "onError: "+e);
                }

                @Override
                public void onNext(Status status) {
                    if (status.getCode().equals("200")){
                        //删除成功
                        homeView.toDelete(position);
                    }else {
                        homeView.showToastMsg("删除失败");
                    }
                }
            };

            HttpService.getInstance().delMomentById(subscriber,momnetList.get(position).getMomentId());
        } else {
            homeView.showToastMsg("只能删除自己的哟！");
        }
    }

    public void editItem(int position) {
        //可以编辑
        homeView.editItem(position);
    }

    public void clickLike(int position) {
        //可以点赞;
        //homeView.changeLikeView(true);
    }

    public void clickComment(int position) {
        //可以评论
        //homeView.changeCommentView(true);
    }
}
