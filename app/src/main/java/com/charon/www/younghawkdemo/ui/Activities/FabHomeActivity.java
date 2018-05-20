package com.charon.www.younghawkdemo.ui.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.presenter.FabPresenter;
import com.charon.www.younghawkdemo.view.IBaseFabView;

import static com.charon.www.younghawkdemo.model.Constant.HOME_CONTENT;

/**
 * Created by Charon on 2017/5/2.
 */

public class FabHomeActivity extends BaseActivity implements IBaseFabView{
    private EditText mEditText;
    private Toolbar mToolbar;
    private LinearLayout mPb;
    private FabPresenter fabPresenter ;
    private String content = "";
    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParam(Bundle param) {
        if (param != null) {
            content = param.getString(HOME_CONTENT);
        }
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_fab_home;
    }

    @Override
    public void initView(View view) {
        mPb = $(R.id.fab_home_pb);
        mToolbar=$(R.id.fab_home_toolbar);
        mEditText=$(R.id.fab_home_edit);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {
        mToolbar.setTitle("发动态");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mEditText.setText(content);
        mEditText.setSelection(content.length());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fab_home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.fab_home_release) {
            send();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void send() {
        //showToast(mEditText.getText().toString());
        //上传
        fabPresenter = new FabPresenter(this,this);
        fabPresenter.sendMoment(mEditText.getText().toString());
    }

    @Override
    public void loading(boolean loading) {
        if (loading) {
            mPb.setVisibility(View.VISIBLE);
        } else {
            mPb.setVisibility(View.GONE);
        }
    }

    @Override
    public void onError() {
        Snackbar.make(mPb, R.string.error_network, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void finishActivity() {
        showToast("发送成功");
        finish();
    }
}
