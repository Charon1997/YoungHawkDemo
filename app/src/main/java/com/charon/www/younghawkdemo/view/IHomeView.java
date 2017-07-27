package com.charon.www.younghawkdemo.view;


import com.charon.www.younghawkdemo.model.Moment;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */

public interface IHomeView extends IBaseItemView{
    void showInf(List<Moment> list, int position);

    void clickLike(int position);

    void clickComment(int position);

    void addView(List<Moment> homeList);

    void refreshList(List<Moment> homeList);

    void changeLikeView(boolean isBlue);

    void changeCommentView(boolean isBlue);

}
