package com.charon.www.younghawkdemo.view;

import com.charon.www.younghawkdemo.model.Plan;

import java.util.ArrayList;


/**
 * Created by Charon on 2017/7/21.
 */

public interface IPlanView extends IBaseItemView {

    void showInf(Plan[] list, int position);

    void addView(ArrayList<Plan> planList);

    void refreshList(Plan[] planList);

    void showToastMsg(String msg);
}
