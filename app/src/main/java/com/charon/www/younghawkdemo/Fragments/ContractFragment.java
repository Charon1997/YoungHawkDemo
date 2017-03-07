package com.charon.www.younghawkdemo.Fragments;


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.charon.www.younghawkdemo.Date;
import com.charon.www.younghawkdemo.NameListActivity;
import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.adapter.MailViewpageAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContractFragment extends android.app.Fragment {
    private Date date = new Date();
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MailViewpageAdapter adapter;
    private ListView mListView;
    private SimpleAdapter mSimpleAdapter;
    private String mListName[] = new String[]{"运营专责", "中学指导", "项目A"};
    private static ContractFragment instance;
    private List<View> list;
    private ListListener listListener = new ListListener();
    public ContractFragment() {

    }

    public static ContractFragment getInstance() {
        if (instance == null) {
            instance = new ContractFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maillist, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.mail_viewpager);
        mTabLayout = (TabLayout) view.findViewById(R.id.mail_tablayout);
        date.addToTeam();
        addView();
        adapter = new MailViewpageAdapter(list);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mListView.setOnItemClickListener(listListener);
        return view;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void addView() {
        list = new ArrayList<>();
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.fragment1_maillist_project, null);
        mListView = (ListView) view1.findViewById(R.id.maillist1);
        mSimpleAdapter = new SimpleAdapter(getActivity(), getData(), R.layout.project_list_mail, new String[]{"name"}, new int[]{R.id.mail_list_projectName});
        mListView.setAdapter(mSimpleAdapter);
        list.add(view1);

        View view2 = LayoutInflater.from(getContext()).inflate(R.layout.fragment2_maillist_project, null);

        list.add(view2);
    }
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (String aMListName : mListName) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", aMListName);
            list.add(map);
        }
        return list;
    }
    private class ListListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent();
            Bundle bundle1 = new Bundle();
            bundle1.putInt("position",i);
            intent.setClass(getActivity(), NameListActivity.class);
            Log.d("test", "跳转" + i);
            intent.putExtras(bundle1);
            startActivity(intent);
        }
    }

}
