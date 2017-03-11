package com.charon.www.younghawkdemo.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.temp.Man;


public class PersonalDateActivity extends AppCompatActivity {
    private TextView mDuty,qq,mPhoneNum,mEmail;
    private Man man = new Man();
    private int dif = 0;
    private FloatingActionButton mFloatingActionButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_person);
        init();
        CollapsingToolbarLayout mToolbarlayout = (CollapsingToolbarLayout) findViewById(R.id.person_toolbar_layout);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.person_toolbar);
        Bundle bundle=getIntent().getExtras();
        man=(Man)bundle.getSerializable("man");
        dif = bundle.getInt("key");
        assert man != null;
        mToolbarlayout.setTitle(man.getName());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + man.getPhoneNum());
                intent.setData(data);
                startActivity(intent);
            }
        });
        setDate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return false;
    }

    private void init() {
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mDuty = (TextView) findViewById(R.id.person_duty);
        qq = (TextView) findViewById(R.id.person_qq);
        mPhoneNum = (TextView) findViewById(R.id.person_phone);
        mEmail = (TextView) findViewById(R.id.person_email);
    }

    private void setDate() {
        switch (dif) {
            case 1:
                mDuty.setText("运营专责&&牵头人");
                break;
            case 2:
                mDuty.setText("牵头人&&项目指导");
                break;
            default:
                mDuty.setText(man.getDuty());
                break;
        }
        mPhoneNum.setText(man.getPhoneNum());
        qq.setText(man.getQQNum());
        mEmail.setText(man.getEmail());
    }
}
