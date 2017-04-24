package com.charon.www.younghawkdemo.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Rye on 2017/3/8.
 */

public class IntroFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;

    public IntroFragmentStatePagerAdapter(FragmentManager supportFragmentManager, List<Fragment> fragments) {
        super(supportFragmentManager);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
