package com.charon.www.younghawkdemo.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Charon on 2017/3/4.
 */
public class MailViewpagerAdapter extends PagerAdapter {
    private List<View> list_view;
    private String[] titles = {"项目组","所有人"};
    public MailViewpagerAdapter(List<View> list_view) {
        this.list_view = list_view;
    }
    @Override
    public int getCount() {
        return list_view.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list_view.get(position),0);
        return list_view.get(position );
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
        view = null;
    }
}
