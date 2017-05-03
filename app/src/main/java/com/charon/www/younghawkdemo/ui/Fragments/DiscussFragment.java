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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.model.PlanItem;
import com.charon.www.younghawkdemo.model.Time;
import com.charon.www.younghawkdemo.ui.adapter.DiscussRecyclerAdapter;
import com.charon.www.younghawkdemo.ui.adapter.PlanRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charon on 2017/4/24.
 */

public class DiscussFragment extends Fragment {
    private DiscussRecyclerAdapter adapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout refresh;
    private static DiscussFragment instance;

    //临时数据
    private List<PlanItem> mDiscussList;


    public static DiscussFragment getInstance() {
        if (instance == null) {
            instance = new DiscussFragment();
        }

        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discuss, container, false);

        addDate(12);
        addView(view);
        return view;
    }

    private void addView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.discuss_recycler);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new DiscussRecyclerAdapter(mDiscussList,getActivity());
        mRecyclerView.setAdapter(adapter);

        refresh = (SwipeRefreshLayout) view.findViewById(R.id.discuss_refresh);
        refresh.setColorSchemeResources(R.color.colorPrimary);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        for (int i = 0; i <1; i++) {
                            Time time = new Time(2017,2,3,12,i);
                            PlanItem homeItem = new PlanItem("Charon",time,"这是一个讨论很长很长很长很长很长很长很长很长的刷新测试文字段"+i);
                            mDiscussList.add(0,homeItem);
                        }
                        adapter.addHeadItem(mDiscussList);
                        refresh.setRefreshing(false);
                        Toast.makeText(getActivity(), "更新了1条数据...", Toast.LENGTH_SHORT).show();
                    }
                }, 5000);
            }
        });
    }

    private void addDate(int j) {
        mDiscussList = new ArrayList<>();
        for (int i = 0 ; i < j ; i++) {
            Time time = new Time(2017,2,3,12,i);
            PlanItem planList = new PlanItem("Charon",time,"这是一个讨论讨论讨论讨论讨论讨论讨论讨论讨论讨论讨论很长讨论讨论讨论讨论讨论讨论长长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长的测试文字段"+i);
            mDiscussList.add(planList);
        }
    }
}
