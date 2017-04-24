package com.charon.www.younghawkdemo.ui.Activities;

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

public class IntroActivity extends AppCompatActivity {
    private LinearLayout indicatorPage1Show;
    private LinearLayout indicatorPage2Show;
    private SharedPreferences spre;
    private TextView next;
    private TextView finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

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
        indicatorPage1Show = (LinearLayout) findViewById(R.id.indicator_page1_show);
        indicatorPage2Show = (LinearLayout) findViewById(R.id.indicator_page2_show);
        next = (TextView) findViewById(R.id.text_next);
        finish = (TextView) findViewById(R.id.text_finish);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.intro_pager);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = spre.edit();
                editor.putInt("flag", 1);
                editor.apply();
                Intent intent = new Intent(IntroActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
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
