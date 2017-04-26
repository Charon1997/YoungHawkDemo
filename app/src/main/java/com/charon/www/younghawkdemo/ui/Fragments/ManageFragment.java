package com.charon.www.younghawkdemo.ui.Fragments;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.ui.adapter.BookViewpagerAdapter;
import com.zzhoujay.richtext.RichText;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageFragment extends android.app.Fragment {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<View> list;
    private BookViewpagerAdapter adapter;
    private static ManageFragment instance;

    public ManageFragment() {

    }
    public static ManageFragment getInstance(){
        if(instance == null){
            instance = new ManageFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.manage_viewpager);
        mTabLayout = (TabLayout) view.findViewById(R.id.manage_tablayout);
        //
        addView();
        Log.d("test", ":" + list.size());
        adapter = new BookViewpagerAdapter(list);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        return view;
    }
    @TargetApi(Build.VERSION_CODES.M)
    private void addView() {
        list = new ArrayList<>();
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.fragment1_book, null);

        TextView textTimeCommunicate = (TextView) view1.findViewById(R.id.time_communicate);
        RichText.fromMarkdown(getString(R.string.text_time_communicate))
                .autoFix(true)
                .noImage(true)
                .into(textTimeCommunicate);

        list.add(view1);
        View view2 = LayoutInflater.from(getContext()).inflate(R.layout.fragment2_book, null);

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
        View view3 = LayoutInflater.from(getContext()).inflate(R.layout.fragment3_book, null);

        TextView textPlatformRules = (TextView) view3.findViewById(R.id.text_platform_rules);
        RichText.fromMarkdown(getString(R.string.text_platform_rules))
                .autoFix(true)
                .noImage(true)
                .into(textPlatformRules);

        list.add(view3);
    }

}
