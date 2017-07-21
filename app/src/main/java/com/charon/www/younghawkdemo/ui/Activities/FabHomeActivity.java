package com.charon.www.younghawkdemo.ui.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.charon.www.younghawkdemo.R;

import static com.charon.www.younghawkdemo.model.Constant.HOME_CONTENT;

/**
 * Created by Charon on 2017/5/2.
 */

public class FabHomeActivity extends AppCompatActivity {
    private EditText mEditText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_home);
        initView();
    }

    private void initView() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.fab_home_toolbar);
        mEditText = (EditText) findViewById(R.id.fab_home_edit);
        mToolbar.setTitle("发动态");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mEditText.setText(getIntent().getStringExtra(HOME_CONTENT));
        mEditText.setSelection(mEditText.getText().toString().length());
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
            Toast.makeText(this, "" + mEditText.getText(), Toast.LENGTH_SHORT).show();
            //上传
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
