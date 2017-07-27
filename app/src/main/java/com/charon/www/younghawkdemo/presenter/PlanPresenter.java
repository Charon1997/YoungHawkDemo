package com.charon.www.younghawkdemo.presenter;

import android.os.Handler;

import com.charon.www.younghawkdemo.model.Date;
import com.charon.www.younghawkdemo.model.PlanBean;
import com.charon.www.younghawkdemo.ui.Fragments.PlanFragment;
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

    /**
     * [添加临时数据]
     * @param j
     * @return
     */
    private List<PlanBean> addDate(int j) {
        planList = new ArrayList<>();
        for (int i = 0 ; i < j ; i++) {
            Date time = new Date(2017,2,3,12,i);
            PlanBean planList = new PlanBean("姓名",time,"这是一个计划计划计划计长很长很很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长的测试文字段"+i,i+"计划是这个啊");
            this.planList.add(planList);
        }
        return planList;
    }

    /**
     * [得到所有条目的信息]
     */
    public void getPlanInf() {
        planView.loading(true);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                planView.addView(addDate(20));
                planView.loading(false);
                PlanFragment.loading = false;
            }
        },2000);
    }

    /**
     * [上拉得到更多]
     */
    public void getMoreInf() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                planView.addView(addDate(5));
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
                planView.refreshList(addDate(1));
                planView.refresh(false);
                PlanFragment.loading = false;
            }
        },1500);
    }

    /**
     * [删除条目]
     * @param position
     */
    public void deleteItem(int position) {
        //先判断身份，可以删除，上传服务器，再删除
        planView.deleteItem(position);
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
