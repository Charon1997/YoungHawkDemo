package com.charon.www.younghawkdemo.view;

import com.charon.www.younghawkdemo.model.DiscussBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public interface IDiscussView extends IBaseItemView{

    void showInf(List<DiscussBean> discussList, int position);

    void addView(List<DiscussBean> discussList);

    void refreshList(List<DiscussBean> discussList);

}
