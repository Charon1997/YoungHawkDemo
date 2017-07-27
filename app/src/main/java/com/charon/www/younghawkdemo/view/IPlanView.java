package com.charon.www.younghawkdemo.view;

import com.charon.www.younghawkdemo.model.PlanBean;

import java.util.List;

/**
 * Created by Charon on 2017/7/21.
 */

public interface IPlanView  extends IBaseView {

    void showInf(List<PlanBean> list, int position);

    void addView(List<PlanBean> planList);

    void refreshList(List<PlanBean> planList);

}
