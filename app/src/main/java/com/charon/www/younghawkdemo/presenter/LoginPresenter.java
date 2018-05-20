package com.charon.www.younghawkdemo.presenter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.charon.www.younghawkdemo.biz.HttpService;
import com.charon.www.younghawkdemo.model.Status;
import com.charon.www.younghawkdemo.model.UserBean;
import com.charon.www.younghawkdemo.util.SpUtil;
import com.charon.www.younghawkdemo.view.ILoginView;

import rx.Subscriber;

import static com.charon.www.younghawkdemo.model.Constant.SP_SAVE_USER_ID;

/**
 * Created by Administrator on 2017/4/24.
 */

public class LoginPresenter {
    private ILoginView loginView;
    private Context mContext;

    public LoginPresenter(Context mContext, ILoginView loginView) {
        this.mContext = mContext;
        this.loginView = loginView;
    }

    public void login() {

        loginView.loading(true);
        Subscriber<UserBean> subscriber = new Subscriber<UserBean>() {
            @Override
            public void onCompleted() {
                loginView.loading(false);
            }

            @Override
            public void onError(Throwable e) {
                loginView.loginFailure();
                loginView.loading(false);
                Log.d("Login123", "onError: " + e);
            }

            @Override
            public void onNext(UserBean userBean) {
                if (userBean != null){
                    SpUtil.put(mContext,SP_SAVE_USER_ID,userBean.getUserId());
                    loginView.loginSuccessfully();
                } else {
                    loginView.loginFailure();
                }

            }
        };
        HttpService.getInstance().login(subscriber, loginView.getName(), loginView.getPassword());
//        loginBiz.login(loginView.getName(), loginView.getPassword(), new OnLoginListener() {
//            @Override
//            public void loginSuccessfully(LoginModel loginModel) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        loginView.loginSuccessfully();
//                        loginView.loading(false);
//                    }
//                });
//
//            }
//
//            @Override
//            public void loginFailure() {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        loginView.loginFailure();
//                        loginView.loading(false);
//                    }
//                });
//            }
//        });
    }

    public void register() {
        loginView.register();
    }

    public void forget() {
        loginView.forget(loginView.getName());
    }

    public void visitor() {
        loginView.loading(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loginView.loading(false);
                loginView.visitor();
            }
        }, 2000);

    }
}
