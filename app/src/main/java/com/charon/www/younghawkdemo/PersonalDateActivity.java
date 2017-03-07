package com.charon.www.younghawkdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;


public class PersonalDateActivity extends AppCompatActivity {
    private final static int DATENUMBER = 5;
    private TextView mName,mDuty,qq,mPhoneNum,mEmail;
    private ImageView mImg;
    private String personalDate[] = new String[DATENUMBER];
    private Toolbar mToolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_personal);
        final Intent intent = getIntent();
        personalDate = intent.getStringArrayExtra("man");
        init();
        mToolbar.setTitle("通讯录");
        setSupportActionBar(mToolbar);
        addDate();
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
        mName.setText(personalDate[0]);
        mDuty.setText(personalDate[1]);
        mPhoneNum.setText(personalDate[2]);
        qq.setText(personalDate[3]);
        mEmail.setText(personalDate[4]);
    }
}
