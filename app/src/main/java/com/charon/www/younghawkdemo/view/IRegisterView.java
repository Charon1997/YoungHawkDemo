package com.charon.www.younghawkdemo.view;

/**
 * 项目名称：YoungHawkDemo
 * 类描述：
 * 创建人：Charon
 * 创建时间：2018/5/20 2:55
 * 修改人：Charon
 * 修改时间：2018/5/20 2:55
 * 修改备注：
 */

public interface IRegisterView {
    String getName();

    String getPassword();

    String getNickName();

    void clearNickName();

    void clearName();

    void clearPassword();

    void loading(boolean loading);

    void registerSuccessfully();

    void registerFailure();
}
