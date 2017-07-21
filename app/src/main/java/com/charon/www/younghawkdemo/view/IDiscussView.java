package com.charon.www.younghawkdemo.view;

import com.charon.www.younghawkdemo.model.DiscussBean;
import com.charon.www.younghawkdemo.model.HomeBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public interface IDiscussView {
    void showInf(List<DiscussBean> discussList, int position);

    void addView(List<DiscussBean> discussList);

    void loading(boolean loading);

    void showError();

    void editItem(int position);

    void deleteItem(int position);

    void refreshList(List<DiscussBean> discussList);

    void refresh(boolean refresh);
}
