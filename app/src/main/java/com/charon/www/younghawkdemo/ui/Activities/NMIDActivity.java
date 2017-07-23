package com.charon.www.younghawkdemo.ui.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.charon.www.younghawkdemo.R;

/**
 * Created by Administrator on 2017/7/22.
 */

public class NMIDActivity extends BaseActivity {
    private WebView mWvNMID;

    @Override
    public void widgetClick(View v) {

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
        return R.layout.activity_nmid;
    }

    @Override
    public void initView(View view) {
        mWvNMID = $(R.id.nmid_web);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {
        init();
    }

    private void init() {

        mWvNMID.loadUrl("http://nmid.cqupt.edu.cn/");
        mWvNMID.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings settings = mWvNMID.getSettings();
        settings.setJavaScriptEnabled(true);

        mWvNMID.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode== KeyEvent.KEYCODE_BACK)
        {
            if(mWvNMID.canGoBack())
            {
                mWvNMID.goBack();//返回上一页面
                return true;
            }
            else
            {
                finish();
                //退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
