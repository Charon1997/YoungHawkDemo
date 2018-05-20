package com.charon.www.younghawkdemo.ui.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.charon.www.younghawkdemo.util.StatusUtil;
import com.charon.www.younghawkdemo.util.IContract;
import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.ui.Fragments.DiscussFragment;
import com.charon.www.younghawkdemo.ui.Fragments.HomeFragment;
import com.charon.www.younghawkdemo.ui.Fragments.MeFragment;
import com.charon.www.younghawkdemo.ui.Fragments.PlanFragment;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.ashokvarma.bottomnavigation.BottomNavigationBar.MODE_FIXED;
import static com.ashokvarma.bottomnavigation.BottomNavigationBar.MODE_SHIFTING;

/**
 * Created by Charon on 2017/4/24.
 */

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, IContract, BottomNavigationBar.OnTabSelectedListener {
    private Fragment currentNavFragment;
    private static int curIndex = 0;
    private boolean isQuit = false;
    private Timer timer = new Timer();
    private Toolbar toolbar;
    private BottomNavigationBar mBNBar;//底部导航
    private FloatingActionButton mFab;
    private OnFabClickListener1 onFabClickListener1 = new OnFabClickListener1();
    private OnFabClickListener2 onFabClickListener2 = new OnFabClickListener2();
    private OnFabClickListener3 onFabClickListener3 = new OnFabClickListener3();
    private final List<Fragment> fragmentNavPool = Arrays.asList(HomeFragment.getInstance(), PlanFragment.getInstance(), DiscussFragment.getInstance(), MeFragment.getInstance());
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParam(Bundle param) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_contract;
    }

    @Override
    public void initView(View view) {
        toolbar = $(R.id.toolbar);
        drawer = $(R.id.drawer_layout);
        navigationView = $(R.id.nav_view);
        mBNBar = $(R.id.bottom_nav_bar);
        mFab = $(R.id.main_fab);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {
        StatusUtil.initStatus(getWindow());
        initView();
        initData();
        setDefaultFragment();
    }

    private void initView() {

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        mBNBar.addItem(new BottomNavigationItem(R.drawable.nav_home, "主页"))
                .addItem(new BottomNavigationItem(R.drawable.nav_plan, "计划"))
                .addItem(new BottomNavigationItem(R.drawable.nav_discuss, "讨论"))
                .addItem(new BottomNavigationItem(R.drawable.nav_me, "我的"))
                .setActiveColor(R.color.colorPrimary)
                .setInActiveColor(R.color.inActive_icon)
                .setMode(MODE_FIXED)
                .initialise();
        mBNBar.setAutoHideEnabled(true);
        mBNBar.setTabSelectedListener(this);

        mFab.setOnClickListener(onFabClickListener1);
    }

    private void initData() {
        int index = 0;
        currentNavFragment = fragmentNavPool.get(index);
    }

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.content_contract, HomeFragment.getInstance());
        transaction.commit();
        toolbar.setTitle("主页");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(MainActivity.this, MailListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this, ManageActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(MainActivity.this, ProfessorActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(MainActivity.this, TableActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            showToast("Beta Version 1.2");
        }
        return true;
    }


    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                mFab.setVisibility(View.VISIBLE);
                switchFragment(currentNavFragment, fragmentNavPool.get(0));
                currentNavFragment = fragmentNavPool.get(0);
                toolbar.setTitle("主页");
                mFab.setOnClickListener(onFabClickListener1);
                curIndex = 0;
                break;
            case 1:
                mFab.setVisibility(View.VISIBLE);
                switchFragment(currentNavFragment, fragmentNavPool.get(1));
                currentNavFragment = fragmentNavPool.get(1);
                toolbar.setTitle("计划");
                mFab.setOnClickListener(onFabClickListener2);
                curIndex = 1;
                break;
            case 2:
                mFab.setVisibility(View.VISIBLE);
                switchFragment(currentNavFragment, fragmentNavPool.get(2));
                currentNavFragment = fragmentNavPool.get(2);
                toolbar.setTitle("讨论");
                mFab.setOnClickListener(onFabClickListener3);
                curIndex = 2;
                break;
            case 3:
                mFab.setVisibility(View.INVISIBLE);
                switchFragment(currentNavFragment, fragmentNavPool.get(3));
                currentNavFragment = fragmentNavPool.get(3);
                toolbar.setTitle("我的");
                curIndex =3;
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void switchFragment(Fragment from, Fragment to) {
        if (!to.isAdded()) {
            getFragmentManager().beginTransaction().add(R.id.content_contract, to).commit();
        }
        getFragmentManager().beginTransaction().hide(from).show(to).commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isQuit) {
                isQuit = true;
                showToast("点击两次退出");
                TimerTask task = null;
                task = new TimerTask() {
                    @Override
                    public void run() {
                        isQuit = false;
                    }
                };
                timer.schedule(task, 2000);
            } else {
                finish();
                System.exit(0);
            }
        }
        return true;
    }

    private class OnFabClickListener1 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(FabHomeActivity.class);
        }
    }

    private class OnFabClickListener2 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(FabPlanActivity.class);
        }
    }

    private class OnFabClickListener3 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity( FabDiscussActivity.class);
        }
    }

}
