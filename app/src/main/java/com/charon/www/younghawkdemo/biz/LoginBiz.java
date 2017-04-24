package com.charon.www.younghawkdemo.biz;

import com.charon.www.younghawkdemo.model.LoginModel;
import com.charon.www.younghawkdemo.view.ILoginView;

/**
 * Created by Administrator on 2017/4/24.
 */

public class LoginBiz implements ILoginBiz {
    @Override
    public void login(final String name, final String password, final OnLoginListener loginListener) {
        new Thread(){
            public void run(){
                try {
                    //模拟联网操作
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if ("123456".equals(name) && "123456".equals(password)){
                    LoginModel user = new LoginModel();
                    user.setName(name);
                    user.setPassword(password);
                    loginListener.loginSuccessfully(user);
                } else loginListener.loginFailure();
            }
        }.start();
    }

    @Override
    public void forget() {

    }

    @Override
    public void register() {

    }

    @Override
    public void visitor() {

    }
}
