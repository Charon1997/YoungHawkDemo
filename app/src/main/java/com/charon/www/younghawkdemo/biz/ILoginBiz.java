package com.charon.www.younghawkdemo.biz;

/**
 * Created by Administrator on 2017/4/24.
 */

public interface ILoginBiz {
    public void login(String name,String password,OnLoginListener loginListener);

    public void forget();

    public void register();

    public void visitor();
}
