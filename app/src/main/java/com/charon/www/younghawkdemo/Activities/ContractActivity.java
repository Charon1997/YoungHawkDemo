package com.charon.www.younghawkdemo.Activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.charon.www.younghawkdemo.Fragments.ContractFragment;
import com.charon.www.younghawkdemo.Fragments.TableFragment;
import com.charon.www.younghawkdemo.IContract;
import com.charon.www.younghawkdemo.Fragments.ManageFragment;
import com.charon.www.younghawkdemo.Fragments.ProfessorFragment;
import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.Costum.StatusUtil;

import java.util.Arrays;
import java.util.List;

public class ContractActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,IContract {
    private int index;
    private Fragment currentFragment;
    private Toolbar toolbar;
    private final List<Fragment> fragmentPool = Arrays.asList(ContractFragment.getInstance(),ManageFragment.getInstance(),ProfessorFragment.getInstance(), TableFragment.getInstance());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusUtil.initStatus(getWindow());
        setContentView(R.layout.activity_contract);
        getFragmentManager().beginTransaction().add(R.id.content_contract,ContractFragment.getInstance()).commit();
        initView();
        initData();
    }

    private void initData() {
        index = 0;
        currentFragment = fragmentPool.get(index);
        getFragmentManager().beginTransaction().show(currentFragment).commit();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contract, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            switchFragment(currentFragment,fragmentPool.get(0));
            currentFragment = fragmentPool.get(0);
            toolbar.setTitle(R.string.mailList);
        } else if (id == R.id.nav_gallery) {
            switchFragment(currentFragment,fragmentPool.get(1));
            currentFragment = fragmentPool.get(1);
            toolbar.setTitle(R.string.book);
        } else if (id == R.id.nav_slideshow) {
            switchFragment(currentFragment,fragmentPool.get(2));
            currentFragment = fragmentPool.get(2);
            toolbar.setTitle(R.string.professor);
        } else if (id == R.id.nav_manage) {
            switchFragment(currentFragment,fragmentPool.get(3));
            currentFragment = fragmentPool.get(3);
            toolbar.setTitle(R.string.table);
        } else if (id == R.id.nav_share) {
            Toast.makeText(this,"Beta Version",Toast.LENGTH_SHORT).show();
        } /*else if (id == R.id.nav_send) {

        }*/
        return true;
    }



    @Override
    public void switchFragment(Fragment from, Fragment to) {
        if(!to.isAdded()){
            getFragmentManager().beginTransaction().add(R.id.content_contract,to).commit();
        }
        getFragmentManager().beginTransaction().hide(from).show(to).commit();
    }
}
