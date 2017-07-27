package com.charon.www.younghawkdemo.view;

/**
 * Created by Administrator on 2017/4/24.
 */

public interface ILoginView {

    String getName();

    String getPassword();

    void clearName();

    void clearPassword();

    void loading(boolean loading);

    void loginSuccessfully();

    void loginFailure();


    void register();

    void visitor();

    void forget(String name);

}
