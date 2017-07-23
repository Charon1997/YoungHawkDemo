package com.charon.www.younghawkdemo.ui.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;

import com.charon.www.younghawkdemo.R;

public class WelcomeActivity extends BaseActivity {
    private int firstEnter = 0;
    private SharedPreferences spre;
    private LinearLayout logoView;

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
        return R.layout.activity_welcome;
    }

    @Override
    public void initView(View view) {
        logoView = $(R.id.launcher);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {
        spre = getSharedPreferences("myPref", MODE_PRIVATE);
        firstEnter = spre.getInt("flag", 0);


        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(2000);
        logoView.setAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("test", firstEnter + "");
                if (firstEnter == 0) {
                    Intent intent = new Intent(WelcomeActivity.this, IntroActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2500);
    }
}
