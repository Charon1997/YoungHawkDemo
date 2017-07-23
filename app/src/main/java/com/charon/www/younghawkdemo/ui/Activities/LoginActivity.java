package com.charon.www.younghawkdemo.ui.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.presenter.LoginPresenter;
import com.charon.www.younghawkdemo.view.ILoginView;
import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * Created by Charon on 2017/4/20.
 */

public class LoginActivity extends BaseActivity implements ILoginView {
    private Toolbar mToolbar;
    private Button mBtnRegister,mBtnLoginIn,mBtnVisitor,mBtnForget;
    private MaterialEditText mMetUserName,mMetPassword;
    private LoginPresenter presenter = new LoginPresenter(this);

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.login_signup_button:
                presenter.register();
                break;
            case R.id.login_send_button:
                presenter.login();
                break;
            case R.id.login_visitor_button:
                presenter.visitor();
                break;
            case R.id.login_forget_button:
                presenter.forget();
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(View view) {
        mToolbar = $(R.id.login_toolbar);
        mBtnRegister = $(R.id.login_signup_button);
        mBtnLoginIn =$(R.id.login_send_button);
        mBtnVisitor = $(R.id.login_visitor_button);
        mBtnForget = $(R.id.login_forget_button);
        mMetUserName = $(R.id.login_name);
        mMetPassword = $(R.id.login_password);
    }

    @Override
    public void setListener() {
        mBtnRegister.setOnClickListener(this);
        mBtnLoginIn.setOnClickListener(this);
        mBtnVisitor.setOnClickListener(this);
        mBtnForget.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {
        mToolbar.setTitle("雏鹰计划");
        setSupportActionBar(mToolbar);
    }

    @Override
    public String getName() {
        return mMetUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return mMetPassword.getText().toString();
    }

    @Override
    public void clearName() {
        mMetUserName.setText("");
    }

    @Override
    public void clearPassword() {
        mMetPassword.setText("");
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void loginSuccessfully() {
        startActivity(MainActivity.class);
        finish();
    }

    @Override
    public void loginFailure() {
        showToast("登录失败，请检查账号密码是否正确");
    }

    @Override
    public void register() {
        //挑战注册界面
    }

    @Override
    public void visitor() {
        //游客登录
        showToast("游客登录");
        startActivity(MainActivity.class);
        finish();
    }

    @Override
    public void forget(String name) {
        //忘记密码界面
    }
}
