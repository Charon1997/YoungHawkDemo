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
import android.widget.Toast;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.model.PlanBean;
import com.charon.www.younghawkdemo.model.Time;
import com.charon.www.younghawkdemo.ui.adapter.HomeRecyclerAdapter;
import com.charon.www.younghawkdemo.ui.adapter.PlanRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charon on 2017/4/24.
 */

public class PlanFragment extends Fragment {
    private PlanRecyclerAdapter adapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout refresh;
    private static PlanFragment instance;

    //临时数据
    private List<PlanBean> mPlanList;

    public static PlanFragment getInstance() {
        if (instance == null) {
            instance = new PlanFragment();
        }
        return instance;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan, container, false);

        addDate(15);
        addView(view);
        return view;
    }

    private void addView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.plan_recycler);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new PlanRecyclerAdapter(mPlanList,getActivity());
        mRecyclerView.setAdapter(adapter);

        refresh = (SwipeRefreshLayout) view.findViewById(R.id.plan_refresh);
        refresh.setColorSchemeResources(R.color.colorPrimary);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        for (int i = 0; i <1; i++) {
                            Time time = new Time(2017,2,3,12,i);
                            PlanBean homeItem = new PlanBean("Charon",time,"这是一个计划很长很长很长很长很长很长很长很长的刷新测试文字段"+i);
                            mPlanList.add(0,homeItem);
                        }
                        adapter.addHeadItem(mPlanList);
                        refresh.setRefreshing(false);
                        Toast.makeText(getActivity(), "更新了1条数据...", Toast.LENGTH_SHORT).show();
                    }
                }, 5000);
            }
        });


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

            }
        });
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


    private void addDate(int j) {
        mPlanList = new ArrayList<>();
        for (int i = 0 ; i < j ; i++) {
            Time time = new Time(2017,2,3,12,i);
            PlanBean planList = new PlanBean("Charon",time,"这是一个计划计划计划计划很划很长很长很长很长很长很长很长很长很长很长很长长很划很长很长很长很长很长很长很长很长很长很长很长长很划很长很长很长很长很长很长很长很长很长很长很长长很划很长很长很长很长很长很长很长很长很长很长很长长很划很长很长很长很长很长很长很长很长很长很长很长长很划很长很长很长很长很长很长很长很长很长很长很长长很划很长很长很长很长很长很长很长很长很长很长很长长很划很长很长很长很长很长很长很长很长很长很长很长长很长很长很长很长很长很长很长很长很长很长很长长很长很长很长很长很长很长很长很长很长长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长的测试文字段"+i);
            mPlanList.add(planList);
        }
        Time time = new Time(2017,2,3,12,23);
        PlanBean planList = new PlanBean("Charon",time,"这是一个计划计划计划计划很划很很长很长长的测试文字段"+23);
        mPlanList.add(planList);
    }
}
