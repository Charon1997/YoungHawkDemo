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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class NameListActivity extends AppCompatActivity {
    private SimpleAdapter mSimpleAdapter;
    private ListView mListView;
    private Toolbar mToolbar;
    private Date date = new Date();
    /*private String mMemberName0[] = new String[]{"1成员A", "1成员B", "1成员C", "1成员D", "1成员E", "1成员F", "1成员G"};
    private String mMemberName1[] = new String[]{"2成员A", "1成员B", "1成员C", "1成员D", "1成员E", "1成员F", "1成员G"};
    private String mMemberName2[] = new String[]{"3成员A", "1成员B", "1成员C", "1成员D", "1成员E", "1成员F", "1成员G"};
    private String mMemberName3[] = new String[]{"4成员A", "1成员B", "1成员C", "1成员D", "1成员E", "1成员F", "1成员G"};
    private String mMemberName4[] = new String[]{"5成员A", "1成员B", "1成员C", "1成员D", "1成员E", "1成员F", "1成员G"};
    private String mMemberName5[] = new String[]{"6成员A", "1成员B", "1成员C", "1成员D", "1成员E", "1成员F", "1成员G"};
    private String mMemberName6[] = new String[]{"7成员A", "1成员B", "1成员C", "1成员D", "1成员E", "1成员F", "1成员G"};*/


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_namelist);
        final Intent intent = getIntent();
        final int oldPositon = intent.getIntExtra("position", -1);
        Log.d("test", oldPositon+"oldPosition");
        mToolbar = (Toolbar) findViewById(R.id.mail_nameList_toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        date.addToTeam();
        mListView = (ListView) findViewById(R.id.mail_nameList);
        mSimpleAdapter = new SimpleAdapter(this, getDate(oldPositon), R.layout.name_list_mail, new String[]{"name"}, new int[]{R.id.mail_list_teamName});
        mListView.setAdapter(mSimpleAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                Bundle bundle1 = new Bundle();
                String man[] = date.projectList.get(oldPositon).get(i);
                bundle1.putStringArray("man", man);
                intent.setClass(NameListActivity.this, PersonalDateActivity.class);
                intent.putExtras(bundle1);
                startActivity(intent);
                Log.d("test", "跳转" + oldPositon + i );
            }
        });
    }


    private List<Map<String, Object>> getDate(int oldPosition) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < date.projectList.get(oldPosition).size(); i++) {
            Map<String, Object> map = new HashMap<>();
            String man[] = date.projectList.get(oldPosition).get(i);
            map.put("name", man[0]);//man[0]是名字
            list.add(map);
        }
        return list;
    }



}
