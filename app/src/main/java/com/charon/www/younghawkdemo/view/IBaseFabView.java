package com.charon.www.younghawkdemo.view;

/**
 * 项目名称：YoungHawkDemo
 * 类描述：
 * 创建人：Charon
 * 创建时间：2017/7/27 16:53
 * 修改人：Charon
 * 修改时间：2017/7/27 16:53
 * 修改备注：
 */

public interface IBaseFabView {
    void send();

    void loading(boolean loading);

    void onError();

    void finishActivity();
}
