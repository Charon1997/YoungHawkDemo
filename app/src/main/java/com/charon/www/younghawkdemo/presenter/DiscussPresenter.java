package com.charon.www.younghawkdemo.presenter;

import android.os.Handler;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.model.DiscussBean;
import com.charon.www.younghawkdemo.model.HomeBean;
import com.charon.www.younghawkdemo.model.Time;
import com.charon.www.younghawkdemo.ui.Fragments.DiscussFragment;
import com.charon.www.younghawkdemo.ui.Fragments.HomeFragment;
import com.charon.www.younghawkdemo.view.IDiscussView;
import com.charon.www.younghawkdemo.view.IHomeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public class DiscussPresenter {
    private List<DiscussBean> discussList;
    private IDiscussView discussView;
    Handler handler = new Handler();
    public DiscussPresenter(IDiscussView discussView) {
        this.discussView = discussView;
    }

    private List<DiscussBean> addDate(int j) {
        discussList = new ArrayList<>();
        for (int i = 0 ; i < j ; i++) {
            Time time = new Time(2017,2,3,12,i);
            DiscussBean discussBean = new DiscussBean("陈恳",time,"这是一个很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长的讨论文字段"+i,"标题");
            discussList.add(discussBean);
        }
        return discussList;
    }

    public void getDiscussInf() {
        discussView.loading(true);
        discussView.addView(addDate(20));
        discussView.loading(false);
    }

    public void getMoreInf() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                discussView.addView(addDate(5));
                DiscussFragment.loading = false;
            }
        },1500);
    }

    public void getHeadInf() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                discussView.refreshList(addDate(1));
                DiscussFragment.loading = false;
                discussView.refresh(false);
            }
        },500);
    }

    public void deleteItem(int position) {
        //先判断，可以删除
        discussView.deleteItem(position);
    }

    public void editItem(int position) {
        //可以编辑
        discussView.editItem(position);
    }

    public void clickLike(int position) {

    }

    public void clickComment(int position) {

    }
}
