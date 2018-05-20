package com.charon.www.younghawkdemo.ui.Activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.presenter.RegisterPresenter;
import com.charon.www.younghawkdemo.view.IRegisterView;

import kr.co.namee.permissiongen.PermissionGen;

/**
 * 项目名称：YoungHawkDemo
 * 类描述：
 * 创建人：Charon
 * 创建时间：2018/5/20 0:32
 * 修改人：Charon
 * 修改时间：2018/5/20 0:32
 * 修改备注：
 */

public class RegisterActivity extends BaseActivity implements IRegisterView {
    private Toolbar mToolbar;
    private EditText mMetUserName, mMetPassword, mMetNickNmae;
    private Button mBtnRegister;
    private ProgressDialog progressDialog;
    private RegisterPresenter presenter = new RegisterPresenter(this);

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.register_nickname:
                break;
            case R.id.register_name:
                break;
            case R.id.register_password:
                break;
            case R.id.register_send_button:
                register();
                break;
            default:
                break;

        }
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
        return R.layout.activity_register;
    }

    @Override
    public void initView(View view) {
        mToolbar = $(R.id.register_toolbar);
        mBtnRegister = $(R.id.register_send_button);
        mMetNickNmae = $(R.id.register_nickname);
        mMetUserName = $(R.id.register_name);
        mMetPassword = $(R.id.register_password);
    }

    @Override
    public void setListener() {
        mBtnRegister.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        PermissionGen.with(RegisterActivity.this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
                ).request();
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
    public String getNickName() {
        return mMetNickNmae.getText().toString();
    }

    @Override
    public void clearNickName() {
        mMetNickNmae.setText("");
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
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
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
    public void registerSuccessfully() {
        //startActivity(MainActivity.class);
        showToast("注册成功，请登录");
        finish();
    }

    @Override
    public void registerFailure() {
        showToast("注册失败");
    }

    public void register() {
        presenter.register(getName(), getPassword(), getNickName());
    }


}
