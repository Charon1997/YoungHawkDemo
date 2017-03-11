package com.charon.www.younghawkdemo.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.charon.www.younghawkdemo.Costum.StatusUtil;
import com.charon.www.younghawkdemo.R;

public class WelcomeActivity extends AppCompatActivity {
    private int firstEnter = 0;
    private SharedPreferences spre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        spre= getSharedPreferences("myPref", MODE_PRIVATE);
        firstEnter = spre.getInt("flag", 0);
        setContentView(R.layout.activity_welcome);
        LinearLayout logoView = (LinearLayout) findViewById(R.id.launcher);
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(2000);
        logoView.setAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("test", firstEnter+"");
                if (firstEnter == 0) {
                    Intent intent = new Intent(WelcomeActivity.this, IntroActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(WelcomeActivity.this, ContractActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 2500);
    }
}
