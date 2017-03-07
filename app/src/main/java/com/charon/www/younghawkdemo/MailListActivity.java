package com.charon.www.younghawkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MailListActivity extends AppCompatActivity {
    private SimpleAdapter mSimpleAdapter;
    private ListView mListView;
    private Toolbar mToolbar;
    private String mListName[] = new String[]{"运营专责", "中学指导", "项目A"};
    private ListListener listListener = new ListListener();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        mListView = (ListView) findViewById(R.id.mail_list);
        mToolbar = (Toolbar) findViewById(R.id.mail_toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mSimpleAdapter = new SimpleAdapter(this, getData(), R.layout.project_list_mail, new String[]{"name"}, new int[]{R.id.mail_list_projectName});
        mListView.setAdapter(mSimpleAdapter);
        mListView.setOnItemClickListener(listListener);
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
            intent.setClass(MailListActivity.this, NameListActivity.class);
            Log.d("test", "跳转" + i);
            intent.putExtras(bundle1);
            startActivity(intent);
        }
    }
}
