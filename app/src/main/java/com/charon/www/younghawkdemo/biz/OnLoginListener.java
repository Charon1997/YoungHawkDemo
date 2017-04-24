package com.charon.www.younghawkdemo.biz;

import com.charon.www.younghawkdemo.model.LoginModel;

/**
 * Created by Administrator on 2017/4/24.
 */

public interface OnLoginListener {
    public void loginSuccessfully(LoginModel loginModel);

    public void loginFailure();
}
