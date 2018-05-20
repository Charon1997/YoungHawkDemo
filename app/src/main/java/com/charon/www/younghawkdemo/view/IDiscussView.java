package com.charon.www.younghawkdemo.view;

import com.charon.www.younghawkdemo.model.Discuss;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/21.
 */

public interface IDiscussView extends IBaseItemView {

    void showInf(Discuss[] discussList, int position);

    void addView(ArrayList<Discuss> discussList);

    void refreshList(Discuss[] discussList);

    void showToastMsg(String msg);
}
