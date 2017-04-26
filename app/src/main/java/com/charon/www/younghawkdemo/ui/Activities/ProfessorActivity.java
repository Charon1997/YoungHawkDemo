package com.charon.www.younghawkdemo.ui.Activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.ui.adapter.ProfessorViewpagerAdapter;
import com.zzhoujay.richtext.RichText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class ProfessorActivity extends AppCompatActivity {
    private List<View> list;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_professor);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.professor_viewpager);
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.professor_tablayout);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.professor_toolbar);
        mToolbar.setTitle("专家");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //加载view
        addView();
        ProfessorViewpagerAdapter adapter = new ProfessorViewpagerAdapter(list);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void addView() {
        list = new ArrayList<>();
        View view1 = LayoutInflater.from(this).inflate(R.layout.fragment1_professor, null);

        //MarkDown
        TextView textProfessorChen = (TextView) view1.findViewById(R.id.professor_chen);
        RichText.fromMarkdown(getString(R.string.text_professor_chen))
                .autoFix(true)
                .noImage(true)
                .into(textProfessorChen);

        list.add(view1);
        View view2 = LayoutInflater.from(this).inflate(R.layout.fragment2_professor, null);

        TextView textProfessorTang = (TextView) view2.findViewById(R.id.professor_tang);
        RichText.fromMarkdown(getString(R.string.text_professor_tang))
                .autoFix(true)
                .noImage(true)
                .into(textProfessorTang);

        list.add(view2);
    }
}
