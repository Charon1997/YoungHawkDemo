package com.charon.www.younghawkdemo.ui.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.biz.MyRecClickListener;
import com.charon.www.younghawkdemo.model.HomeItem;
import com.charon.www.younghawkdemo.model.Time;
import com.charon.www.younghawkdemo.ui.adapter.HomeRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charon on 2017/4/24.
 */

public class HomeFragment extends Fragment {
    private static HomeFragment instance;
    private HomeRecyclerAdapter adapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout refresh;
    public static final int NORMAL = 0;
    public static final int LOADING = 1;
    public static final int END = 2;

    //临时数据
    private List<HomeItem> mHomeList;

    public static HomeFragment getInstance() {
        if (instance == null) {
            instance = new HomeFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        addDate(20);
        addView(view);
        return view;
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        int position = adapter.getPosition();
        switch (id) {
            case 0:
                Toast.makeText(getActivity(), "编辑"+position, Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(getActivity(), "删除"+position, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void addView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_recycler);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new HomeRecyclerAdapter(mHomeList,getActivity());
        mRecyclerView.setAdapter(adapter);

        refresh = (SwipeRefreshLayout) view.findViewById(R.id.home_refresh);
        refresh.setColorSchemeResources(R.color.colorPrimary);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i <1; i++) {
                            Time time = new Time(2017,2,3,12,i);
                            HomeItem homeItem = new HomeItem(R.drawable.charonhead,"Charon",time,"这是一个很长很长很长很长很长很长很长很长的刷新测试文字段"+i,0,0);
                            mHomeList.add(0,homeItem);
                        }
                        adapter.addHeadItem(mHomeList);
                        refresh.setRefreshing(false);
                        Toast.makeText(getActivity(), "更新了1条数据...", Toast.LENGTH_SHORT).show();
                    }
                }, 5000);
            }
        });
    }

    public void showInf(List<HomeItem> list, int position) {
        //Toast.makeText(getActivity(), "点击的名字" + list.get(position).getUserName(), Toast.LENGTH_SHORT).show();
    }

    private void addDate(int j) {
        mHomeList = new ArrayList<>();
        for (int i = 0 ; i < j ; i++) {
            Time time = new Time(2017,2,3,12,i);
            HomeItem homeItem = new HomeItem(R.drawable.charonhead,"Charon",time,"这是一个很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长的测试文字段"+i,0,0);
            mHomeList.add(homeItem);
        }
    }



    public void clickLong() {
        Toast.makeText(getActivity(), "long", Toast.LENGTH_SHORT).show();
    }
}
