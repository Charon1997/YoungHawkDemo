package com.charon.www.younghawkdemo.ui.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.temp.Man;


public class PersonalDateActivity extends BaseActivity {
    private TextView mDuty,qq,mPhoneNum,mEmail;
    private Man man = new Man();
    private int dif = 0;
    private FloatingActionButton mFloatingActionButton;
    private CollapsingToolbarLayout mToolbarlayout;
    private Toolbar mToolbar;
    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + man.getPhoneNum());
                intent.setData(data);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void initParam(Bundle param) {
        man=(Man)param.getSerializable("man");
        dif = param.getInt("key");
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_info_person;
    }

    @Override
    public void initView(View view) {
        mFloatingActionButton = $(R.id.fab);
        mDuty = $(R.id.person_duty);
        qq = $(R.id.person_qq);
        mPhoneNum =$(R.id.person_phone);
        mEmail =$(R.id.person_email);
        mToolbarlayout =$(R.id.person_toolbar_layout);
        mToolbar = $(R.id.person_toolbar);
    }

    @Override
    public void setListener() {
        mFloatingActionButton.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {
        assert man != null;
        mToolbarlayout.setTitle(man.getName());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setDate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return false;
    }



    private void setDate() {
        switch (dif) {
            case 1:
                mDuty.setText("运营专责&&牵头人");
                break;
            case 2:
                mDuty.setText("牵头人&&项目指导");
                break;
            default:
                mDuty.setText(man.getDuty());
                break;
        }
        mPhoneNum.setText(man.getPhoneNum());
        qq.setText(man.getQQNum());
        mEmail.setText(man.getEmail());
    }
}
