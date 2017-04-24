package com.charon.www.younghawkdemo.ui.Activities;

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
 * Created by Administrator on 2017/4/20.
 */

public class LoginActivity extends AppCompatActivity implements ILoginView {
    private Toolbar mToolbar;
    private Button mBtnRegister,mBtnLoginIn,mBtnVisitor,mBtnForget;
    private MaterialEditText mMetUserName,mMetPassword;
    private LoginPresenter presenter = new LoginPresenter(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        initView();
    }

    private void init() {
        mToolbar = (Toolbar) findViewById(R.id.login_toolbar);
        mBtnRegister = (Button) findViewById(R.id.login_signup_button);
        mBtnLoginIn = (Button) findViewById(R.id.login_send_button);
        mBtnVisitor = (Button) findViewById(R.id.login_visitor_button);
        mBtnForget = (Button) findViewById(R.id.login_forget_button);
        mMetUserName = (MaterialEditText) findViewById(R.id.login_name);
        mMetPassword = (MaterialEditText) findViewById(R.id.login_password);
    }
    private void initView(){
        mToolbar.setTitle("雏鹰计划");
        setSupportActionBar(mToolbar);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.register();
            }
        });
        mBtnLoginIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login();
            }
        });
        mBtnVisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.visitor();
            }
        });
        mBtnForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.forget();
            }
        });
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
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFailure() {
        Toast.makeText(this, "登录失败，请检查账号密码是否正确", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void register() {
        //挑战注册界面
    }

    @Override
    public void visitor() {
        //游客登录
        Toast.makeText(this, "游客登录", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void forget(String name) {
        //忘记密码界面
    }
}
