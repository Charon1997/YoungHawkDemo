package com.charon.www.younghawkdemo.biz;

import com.charon.www.younghawkdemo.model.LoginModel;
import com.charon.www.younghawkdemo.model.UserBean;
import com.charon.www.younghawkdemo.view.ILoginView;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/4/24.
 */

public class LoginBiz implements ILoginBiz {
    private Subscriber<UserBean> subscriber;
    @Override
    public void login(final String name, final String password, final OnLoginListener loginListener) {
        subscriber = new Subscriber<UserBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(UserBean userBean) {
                loginListener.loginSuccessfully(userBean);
            }
        };


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
