package com.charon.www.younghawkdemo.ui.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.charon.www.younghawkdemo.ui.Fragments.IntroPage1Fragment;
import com.charon.www.younghawkdemo.ui.Fragments.IntroPage2Fragment;
import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.ui.adapter.IntroFragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends BaseActivity {
    private LinearLayout indicatorPage1Show;
    private LinearLayout indicatorPage2Show;
    private SharedPreferences spre;
    private TextView next;
    private TextView finish;
    private ViewPager viewPager;
    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.text_next:
                viewPager.setCurrentItem(1);
                break;
            case R.id.text_finish:
                SharedPreferences.Editor editor = spre.edit();
                editor.putInt("flag", 1);
                editor.apply();
                startActivity(LoginActivity.class);
                finish();
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
        return R.layout.activity_intro;
    }

    @Override
    public void initView(View view) {
        indicatorPage1Show = $(R.id.indicator_page1_show);
        indicatorPage2Show = $(R.id.indicator_page2_show);
        next = $(R.id.text_next);
        finish = $(R.id.text_finish);
        viewPager =$(R.id.intro_pager);
    }

    @Override
    public void setListener() {
        next.setOnClickListener(this);
        finish.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {
        spre= getSharedPreferences("myPref", MODE_PRIVATE);
        initView(getData());
    }

    private List<Fragment> getData() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new IntroPage1Fragment());
        fragments.add(new IntroPage2Fragment());
        return fragments;
    }

    private void initView(List<Fragment> fragments) {
        viewPager.setAdapter(new IntroFragmentStatePagerAdapter(getSupportFragmentManager(),fragments) {
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    indicatorPage1Show.setVisibility(View.VISIBLE);
                    indicatorPage2Show.setVisibility(View.GONE);
                    finish.setVisibility(View.GONE);
                    next.setVisibility(View.VISIBLE);
                }else if(position == 1){
                    indicatorPage1Show.setVisibility(View.GONE);
                    indicatorPage2Show.setVisibility(View.VISIBLE);
                    finish.setVisibility(View.VISIBLE);
                    next.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
