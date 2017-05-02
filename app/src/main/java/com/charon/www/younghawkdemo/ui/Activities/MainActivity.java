package com.charon.www.younghawkdemo.ui.Activities;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.charon.www.younghawkdemo.Costum.StatusUtil;
import com.charon.www.younghawkdemo.IContract;
import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.biz.ScrollAwareFABBehavior;
import com.charon.www.younghawkdemo.ui.Fragments.DiscussFragment;
import com.charon.www.younghawkdemo.ui.Fragments.HomeFragment;
import com.charon.www.younghawkdemo.ui.Fragments.MeFragment;
import com.charon.www.younghawkdemo.ui.Fragments.PlanFragment;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.ashokvarma.bottomnavigation.BottomNavigationBar.MODE_SHIFTING;

/**
 * Created by Administrator on 2017/4/24.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, IContract, BottomNavigationBar.OnTabSelectedListener{
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    private Fragment currentNavFragment;//底部导航
    private boolean isQuit = false;
    private Timer timer = new Timer();
    private Toolbar toolbar;
    private BottomNavigationBar mBNBar;
    private FloatingActionButton mFab;
    private OnFabClickListener1 onFabClickListener1 = new OnFabClickListener1();
    private OnFabClickListener2 onFabClickListener2 = new OnFabClickListener2();
    private OnFabClickListener3 onFabClickListener3 = new OnFabClickListener3();
    private final List<Fragment> fragmentNavPool = Arrays.asList(HomeFragment.getInstance(), PlanFragment.getInstance(), DiscussFragment.getInstance(), MeFragment.getInstance());
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusUtil.initStatus(getWindow());
        setContentView(R.layout.activity_contract);
        initView();
        initData();
        setDefaultFragment();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mBNBar = (BottomNavigationBar) findViewById(R.id.bottom_nav_bar);
        mBNBar.addItem(new BottomNavigationItem(R.drawable.nav_home, "主页"))
                .addItem(new BottomNavigationItem(R.drawable.nav_plan, "计划"))
                .addItem(new BottomNavigationItem(R.drawable.nav_discuss, "讨论"))
                .addItem(new BottomNavigationItem(R.drawable.nav_me,"我的"))
                .setActiveColor(R.color.colorPrimary)
                .setMode(MODE_SHIFTING)
                .initialise();
        mBNBar.setAutoHideEnabled(true);
        mBNBar.setTabSelectedListener(this);

        mFab = (FloatingActionButton) findViewById(R.id.main_fab);
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
            Toast.makeText(this, "Beta Version", Toast.LENGTH_SHORT).show();
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
                break;
            case 1:
                mFab.setVisibility(View.VISIBLE);
                switchFragment(currentNavFragment, fragmentNavPool.get(1));
                currentNavFragment = fragmentNavPool.get(1);
                toolbar.setTitle("计划");
                mFab.setOnClickListener(onFabClickListener2);
                break;
            case 2:
                mFab.setVisibility(View.VISIBLE);
                switchFragment(currentNavFragment, fragmentNavPool.get(2));
                currentNavFragment = fragmentNavPool.get(2);
                toolbar.setTitle("讨论");
                mFab.setOnClickListener(onFabClickListener3);
                break;
            case 3:
                mFab.setVisibility(View.INVISIBLE);
                switchFragment(currentNavFragment, fragmentNavPool.get(3));
                currentNavFragment = fragmentNavPool.get(3);
                toolbar.setTitle("我的");
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
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
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

    private class OnFabClickListener1 implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "首页", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, FabHomeActivity.class);
            startActivity(intent);
        }
    }
    private class OnFabClickListener2 implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "计划", Toast.LENGTH_SHORT).show();
        }
    }
    private class OnFabClickListener3 implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "讨论", Toast.LENGTH_SHORT).show();
        }
    }
}
