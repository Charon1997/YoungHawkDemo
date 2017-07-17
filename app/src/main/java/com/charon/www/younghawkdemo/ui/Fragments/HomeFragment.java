package com.charon.www.younghawkdemo.ui.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.biz.MyRecClickListener;
import com.charon.www.younghawkdemo.model.HomeBean;
import com.charon.www.younghawkdemo.model.Time;
import com.charon.www.younghawkdemo.presenter.HomePresenter;
import com.charon.www.younghawkdemo.ui.adapter.HomeRecyclerAdapter;
import com.charon.www.younghawkdemo.view.IHomeView;

import java.util.ArrayList;
import java.util.List;

import static com.charon.www.younghawkdemo.model.Constant.VISIBLE_THRESHOLD;

/**
 * Created by Charon on 2017/4/24.
 */

public class HomeFragment extends Fragment implements IHomeView {
    public static int moreCount = 0;
    public static boolean loading = false;
    private static HomeFragment instance;
    private HomeRecyclerAdapter adapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout refresh;
    public static final int NORMAL = 0;
    public static final int LOADING = 1;
    public static final int END = 2;
    private HomePresenter homePresenter = new HomePresenter(this);
    private Context mContext;

    //临时数据
    private List<HomeBean> mHomeList;

    public static HomeFragment getInstance() {
        if (instance == null) {
            instance = new HomeFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_recycler);
        refresh = (SwipeRefreshLayout) view.findViewById(R.id.home_refresh);
        homePresenter.getHomeInf();
        return view;
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        int position = adapter.getPosition();
        switch (id) {
            case 0:
                homePresenter.editItem(position);
                Toast.makeText(getActivity(), "编辑"+position, Toast.LENGTH_SHORT).show();
                break;
            case 1:
                homePresenter.deleteItem(position);
                Toast.makeText(getActivity(), "删除"+position, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void addView(List<HomeBean> homeList) {
        Log.d("123", homeList.size() + "homeList的大小");
        if (moreCount == 0) {
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            adapter = new HomeRecyclerAdapter(homeList,getActivity());
            mRecyclerView.setAdapter(adapter);
        } else adapter.addData(homeList);


        refresh.setColorSchemeResources(R.color.colorPrimary);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addHeadItem(homePresenter.addDate(1));
                        refresh.setRefreshing(false);
                        Toast.makeText(getActivity(), "更新了1条数据...", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                int totalItemCount = layoutManager.getItemCount();

                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                Log.d("123", "开始前loading" + loading + "movieMore" + moreCount + "last" + totalItemCount );
                if (!loading && totalItemCount < (lastVisibleItem + VISIBLE_THRESHOLD) && dy > 0&& adapter.ifMore() ) {
                    //未在加载、且还有3个就要到底了
                    moreCount++;
                    Log.d("123", "开始后loading" + loading + "movieMore" + moreCount);
                    homePresenter.getMoreInf();
                    loading = true;
                }
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void editItem(int position) {
        adapter.deleteItem(position);
    }

    @Override
    public void deleteItem(int position) {

    }


    public void showInf(List<HomeBean> list, int position) {
        //Toast.makeText(getActivity(), "点击的名字" + list.get(position).getUserName(), Toast.LENGTH_SHORT).show();
    }

    public void clickLong() {
        Toast.makeText(getActivity(), "long", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clickLike(int position) {

        homePresenter.clickLike(position);
    }

    @Override
    public void clickComment(int position) {

        homePresenter.clickComment(position);
    }

    @Override
    public void initView() {

    }



}
