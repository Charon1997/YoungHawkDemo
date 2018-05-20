package com.charon.www.younghawkdemo.presenter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.charon.www.younghawkdemo.biz.HttpService;
import com.charon.www.younghawkdemo.model.Discuss;
import com.charon.www.younghawkdemo.model.Status;
import com.charon.www.younghawkdemo.ui.Fragments.DiscussFragment;
import com.charon.www.younghawkdemo.util.SpUtil;
import com.charon.www.younghawkdemo.view.IDiscussView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Subscriber;

/**
 * Created by Charon on 2017/7/21.
 */

public class DiscussPresenter {
    private ArrayList<Discuss> discussList = new ArrayList<>();
    private IDiscussView discussView;
    Handler handler = new Handler();
    private Context mContext;
    private Subscriber<Discuss[]> subscriberList;

    private Subscriber<Status> subscriber;
    public DiscussPresenter(Context mContext,IDiscussView discussView) {
        this.mContext = mContext;
        this.discussView = discussView;
    }


    /**
     * [添加临时数据,一个j个条目]
     * @param j
     * @return
     */
//    private List<DiscussBean> addDate(int j) {
//        discussList = new ArrayList<>();
//        for (int i = 0 ; i < j ; i++) {
//            Date time = new Date(2,i);
//            DiscussBean discussBean = new DiscussBean("陈恳",time,"这是一个很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长的讨论文字段"+i,"标题");
//            discussList.add(discussBean);
//        }
//        return discussList;
//    }

    /**
     * [得到所有条目的信息]
     */
    public void getDiscussInf() {
        discussView.loading(true);
        subscriberList = new Subscriber<Discuss[]>() {
            @Override
            public void onCompleted() {
                discussView.loading(false);
                discussView.addView(discussList);
                Log.d("Discuss", "onCompleted ");
            }

            @Override
            public void onError(Throwable e) {
                discussView.loading(false);
                Log.d("Home", "onError: "+e);
            }

            @Override
            public void onNext(Discuss[] discusses) {
                Collections.addAll(discussList, discusses);
            }
        };
        HttpService.getInstance().getDiscussList(subscriberList);
    }

    /**
     * [上拉得到更多条目的信息]
     */
    public void getMoreInf() {

    }

    /**
     * [下滑刷新数据]
     */
    public void getHeadInf() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //判断是否有新数据
                //discussView.refreshList(addDate(1));
                DiscussFragment.loading = false;
                discussView.refresh(false);
            }
        },500);
    }

    /**
     * [删除条目]
     * @param position
     */
    public void deleteItem(final int position) {
        if (discussList.get(position).getUserId() == (int) SpUtil.get(mContext,"userId",1)){//discussList[position].getUserId() == (int)SpUtil.get(mContext,"userId",1)){
            discussView.loading(true);
            subscriber = new Subscriber<Status>() {
                @Override
                public void onCompleted() {
                    discussView.loading(false);
                    Log.d("Dis", "onCompleted");
                }

                @Override
                public void onError(Throwable e) {
                    discussView.loading(false);
                    Log.d("Dis", "onError: "+e);
                }

                @Override
                public void onNext(Status status) {
                    if (status.getCode().equals("200")){
                        //删除成功
                        discussView.toDelete(position);
                    }else {
                        discussView.showToastMsg("删除失败");
                    }
                }
            };
            HttpService.getInstance().delDiscussById(subscriber,discussList.get(position).getDcId());
        } else {
            discussView.showToastMsg("只能删除自己的哟！");
        }
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
