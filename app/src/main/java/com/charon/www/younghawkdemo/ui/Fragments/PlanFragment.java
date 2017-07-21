package com.charon.www.younghawkdemo.ui.Fragments;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.model.HomeBean;
import com.charon.www.younghawkdemo.model.PlanBean;
import com.charon.www.younghawkdemo.model.Time;
import com.charon.www.younghawkdemo.presenter.PlanPresenter;
import com.charon.www.younghawkdemo.ui.adapter.HomeRecyclerAdapter;
import com.charon.www.younghawkdemo.ui.adapter.PlanRecyclerAdapter;
import com.charon.www.younghawkdemo.view.IHomeView;
import com.charon.www.younghawkdemo.view.IPlanView;

import java.util.ArrayList;
import java.util.List;

import static com.charon.www.younghawkdemo.model.Constant.VISIBLE_THRESHOLD;

/**
 * Created by Charon on 2017/4/24.
 */

public class PlanFragment extends Fragment implements IPlanView {
    public static int moreCount = 0;
    public static boolean loading = false;

    private PlanRecyclerAdapter adapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout refresh;
    private static PlanFragment instance;

    private PlanPresenter planPresenter = new PlanPresenter(this);


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
        mRecyclerView = (RecyclerView) view.findViewById(R.id.plan_recycler);
        refresh = (SwipeRefreshLayout) view.findViewById(R.id.plan_refresh);
        planPresenter.getPlanInf();
        return view;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        int position = adapter.getPosition();
        switch (id) {
            case 0:
                planPresenter.editItem(position);
                Toast.makeText(getActivity(), "编辑" + position, Toast.LENGTH_SHORT).show();
                break;
            case 1:
                planPresenter.deleteItem(position);
                Toast.makeText(getActivity(), "删除" + position, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }


    @Override
    public void showInf(List<PlanBean> list, int position) {
    }

    @Override
    public void addView(final List<PlanBean> planList) {
        if (moreCount == 0) {
            final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            adapter = new PlanRecyclerAdapter(planList, getActivity());
            mRecyclerView.setAdapter(adapter);
        } else adapter.addData(planList);


        refresh.setColorSchemeResources(R.color.colorPrimary);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                planPresenter.getHeadInf();
                Toast.makeText(getActivity(), "更新了1条数据...", Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                int totalItemCount = layoutManager.getItemCount();

                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                Log.d("123", "开始前loading" + loading + "movieMore" + moreCount + "last" + totalItemCount);
                if (!loading && totalItemCount < (lastVisibleItem + VISIBLE_THRESHOLD) && dy > 0 && adapter.ifMore()) {
                    //未在加载、且还有3个就要到底了
                    moreCount++;
                    Log.d("123", "开始后loading" + loading + "movieMore" + moreCount);
                    planPresenter.getMoreInf();
                    loading = true;
                }
            }
        });
    }

    @Override
    public void loading(boolean loading) {

    }


    @Override
    public void showError() {

    }

    @Override
    public void editItem(int position) {

    }

    @Override
    public void deleteItem(final int position) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getActivity());
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("删除计划");
        normalDialog.setMessage("是否删除此项计划");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do传入数据库，建立连接，退出
                        adapter.deleteItem(position);
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        // 显示
        normalDialog.show();
    }

    @Override
    public void refreshList(List<PlanBean> planList) {
        adapter.addHeadItem(planList);
    }

    @Override
    public void refresh(boolean refresh) {
        this.refresh.setRefreshing(refresh);
    }


}
