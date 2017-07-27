package com.charon.www.younghawkdemo.presenter;

import android.os.Handler;

import com.charon.www.younghawkdemo.model.Date;
import com.charon.www.younghawkdemo.model.DiscussBean;
import com.charon.www.younghawkdemo.ui.Fragments.DiscussFragment;
import com.charon.www.younghawkdemo.view.IDiscussView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charon on 2017/7/21.
 */

public class DiscussPresenter {
    private List<DiscussBean> discussList;
    private IDiscussView discussView;
    Handler handler = new Handler();
    public DiscussPresenter(IDiscussView discussView) {
        this.discussView = discussView;
    }


    /**
     * [添加临时数据,一个j个条目]
     * @param j
     * @return
     */
    private List<DiscussBean> addDate(int j) {
        discussList = new ArrayList<>();
        for (int i = 0 ; i < j ; i++) {
            Date time = new Date(2017,2,3,12,i);
            DiscussBean discussBean = new DiscussBean("陈恳",time,"这是一个很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长的讨论文字段"+i,"标题");
            discussList.add(discussBean);
        }
        return discussList;
    }

    /**
     * [得到所有条目的信息]
     */
    public void getDiscussInf() {
        discussView.loading(true);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                discussView.addView(addDate(20));
                DiscussFragment.loading = false;
                discussView.loading(false);
            }
        },1500);
    }

    /**
     * [上拉得到更多条目的信息]
     */
    public void getMoreInf() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                discussView.addView(addDate(5));
                DiscussFragment.loading = false;
            }
        },1500);
    }

    /**
     * [下滑刷新数据]
     */
    public void getHeadInf() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //判断是否有新数据
                discussView.refreshList(addDate(1));
                DiscussFragment.loading = false;
                discussView.refresh(false);
            }
        },500);
    }

    /**
     * [删除条目]
     * @param position
     */
    public void deleteItem(int position) {
        //先判断身份，可以删除，上传服务器，再删除
        discussView.deleteItem(position);

    }

    /**
     * [编辑条目]
     * @param position
     */
    public void editItem(int position) {
        //先判断身份，可以编辑，上传服务器，再编辑
        discussView.editItem(position);
    }

    /**
     * [点击喜欢]
     * @param position
     */
    public void clickLike(int position) {

    }

    /**
     * [点击评论]
     * @param position
     */
    public void clickComment(int position) {

    }
}
