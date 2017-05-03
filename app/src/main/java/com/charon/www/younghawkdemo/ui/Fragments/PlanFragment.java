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
import com.charon.www.younghawkdemo.model.HomeItem;
import com.charon.www.younghawkdemo.model.PlanItem;
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
    private List<PlanItem> mPlanList;

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
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
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
                            PlanItem homeItem = new PlanItem("Charon",time,"这是一个计划很长很长很长很长很长很长很长很长的刷新测试文字段"+i);
                            mPlanList.add(0,homeItem);
                        }
                        adapter.addHeadItem(mPlanList);
                        refresh.setRefreshing(false);
                        Toast.makeText(getActivity(), "更新了1条数据...", Toast.LENGTH_SHORT).show();
                    }
                }, 5000);
            }
        });
    }

    private void addDate(int j) {
        mPlanList = new ArrayList<>();
        for (int i = 0 ; i < j ; i++) {
            Time time = new Time(2017,2,3,12,i);
            PlanItem planList = new PlanItem("Charon",time,"这是一个计划计划计划计划很长很长很长很长很长很长很长很长很长很长很长长很长很长很长很长很长很长很长很长很长长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长的测试文字段"+i);
            mPlanList.add(planList);
        }
    }
}
