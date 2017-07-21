package com.charon.www.younghawkdemo.view;

import com.charon.www.younghawkdemo.model.HomeBean;
import com.charon.www.younghawkdemo.model.PlanBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public interface IPlanView {


    void showInf(List<PlanBean> list, int position);

    void addView(List<PlanBean> planList);

    void loading(boolean loading);

    void showError();

    void editItem(int position);

    void deleteItem(int position);

    void refreshList(List<PlanBean> planList);

    void refresh(boolean refresh);
}
