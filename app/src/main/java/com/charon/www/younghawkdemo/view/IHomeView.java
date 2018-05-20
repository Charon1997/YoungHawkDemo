package com.charon.www.younghawkdemo.view;


import com.charon.www.younghawkdemo.model.Moment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */

public interface IHomeView extends IBaseItemView{
    void showInf(Moment list);

    void clickLike(int position);

    void clickComment(int position);

    void addView(ArrayList<Moment> homeList);

    void refreshList(Moment[] homeList);

    void changeLikeView(boolean isBlue);

    void changeCommentView(boolean isBlue);

    void showToastMsg(String msg);

}
