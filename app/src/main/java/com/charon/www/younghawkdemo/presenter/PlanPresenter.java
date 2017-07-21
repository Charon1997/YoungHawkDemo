package com.charon.www.younghawkdemo.presenter;

import android.os.Handler;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.model.HomeBean;
import com.charon.www.younghawkdemo.model.PlanBean;
import com.charon.www.younghawkdemo.model.Time;
import com.charon.www.younghawkdemo.ui.Fragments.HomeFragment;
import com.charon.www.younghawkdemo.ui.Fragments.PlanFragment;
import com.charon.www.younghawkdemo.view.IHomeView;
import com.charon.www.younghawkdemo.view.IPlanView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public class PlanPresenter {
    private List<PlanBean> planList;
    private IPlanView planView;
    Handler handler = new Handler();
    public PlanPresenter(IPlanView planView) {
        this.planView = planView;
    }

    private List<PlanBean> addDate(int j) {
        planList = new ArrayList<>();
        for (int i = 0 ; i < j ; i++) {
            Time time = new Time(2017,2,3,12,i);
            PlanBean planList = new PlanBean("姓名",time,"这是一个计划计划计划计划很划很长很长很长很长很长很长很长很长很长很长很长长很划很长很长很长很长很长很长很长很长很长很长很长长很划很长很长很长很长很长很长很长很长很长很长很长长很划很长很长很长很长很长很长很长很长很长很长很长长很划很长很长很长很长很长很长很长很长很长很长很长长很划很长很长很长很长很长很长很长很长很长很长很长长很划很长很长很长很长很长很长很长很长很长很长很长长很划很长很长很长很长很长很长很长很长很长很长很长长很长很长很长很长很长很长很长很长很长很长很长长很长很长很长很长很长很长很长很长很长长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长的测试文字段"+i);
            this.planList.add(planList);
        }
        return planList;
    }

    public void getPlanInf() {
        planView.addView(addDate(20));
    }

    public void getMoreInf() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                planView.addView(addDate(5));
                PlanFragment.loading = false;
            }
        },1500);

    }

    public void getHeadInf() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                planView.refreshList(addDate(1));
                planView.refresh(false);
                PlanFragment.loading = false;
            }
        },1500);
    }

    public void deleteItem(int position) {
        //可以删除
        planView.deleteItem(position);
    }

    public void editItem(int position) {
        //可以编辑
        planView.editItem(position);
    }
}
