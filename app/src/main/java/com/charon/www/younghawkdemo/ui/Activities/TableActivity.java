package com.charon.www.younghawkdemo.ui.Activities;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.model.API;

/**
 * Created by Charon on 2017/4/24.
 */

public class TableActivity extends BaseActivity {
    private Toolbar mToolbar;
    private ImageView mIvDownload1,mIvDownload2,getmIvDownload3;
    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.table_image_download1:
                download(API.URI_GET_DOWNLOAD1,"a.txt");
                break;
            case R.id.table_image_download2:
                download(API.URI_GET_DOWNLOAD2,"文档1.pdf");
                break;
            case R.id.table_image_download3:
                download(API.URI_GET_DOWNLOAD3,"wd2.docx");
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

    private void download(String uri,String fileString){
        //创建下载任务,downloadUrl就是下载链接
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(uri));
        //指定下载路径和下载文件名
        request.setDestinationInExternalPublicDir("/download/", fileString);
        //获取下载管理器
        DownloadManager downloadManager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        //将下载任务加入下载队列，否则不会进行下载
        if (downloadManager != null) {
            downloadManager.enqueue(request);
            showToast("开始下载");
        }
    }
}
