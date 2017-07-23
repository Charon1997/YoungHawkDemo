package com.charon.www.younghawkdemo.ui.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.charon.www.younghawkdemo.R;

import static com.charon.www.younghawkdemo.R.layout.activity_fab_discuss;
import static com.charon.www.younghawkdemo.model.Constant.DISCUSS_CONTENT;
import static com.charon.www.younghawkdemo.model.Constant.DISCUSS_TITLE;

/**
 * Created by Charon on 2017/5/3.
 */

public class FabDiscussActivity extends BaseActivity {
    private EditText mEtTitle,mEtContent;
    private Toolbar mToolbar;
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
        return activity_fab_discuss;
    }

    @Override
    public void initView(View view) {
        mEtTitle=$(R.id.fab_discuss_et_title);
        mEtContent = $(R.id.fab_discuss_et_content);
        mToolbar = $(R.id.fab_discuss_toolbar);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {
        mToolbar.setTitle("讨论与集中");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mEtTitle.setText(getIntent().getStringExtra(DISCUSS_TITLE));
        mEtContent.setText(getIntent().getStringExtra(DISCUSS_CONTENT));
        mEtTitle.setSelection(mEtTitle.getText().toString().length());
        mEtContent.setSelection(mEtContent.getText().toString().length());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fab_home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.fab_home_release) {
            Toast.makeText(this, "标题" + mEtTitle.getText()+"内容"+mEtContent.getText(), Toast.LENGTH_SHORT).show();
            //上传
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
