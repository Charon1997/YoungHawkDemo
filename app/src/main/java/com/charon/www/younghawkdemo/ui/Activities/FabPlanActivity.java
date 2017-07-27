package com.charon.www.younghawkdemo.ui.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.presenter.FabPresenter;
import com.charon.www.younghawkdemo.view.IBaseFabView;

import static com.charon.www.younghawkdemo.model.Constant.PLAN_PLAN;
import static com.charon.www.younghawkdemo.model.Constant.PLAN_SUMMARY;

/**
 * Created by Charon on 2017/5/3.
 */

public class FabPlanActivity extends BaseActivity implements IBaseFabView {
    private EditText mEtSummary,mEtPlan;
    private Toolbar mToolbar;
    private LinearLayout mPb;
    private FabPresenter fabPresenter = new FabPresenter(this);
    @Override
    public void widgetClick(View v) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        return R.layout.activity_fab_plan;
    }

    @Override
    public void initView(View view) {
        mPb = $(R.id.fab_plan_pb);
        mToolbar =$(R.id.fab_plan_toolbar);
        mEtSummary =$(R.id.fab_plan_et_summary);
        mEtPlan =$(R.id.fab_plan_et_plan);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {
        mToolbar.setTitle("总结与计划");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mEtSummary.setText(getIntent().getStringExtra(PLAN_SUMMARY));
        mEtSummary.setSelection(mEtSummary.getText().toString().length());
        mEtPlan.setText(getIntent().getStringExtra(PLAN_PLAN));
        mEtPlan.setSelection(mEtPlan.getText().toString().length());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fab_home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.fab_home_release) {
            send();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void send() {
        //上传
        showToast("总结：" + mEtSummary.getText()+"计划："+mEtPlan.getText());
        fabPresenter.sendPlan(mEtPlan.getText().toString(),mEtSummary.getText().toString());
    }

    @Override
    public void loading(boolean loading) {
        if (loading) {
            mPb.setVisibility(View.VISIBLE);
        } else mPb.setVisibility(View.GONE);
    }

    @Override
    public void onError() {
        Snackbar.make(mPb, R.string.error_network, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void finishActivity() {
        showToast("发送成功");
        finish();
    }
}
