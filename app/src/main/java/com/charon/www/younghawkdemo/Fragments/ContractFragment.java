package com.charon.www.younghawkdemo.Fragments;


import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.charon.www.younghawkdemo.adapter.ItemAdapter;
import com.charon.www.younghawkdemo.temp.Date;
import com.charon.www.younghawkdemo.temp.Man;
import com.charon.www.younghawkdemo.NameListActivity;
import com.charon.www.younghawkdemo.PersonalDateActivity;
import com.charon.www.younghawkdemo.PinyinComparator;
import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.adapter.MailViewpageAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


public class ContractFragment extends android.app.Fragment {
    private int firstEnter;
    private Date date = new Date();
    private ListView mListView1,mListView2;
    private static ContractFragment instance;
    private List<View> list;
    private List<String> mListName = new ArrayList<>();
    private ListListener1 listListener1 = new ListListener1();
    private ListListener2 listListener2 = new ListListener2();
    private PinyinComparator comparator = new PinyinComparator();
    SharedPreferences spre;
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
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.mail_viewpager);
        TabLayout mTabLayout = (TabLayout) view.findViewById(R.id.mail_tablayout);
        spre= getActivity().getSharedPreferences("myPref", MODE_PRIVATE);
        firstEnter = spre.getInt("flag", 0);
        Log.d("test",firstEnter+"");
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

        MailViewpageAdapter adapter = new MailViewpageAdapter(list);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        //设置监听
        mListView1.setOnItemClickListener(listListener1);
        mListView2.setOnItemClickListener(listListener2);
        return view;
    }

    //得到所有的名字
    private void getName() {
        for (int i = 0, j = 0; j < date.teamList.size(); j++) {
            for (int k = 0; k < date.teamList.get(j).getManList().size(); k++) {
                mListName.add(date.teamList.get(j).getManList().get(k).getName());
                i++;
            }
        }
        mListName.remove(1);
        mListName.remove(7);
    }



    @TargetApi(Build.VERSION_CODES.M)
    private void addView() {
        list = new ArrayList<>();
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.fragment1_maillist_project, null);
        mListView1 = (ListView) view1.findViewById(R.id.maillist1);
        /*SimpleAdapter mSimpleAdapter1 = new SimpleAdapter(getActivity(), getData(), R.layout.project_list_mail, new String[]{"name"}, new int[]{R.id.mail_list_projectName});*/
       /* mListView1.setAdapter(mSimpleAdapter1);*/
        mListView1.setAdapter(new ItemAdapter(getContext(),getData()));
        list.add(view1);

        View view2 = LayoutInflater.from(getContext()).inflate(R.layout.fragment2_maillist_project, null);
        mListView2 = (ListView) view2.findViewById(R.id.maillist2);
        /*SimpleAdapter mSimpleAdapter2 = new SimpleAdapter(getActivity(), getData2(), R.layout.name_list_mail, new String[]{"name"}, new int[]{R.id.mail_list_teamName});
        mListView2.setAdapter(mSimpleAdapter2);*/
        mListView2.setAdapter(new ItemAdapter(getContext(),getData2()));
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
            intent.setClass(getActivity(), NameListActivity.class);
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
            intent.setClass(getActivity(), PersonalDateActivity.class);
            Log.d("test", "跳转" + i);
            intent.putExtras(bundle1);
            startActivity(intent);
        }
    }


}
