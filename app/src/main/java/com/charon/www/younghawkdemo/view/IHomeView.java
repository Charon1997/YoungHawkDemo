package com.charon.www.younghawkdemo.view;

import android.view.View;

import com.charon.www.younghawkdemo.model.HomeBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */

public interface IHomeView {
    void clickLike(int position);

    void clickComment(int position);


    void initView();

    void addView(List<HomeBean> homeList);

    void showLoading();

    void hideLoading();

    void showError();

    void editItem(int position);

    void deleteItem(int position);

}
