package com.charon.www.younghawkdemo.ui.Activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.charon.www.younghawkdemo.R;

/**
 * Created by Charon on 2017/4/24.
 */

public class TableActivity extends BaseActivity {
    private Toolbar mToolbar;
    private ImageView mIvDownload1,mIvDownload2;
    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.table_image_download1:
                showToast("正在下载项目推进报表");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showToast("逗你玩的:)");
                    }
                },1500);
                break;
            case R.id.table_image_download2:
                showToast("正在下载学员请假审批表");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showToast("逗你玩的:)");
                    }
                },1500);
                break;
        }
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
        return R.layout.fragment_table;
    }

    @Override
    public void initView(View view) {
        mIvDownload1 = $(R.id.table_image_download1);
        mIvDownload2 = $(R.id.table_image_download2);
        mToolbar =$(R.id.table_toolbar);
    }

    @Override
    public void setListener() {
        mIvDownload1.setOnClickListener(this);
        mIvDownload2.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {
        mToolbar.setTitle("表格");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
