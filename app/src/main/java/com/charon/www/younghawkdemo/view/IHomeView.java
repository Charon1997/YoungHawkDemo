package com.charon.www.younghawkdemo.view;

import android.view.View;

import com.charon.www.younghawkdemo.model.HomeBean;
import com.charon.www.younghawkdemo.model.PlanBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */

public interface IHomeView {
    void showInf(List<HomeBean> list, int position);

    void clickLike(int position);

    void clickComment(int position);

    void addView(List<HomeBean> homeList);

    void loading(boolean loading);

    void showError();

    void editItem(int position);

    void deleteItem(int position);

    void refreshList(List<HomeBean> homeList);

    void refresh(boolean refresh);

    void changeLikeView(boolean isBlue);

    void changeCommentView(boolean isBlue);

}
