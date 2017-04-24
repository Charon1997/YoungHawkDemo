package com.charon.www.younghawkdemo.ui.Fragments;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.ui.adapter.ProfessorViewpageAdapter;
import com.zzhoujay.richtext.RichText;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfessorFragment extends android.app.Fragment {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<View> list;
    private ProfessorViewpageAdapter adapter;
    private static ProfessorFragment instance;

    public ProfessorFragment() {

    }
    public static ProfessorFragment getInstance(){
        if(instance == null){
            instance = new ProfessorFragment();
        }
        return instance;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_professor, container, false);

        mViewPager = (ViewPager) view.findViewById(R.id.professor_viewpager);
        mTabLayout = (TabLayout) view.findViewById(R.id.professor_tablayout);

        //加载view
        addView();
        adapter = new ProfessorViewpageAdapter(list);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void addView() {
        list = new ArrayList<>();
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.fragment1_professor, null);

        //MarkDown
        TextView textProfessorChen = (TextView) view1.findViewById(R.id.professor_chen);
        RichText.fromMarkdown(getString(R.string.text_professor_chen))
                .autoFix(true)
                .noImage(true)
                .into(textProfessorChen);

        list.add(view1);
        View view2 = LayoutInflater.from(getContext()).inflate(R.layout.fragment2_professor, null);

        TextView textProfessorTang = (TextView) view2.findViewById(R.id.professor_tang);
        RichText.fromMarkdown(getString(R.string.text_professor_tang))
                .autoFix(true)
                .noImage(true)
                .into(textProfessorTang);

        list.add(view2);
    }

}
