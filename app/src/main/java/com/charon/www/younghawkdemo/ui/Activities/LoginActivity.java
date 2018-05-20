package com.charon.www.younghawkdemo.ui.Activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.app.MyApplication;
import com.charon.www.younghawkdemo.presenter.LoginPresenter;
import com.charon.www.younghawkdemo.view.ILoginView;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * Created by Charon on 2017/4/20.
 */

public class LoginActivity extends BaseActivity implements ILoginView {
    private Toolbar mToolbar;
    private Button mBtnRegister,mBtnLoginIn,mBtnVisitor,mBtnForget;
    private EditText mMetUserName,mMetPassword;
    private ProgressDialog progressDialog;
    private ImageView mIvWx;
    private LoginPresenter presenter = new LoginPresenter(this,this);



    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.login_signup_button:
                presenter.register();
                break;
            case R.id.login_wx_send_button:

                toWx();
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
            default:
                break;
        }
    }

    private void toWx() {
        showToast("toWx");
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
         req.scope = "snsapi_userinfo";//提示 scope参数错误，或者没有scope权限
        req.state = "wechat_sdk_微信登录";
        MyApplication.api.sendReq(req);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initParam(Bundle param) {

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
        mIvWx = $(R.id.login_wx_send_button);
    }

    @Override
    public void setListener() {
        mBtnRegister.setOnClickListener(this);
        mBtnLoginIn.setOnClickListener(this);
        mBtnVisitor.setOnClickListener(this);
        mBtnForget.setOnClickListener(this);
        mIvWx.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);
        PermissionGen.with(LoginActivity.this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE
                ).request();

        //PermisionUtils.verifyStoragePermissions(this);
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
    public void loading(boolean loading) {
        if(progressDialog == null){
            progressDialog =  new ProgressDialog(this);
            progressDialog.setMessage("加载中...");
            progressDialog.setTitle("登录");
        }
        if (loading && !progressDialog.isShowing()) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
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
        startActivity(RegisterActivity.class);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = 100)
    public void doSomething() {
        showToast("已经获取权限");
    }

    @PermissionFail(requestCode = 100)
    public void doFailSomething() {
        showToast("获取权限失败");
    }
}
