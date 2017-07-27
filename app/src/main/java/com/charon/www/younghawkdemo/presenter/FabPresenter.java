package com.charon.www.younghawkdemo.presenter;

import android.os.Handler;

import com.charon.www.younghawkdemo.view.IBaseFabView;

/**
 * 项目名称：YoungHawkDemo
 * 类描述：
 * 创建人：Charon
 * 创建时间：2017/7/27 16:56
 * 修改人：Charon
 * 修改时间：2017/7/27 16:56
 * 修改备注：
 */

public class FabPresenter {
    private IBaseFabView baseFabView;

    public FabPresenter(IBaseFabView baseFabView) {
        this.baseFabView = baseFabView;
    }

    /**
     * [发送动态]
     * @param moment
     */
    public void sendMoment(String moment) {
        baseFabView.loading(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                baseFabView.loading(false);
                baseFabView.finishActivity();
            }
        },1500);
    }

    /**
     * [发送计划与总结]
     * @param plan
     * @param summary
     */
    public void sendPlan(String plan,String summary){
        baseFabView.loading(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                baseFabView.loading(false);
                baseFabView.finishActivity();
            }
        },1500);
    }

    /**
     * [发起讨论]
     * @param title
     * @param comment
     */
    public void sendDiscuss(String title,String comment) {
        baseFabView.loading(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                baseFabView.loading(false);
                baseFabView.finishActivity();
            }
        },1500);
    }
}
