package com.charon.www.younghawkdemo.presenter;


import android.os.Handler;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.model.Date;
import com.charon.www.younghawkdemo.model.Moment;
import com.charon.www.younghawkdemo.ui.Fragments.HomeFragment;
import com.charon.www.younghawkdemo.view.IHomeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */

public class HomePresenter {
    private List<Moment> homeList;
    private IHomeView homeView;
    Handler handler = new Handler();
    public HomePresenter(IHomeView homeView) {
        this.homeView = homeView;
    }

    private List<Moment> addDate(int j) {
        homeList = new ArrayList<>();
        for (int i = 0 ; i < j ; i++) {
            Date day = new Date(2017,12,12);
            Date time = new Date(22,i);
            Moment homeBean = new Moment(R.drawable.charonhead,"Charon",day,time,"这是一个很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长的测试文字段"+i,0,0);
            homeList.add(homeBean);
        }
        return homeList;
    }

    public void getHomeInf() {
        homeView.loading(true);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                homeView.addView(addDate(10));
                homeView.loading(false);
            }
        },1500);
    }

    public void getMoreInf() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                homeView.addView(addDate(5));
                HomeFragment.loading = false;
            }
        },1500);
    }

    public void getHeadInf() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                homeView.refreshList(addDate(1));
                HomeFragment.loading = false;
                homeView.refresh(false);
            }
        },500);
    }

    public void deleteItem(int position) {
        //可以删除
        homeView.deleteItem(position);
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
