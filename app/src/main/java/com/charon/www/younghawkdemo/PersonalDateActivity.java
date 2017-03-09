package com.charon.www.younghawkdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;


public class PersonalDateActivity extends AppCompatActivity {
    private TextView mName,mDuty,qq,mPhoneNum,mEmail;
    private ImageView mImg;
    private Toolbar mToolbar;
    private Man man = new Man();
    private int dif = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_personal);
        Bundle bundle=getIntent().getExtras();
        man=(Man)bundle.getSerializable("man");
        dif = bundle.getInt("key");
        init();
        mToolbar.setTitle(man.getName());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (man != null) {
            addDate();
        }
    }

    private void init() {
        mImg = (ImageView) findViewById(R.id.mail_personalImg);
        mName = (TextView) findViewById(R.id.mail_personalName);
        mDuty = (TextView) findViewById(R.id.mail_personalDuty);
        qq = (TextView) findViewById(R.id.mail_personalQQ);
        mPhoneNum = (TextView) findViewById(R.id.mail_personalPhoneNum);
        mEmail = (TextView) findViewById(R.id.mail_personalEmail);
        mToolbar = (Toolbar) findViewById(R.id.mail_personal_toolbar);
    }

    private void addDate() {
        switch (dif) {
            case 1:
                mDuty.setText("运营专责牵头人");
                break;
            case 2:
                mDuty.setText("牵头人   项目指导");
                break;
            default:
                mDuty.setText(man.getDuty());
                break;
        }
        mName.setText(man.getName());
        mPhoneNum.setText(man.getPhoneNum());
        qq.setText(man.getQQNum());
        mEmail.setText(man.getEmail());
    }
}
