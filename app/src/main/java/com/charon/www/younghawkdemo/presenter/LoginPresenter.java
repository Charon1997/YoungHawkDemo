package com.charon.www.younghawkdemo.presenter;

import android.os.Handler;
import android.print.PrintJob;

import com.charon.www.younghawkdemo.biz.ILoginBiz;
import com.charon.www.younghawkdemo.biz.LoginBiz;
import com.charon.www.younghawkdemo.biz.OnLoginListener;
import com.charon.www.younghawkdemo.model.LoginModel;
import com.charon.www.younghawkdemo.view.ILoginView;

/**
 * Created by Administrator on 2017/4/24.
 */

public class LoginPresenter {
    private ILoginView loginView;
    private ILoginBiz loginBiz;
    private Handler handler = new Handler();

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
        loginBiz = new LoginBiz();
    }

    public void login() {
        loginView.loading(true);
        loginBiz.login(loginView.getName(), loginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccessfully(LoginModel loginModel) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.loginSuccessfully();
                        loginView.loading(false);
                    }
                });

            }

            @Override
            public void loginFailure() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.loginFailure();
                        loginView.loading(false);
                    }
                });
            }
        });
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
        },2000);

    }
}
