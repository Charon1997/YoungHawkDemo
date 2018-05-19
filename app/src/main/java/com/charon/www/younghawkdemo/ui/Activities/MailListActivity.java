package com.charon.www.younghawkdemo.ui.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.charon.www.younghawkdemo.util.PinyinComparator;
import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.temp.Date;
import com.charon.www.younghawkdemo.temp.Man;
import com.charon.www.younghawkdemo.ui.adapter.ItemAdapter;
import com.charon.www.younghawkdemo.ui.adapter.MailViewpagerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Charon on 2017/4/24.
 */

public class MailListActivity extends BaseActivity{
    private Date date = new Date();
    private ListView mListView1,mListView2;
    private List<View> list;
    private List<String> mListName = new ArrayList<>();
    private MailListActivity.ListListener1 listListener1 = new MailListActivity.ListListener1();
    private MailListActivity.ListListener2 listListener2 = new MailListActivity.ListListener2();
    private PinyinComparator comparator = new PinyinComparator();
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;

    SharedPreferences spre;

    @Override
    public void widgetClick(View v) {

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
        return R.layout.fragment_maillist;
    }

    @Override
    public void initView(View view) {
        mViewPager = $(R.id.mail_list_viewpager);
        mTabLayout = $(R.id.mail_list_tablayout);
        mToolbar =$(R.id.mail_list_toolbar);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {
        // TODO: 2018/4/13  加入pb过渡
        mToolbar.setTitle("通讯录");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        spre= getSharedPreferences("myPref", MODE_PRIVATE);
        int firstEnter = spre.getInt("flag", 0);
        Log.d("test", firstEnter +"");
        date.addDate();
        if (firstEnter == 1) {
            getName();
            Collections.sort(mListName, comparator);//按字母排序
            SharedPreferences.Editor editor = spre.edit();
            editor.putInt("nameSize", mListName.size());
            editor.putInt("flag", 2);
            Log.d("test", mListName.toString());
            //list -> string
            for (int i = 0; i < mListName.size();i++) {
                editor.putString("nameList"+i, mListName.get(i));
            }
            editor.apply();
        }else {// string -> list
            for (int i = 0; i < spre.getInt("nameSize",0);i++) {
                mListName.add(spre.getString("nameList" + i, "1"));
            }
            Log.d("test", mListName.toString());
        }
        addView();

        MailViewpagerAdapter adapter = new MailViewpagerAdapter(list);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        //设置监听
        mListView1.setOnItemClickListener(listListener1);
        mListView2.setOnItemClickListener(listListener2);
    }


    //得到所有的名字
    private void getName() {
        for (int j = 0; j < date.teamList.size(); j++) {
            for (int k = 0; k < date.teamList.get(j).getManList().size(); k++) {
                mListName.add(date.teamList.get(j).getManList().get(k).getName());
            }
        }
        mListName.remove(1);
        mListName.remove(7);
    }


    private void addView() {
        list = new ArrayList<>();
        View view1 = LayoutInflater.from(this).inflate(R.layout.fragment1_maillist_project, null);
        mListView1 = (ListView) view1.findViewById(R.id.maillist1);
        mListView1.setAdapter(new ItemAdapter(this,getData()));
        list.add(view1);

        View view2 = LayoutInflater.from(this).inflate(R.layout.fragment2_maillist_project, null);
        mListView2 = (ListView) view2.findViewById(R.id.maillist2);
        mListView2.setAdapter(new ItemAdapter(this,getData2()));
        list.add(view2);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0 ;i< date.teamList.size();i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", date.teamList.get(i).getProjectName());
            list.add(map);
        }
        return list;
    }
    private List<Map<String, Object>> getData2() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < mListName.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", mListName.get(i));
            list.add(map);
        }
        return list;
    }

    private class ListListener1 implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent();
            Bundle bundle1 = new Bundle();
            bundle1.putInt("position", i);
            intent.setClass(MailListActivity.this, NameListActivity.class);
            Log.d("test", "跳转" + i);
            intent.putExtras(bundle1);
            startActivity(intent);
        }
    }
    private class ListListener2 implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String clickName = mListName.get(i);
            Man man = new Man();
            int found = 0;
            int difMan = 0;
            for (int j = 0;j<date.teamList.size();j++) {
                for (int k = 0; k < date.teamList.get(j).getManList().size();k++) {
                    if (clickName.equals( date.teamList.get(j).getManList().get(k).getName())) {
                        man = date.teamList.get(j).getManList().get(k);
                        if (date.teamList.get(j).getManList().get(k).getName().equals("陈思捷")){
                            difMan = 1;
                        }else if (date.teamList.get(j).getManList().get(k).getName().equals("匡政泽")){
                            difMan = 2;
                        }
                        found = 1;
                        break;
                    }
                }
                if (found == 1)
                    break;
            }
            Intent intent = new Intent();
            Bundle bundle1 = new Bundle();
            bundle1.putInt("key",difMan);
            bundle1.putSerializable("man", man);
            intent.setClass(MailListActivity.this, PersonalDateActivity.class);
            Log.d("test", "跳转" + i);
            intent.putExtras(bundle1);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
