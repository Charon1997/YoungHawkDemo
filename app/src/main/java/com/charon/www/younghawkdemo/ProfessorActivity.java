package com.charon.www.younghawkdemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.charon.www.younghawkdemo.adapter.ProfessorViewpageAdapter;

import java.util.ArrayList;
import java.util.List;


public class ProfessorActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;
    private List<View> list;
    private ProfessorViewpageAdapter adapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
        //实例化
        mViewPager = (ViewPager) findViewById(R.id.professor_viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.professor_tablayout);
        mToolbar = (Toolbar) findViewById(R.id.professor_toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        //加载view
        addView();
        adapter = new ProfessorViewpageAdapter(list);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void addView() {
        list = new ArrayList<>();
        View view1 = LayoutInflater.from(this).inflate(R.layout.fragment1_professor, null);
        list.add(view1);
        View view2 = LayoutInflater.from(this).inflate(R.layout.fragment2_professor, null);
        list.add(view2);
    }
}
