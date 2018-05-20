package com.charon.www.younghawkdemo.presenter;

import android.content.Context;
import android.util.Log;

import com.charon.www.younghawkdemo.biz.HttpService;
import com.charon.www.younghawkdemo.model.Status;
import com.charon.www.younghawkdemo.model.UserBean;
import com.charon.www.younghawkdemo.view.IRegisterView;

import rx.Subscriber;

/**
 * 项目名称：YoungHawkDemo
 * 类描述：
 * 创建人：Charon
 * 创建时间：2018/5/20 2:51
 * 修改人：Charon
 * 修改时间：2018/5/20 2:51
 * 修改备注：
 */

public class RegisterPresenter {
    private IRegisterView registerView;

    public RegisterPresenter(IRegisterView registerView){
        this.registerView = registerView;
    }

    public void register(String tel,String pwd,String nickName){
        registerView.loading(true);
        Subscriber<Status> subscriber = new Subscriber<Status>() {
            @Override
            public void onCompleted() {
                registerView.loading(false);
            }

            @Override
            public void onError(Throwable e) {
                registerView.registerFailure();
                registerView.loading(false);
                Log.d("Register", "onError: "+e);
            }

            @Override
            public void onNext(Status userBean) {
                registerView.registerSuccessfully();
            }
        };
        HttpService.getInstance().register(subscriber,tel,pwd,nickName);
    }
}
