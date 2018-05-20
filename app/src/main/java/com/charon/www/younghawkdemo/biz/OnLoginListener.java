package com.charon.www.younghawkdemo.biz;

import com.charon.www.younghawkdemo.model.UserBean;

/**
 * Created by Administrator on 2017/4/24.
 */

public interface OnLoginListener {
    public void loginSuccessfully(UserBean userBean);

    public void loginFailure();
}
