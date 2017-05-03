package com.charon.www.younghawkdemo.ui.Activities;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.ui.adapter.BookViewpagerAdapter;
import com.zzhoujay.richtext.RichText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charon on 2017/4/24.
 */

public class ManageActivity extends AppCompatActivity {
    private List<View> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_manage);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.manage_viewpager);
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.manage_tablayout);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.manage_toolbar);
        mToolbar.setTitle("管理手册");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addView();
        Log.d("test", ":" + list.size());
        BookViewpagerAdapter adapter = new BookViewpagerAdapter(list);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void addView() {
        list = new ArrayList<>();
        View view1 = LayoutInflater.from(this).inflate(R.layout.fragment1_book, null);

        TextView textTimeCommunicate = (TextView) view1.findViewById(R.id.time_communicate);
        RichText.fromMarkdown(getString(R.string.text_time_communicate))
                .autoFix(true)
                .noImage(true)
                .into(textTimeCommunicate);

        list.add(view1);
        View view2 = LayoutInflater.from(this).inflate(R.layout.fragment2_book, null);

        TextView textArrange1 = (TextView) view2.findViewById(R.id.text_arrange1);
        TextView textArrange2 = (TextView) view2.findViewById(R.id.text_arrange2);
        RichText.fromMarkdown(getString(R.string.text_arrange1))
                .autoFix(true)
                .noImage(true)
                .into(textArrange1);
        RichText.fromMarkdown(getString(R.string.text_arrange2))
                .autoFix(true)
                .noImage(true)
                .into(textArrange2);

        list.add(view2);
        View view3 = LayoutInflater.from(this).inflate(R.layout.fragment3_book, null);

        TextView textPlatformRules = (TextView) view3.findViewById(R.id.text_platform_rules);
        RichText.fromMarkdown(getString(R.string.text_platform_rules))
                .autoFix(true)
                .noImage(true)
                .into(textPlatformRules);

        list.add(view3);
    }
}
