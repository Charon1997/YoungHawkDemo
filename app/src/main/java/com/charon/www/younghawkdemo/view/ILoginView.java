package com.charon.www.younghawkdemo.view;

/**
 * Created by Administrator on 2017/4/24.
 */

public interface ILoginView {
    public String getName();
    public String getPassword();

    public void clearName();
    public void clearPassword();

    public void showLoading();
    public void hideLoading();

    public void loginSuccessfully();
    public void loginFailure();


    public void register();
    public void visitor();
    public void forget(String name);

}
