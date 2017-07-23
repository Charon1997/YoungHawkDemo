package com.charon.www.younghawkdemo.ui.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.ui.adapter.ItemAdapter;
import com.charon.www.younghawkdemo.temp.Date;
import com.charon.www.younghawkdemo.temp.Man;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NameListActivity extends BaseActivity {
    private Date date = new Date();
    private Toolbar mToolbar;
    private ListView mListView;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_mail_namelist;
    }

    @Override
    public void initView(View view) {
        mToolbar = $(R.id.mail_nameList_toolbar);
        mListView = $(R.id.mail_nameList);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {
        final Intent intent = getIntent();
        final int oldPositon = intent.getIntExtra("position", -1);
        Log.d("test", oldPositon + "oldPosition");
        date.addDate();
        //设置toolbar

        mToolbar.setTitle(date.teamList.get(oldPositon).getProjectName());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mListView.setAdapter(new ItemAdapter(this, getDate(oldPositon)));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                Bundle bundle1 = new Bundle();
                Man man = date.teamList.get(oldPositon).getManList().get(i);
                bundle1.putSerializable("man", man);
                startActivity(PersonalDateActivity.class, bundle1);
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
