package com.charon.www.younghawkdemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import com.charon.www.younghawkdemo.adapter.BookViewpageAdapter;

import java.util.ArrayList;
import java.util.List;


public class BookActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;
    private List<View> list;

    private BookViewpageAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        //实例化
        mViewPager = (ViewPager) findViewById(R.id.book_viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.book_tablayout);
        mToolbar = (Toolbar) findViewById(R.id.book_toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        //
        addView();
        Log.d("test", ":"+list.size());
        adapter = new BookViewpageAdapter(list);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void addView() {
        list = new ArrayList<>();
        View view1 = LayoutInflater.from(this).inflate(R.layout.fragment1_book, null);
        list.add(view1);
        View view2 = LayoutInflater.from(this).inflate(R.layout.fragment2_book, null);
        list.add(view2);
        View view3 = LayoutInflater.from(this).inflate(R.layout.fragment3_book, null);
        list.add(view3);
    }

}
