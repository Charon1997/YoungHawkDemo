package com.charon.www.younghawkdemo.wxapi;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.charon.www.younghawkdemo.app.MyApplication;
import com.charon.www.younghawkdemo.model.WXToken;
import com.charon.www.younghawkdemo.ui.Activities.BaseActivity;
import com.charon.www.younghawkdemo.util.WXUtil;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;


import rx.Subscriber;

/**
 * 项目名称：YoungHawkDemo
 * 类描述：
 * 创建人：Charon
 * 创建时间：2018/5/21 0:05
 * 修改人：Charon
 * 修改时间：2018/5/21 0:05
 * 修改备注：
 */

public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private static final String TAG = "WXEntryActivity";
    private static final int RETURN_MSG_TYPE_LOGIN = 1; //登录
    private static final int RETURN_MSG_TYPE_SHARE = 2; //分享
    private Context mContext;

    @Override
    public void widgetClick(View v) {

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
        return 0;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {
        //这句没有写,是不能执行回调的方法的
        MyApplication.api.handleIntent(getIntent(), this);
    }


    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq baseReq) {

    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调
    @Override
    public void onResp(BaseResp baseResp) {
        Log.i(TAG, "onResp:------>");
        Log.i(TAG, "error_code:---->" + baseResp.errCode);
        int type = baseResp.getType(); //类型：分享还是登录
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                //用户拒绝授权
                showToast( "拒绝授权微信登录");
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //用户取消
                String message = "";
                if (type == RETURN_MSG_TYPE_LOGIN) {
                    message = "取消了微信登录";
                } else if (type == RETURN_MSG_TYPE_SHARE) {
                    message = "取消了微信分享";
                }
                showToast( message);
                break;
            case BaseResp.ErrCode.ERR_OK:
                //用户同意
                if (type == RETURN_MSG_TYPE_LOGIN) {
                    //用户换取access_token的code，仅在ErrCode为0时有效
                    String code = ((SendAuth.Resp) baseResp).code;
                    Log.i(TAG, "code:------>" + code);

                    //这里拿到了这个code，去做2次网络请求获取access_token和用户个人信息
                    getToken(code);

                } else if (type == RETURN_MSG_TYPE_SHARE) {
                    showToast( "微信分享成功");
                }
                break;
        }
    }
    private void getToken(String code){
        Subscriber<WXToken> subscriber = new Subscriber<WXToken>() {
            @Override
            public void onCompleted() {
                Log.d("Token", "onCompleted ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Token", "onError: "+e);
            }

            @Override
            public void onNext(WXToken wxToken) {

            }
        };
        WXUtil.getInstance().getToken(subscriber,code);
    }

}
