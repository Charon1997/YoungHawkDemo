package com.charon.www.younghawkdemo.ui.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.charon.www.younghawkdemo.ui.Fragments.ContractFragment;
import com.charon.www.younghawkdemo.ui.Fragments.DiscussFragment;
import com.charon.www.younghawkdemo.ui.Fragments.HomeFragment;
import com.charon.www.younghawkdemo.ui.Fragments.TableFragment;
import com.charon.www.younghawkdemo.IContract;
import com.charon.www.younghawkdemo.ui.Fragments.ManageFragment;
import com.charon.www.younghawkdemo.ui.Fragments.ProfessorFragment;
import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.Costum.StatusUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.ashokvarma.bottomnavigation.BottomNavigationBar.MODE_SHIFTING;

public class ContractActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IContract, BottomNavigationBar.OnTabSelectedListener {
    private Fragment currentFragment;
    private Fragment currentNavFragment;//底部导航
    private boolean isQuit = false;
    private Timer timer = new Timer();
    private Toolbar toolbar;
    private BottomNavigationBar mBNBar;
    private final List<Fragment> fragmentPool = Arrays.asList(ContractFragment.getInstance(), ManageFragment.getInstance(), ProfessorFragment.getInstance(), TableFragment.getInstance());
    private final List<Fragment> fragmentNavPool = Arrays.asList(HomeFragment.getInstance(), DiscussFragment.getInstance());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusUtil.initStatus(getWindow());
        setContentView(R.layout.activity_contract);
        //getFragmentManager().beginTransaction().add(R.id.content_contract, HomeFragment.getInstance()).commit();
        initView();
        initData();
        setDefaultFragment();
    }

    private void initData() {
        int index = 0;
        currentFragment = fragmentPool.get(index);
        //getFragmentManager().beginTransaction().show(currentFragment).commit();
        currentNavFragment = fragmentNavPool.get(index);
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
                .addItem(new BottomNavigationItem(R.drawable.nav_discuss, "讨论"))
                .setActiveColor(R.color.colorPrimary)
                .setMode(MODE_SHIFTING)
                .initialise();
        mBNBar.setAutoHideEnabled(true);
        mBNBar.setTabSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.content_contract, HomeFragment.getInstance());
        transaction.commit();
        toolbar.setTitle("主页");
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        switchFragment(currentNavFragment,fragmentNavPool.get(1));
        currentNavFragment = fragmentNavPool.get(1);
        getFragmentManager().beginTransaction().hide(fragmentNavPool.get(1)).commit();
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            switchFragment(currentFragment, fragmentPool.get(0));
            currentFragment = fragmentPool.get(0);
            toolbar.setTitle(R.string.mailList);
        } else if (id == R.id.nav_gallery) {
            switchFragment(currentFragment, fragmentPool.get(1));
            currentFragment = fragmentPool.get(1);
            toolbar.setTitle(R.string.book);
        } else if (id == R.id.nav_slideshow) {
            switchFragment(currentFragment, fragmentPool.get(2));
            currentFragment = fragmentPool.get(2);
            toolbar.setTitle(R.string.professor);
        } else if (id == R.id.nav_manage) {
            switchFragment(currentFragment, fragmentPool.get(3));
            currentFragment = fragmentPool.get(3);
            toolbar.setTitle(R.string.table);
        } else if (id == R.id.nav_share) {
            Toast.makeText(this, "Beta Version", Toast.LENGTH_SHORT).show();
        }
        return true;
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


    @Override
    public void onTabSelected(int position) {
        FragmentManager fm = getFragmentManager();
        switch (position) {
            case 0:
                switchFragment(currentNavFragment, fragmentNavPool.get(0));
                currentNavFragment = fragmentNavPool.get(0);
                toolbar.setTitle("主页");
                break;
            case 1:
                switchFragment(currentNavFragment, fragmentPool.get(0));
                currentNavFragment = fragmentNavPool.get(1);
                currentFragment = fragmentPool.get(0);
                toolbar.setTitle("通讯录");
                break;
            case 2:
                switchFragment(currentNavFragment, fragmentNavPool.get(2));
                currentNavFragment = fragmentNavPool.get(2);
                toolbar.setTitle("讨论");
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
