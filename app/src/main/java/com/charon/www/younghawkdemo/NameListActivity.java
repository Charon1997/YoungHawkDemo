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

import com.charon.www.younghawkdemo.adapter.ItemAdapter;
import com.charon.www.younghawkdemo.temp.Date;
import com.charon.www.younghawkdemo.temp.Man;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NameListActivity extends AppCompatActivity {
    private Date date = new Date();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_namelist);
        final Intent intent = getIntent();
        final int oldPositon = intent.getIntExtra("position", -1);
        Log.d("test", oldPositon + "oldPosition");
        date.addDate();
        //设置toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.mail_nameList_toolbar);
        mToolbar.setTitle(date.teamList.get(oldPositon).getProjectName());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ListView mListView = (ListView) findViewById(R.id.mail_nameList);
        /*SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, getDate(oldPositon), R.layout.name_list_mail, new String[]{"name"}, new int[]{R.id.mail_list_teamName});
        mListView.setAdapter(mSimpleAdapter);*/
        mListView.setAdapter(new ItemAdapter(this,getDate(oldPositon)));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                Bundle bundle1 = new Bundle();
                Man man = date.teamList.get(oldPositon).getManList().get(i);
                bundle1.putSerializable("man", man);
                intent.setClass(NameListActivity.this, PersonalDateActivity.class);
                intent.putExtras(bundle1);
                startActivity(intent);
                Log.d("test", "跳转" + oldPositon + i);
            }
        });
    }


    private List<Map<String, Object>> getDate(int oldPosition) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < date.teamList.get(oldPosition).getManList().size(); i++) {
            Map<String, Object> map = new HashMap<>();
            String man = date.teamList.get(oldPosition).getManList().get(i).getName();
            map.put("name", man);
            list.add(map);
        }
        return list;
    }


}
