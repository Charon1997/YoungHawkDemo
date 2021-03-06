package com.charon.www.younghawkdemo.ui.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
import com.charon.www.younghawkdemo.model.Discuss;
import com.charon.www.younghawkdemo.model.DiscussBean;
import com.charon.www.younghawkdemo.presenter.DiscussPresenter;
import com.charon.www.younghawkdemo.ui.Activities.FabDiscussActivity;
import com.charon.www.younghawkdemo.ui.adapter.DiscussRecyclerAdapter;
import com.charon.www.younghawkdemo.view.IDiscussView;

import java.util.ArrayList;
import java.util.List;

import static com.charon.www.younghawkdemo.model.Constant.DISCUSS_CONTENT;
import static com.charon.www.younghawkdemo.model.Constant.DISCUSS_TITLE;
import static com.charon.www.younghawkdemo.model.Constant.VISIBLE_THRESHOLD;

/**
 * Created by Charon on 2017/4/24.
 */

public class DiscussFragment extends Fragment implements IDiscussView {
    public static int moreCount = 0;
    public static boolean loading = false;

    private DiscussRecyclerAdapter adapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout refresh;
    private static DiscussFragment instance;
    private Context context;
    private DiscussPresenter discussPresenter ;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

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
        mRecyclerView = (RecyclerView) view.findViewById(R.id.discuss_recycler);
        refresh = (SwipeRefreshLayout) view.findViewById(R.id.discuss_refresh);
        refresh.setColorSchemeResources(R.color.colorPrimary);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        discussPresenter = new DiscussPresenter(context,this);
        discussPresenter.getDiscussInf();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        int position = adapter.getPosition();
        switch (id) {
            case 0:
                discussPresenter.editItem(position);
                Toast.makeText(getActivity(), "编辑" + position, Toast.LENGTH_SHORT).show();
                break;
            case 1:
                deleteItem(position);
                Toast.makeText(getActivity(), "删除" + position, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }




    @Override
    public void loading(boolean loading) {
        if (loading){
            refresh.setRefreshing(true);
        } else {
            refresh.setRefreshing(false);
        }
    }

    @Override
    public void showError() {
        refresh.setRefreshing(false);
        Snackbar.make(mRecyclerView,R.string.error_network,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void editItem(int position) {
        Intent intent = new Intent(DiscussFragment.getInstance().getActivity(), FabDiscussActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(DISCUSS_CONTENT,adapter.getContent(position));
        bundle.putString(DISCUSS_TITLE,adapter.getTitle(position));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void deleteItem(final int position) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getActivity());
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("删除讨论");
        normalDialog.setMessage("是否删除此项讨论");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do传入数据库，建立连接，退出
                        discussPresenter.deleteItem(position);
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
    public void showInf(Discuss[] discussList, int position) {

    }

    @Override
    public void addView(ArrayList<Discuss> discussList) {
        if (moreCount == 0) {
            final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            adapter = new DiscussRecyclerAdapter(discussList, getActivity());
            mRecyclerView.setAdapter(adapter);
        } else {
            //adapter.addData(discussList);
        }
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                discussPresenter.getHeadInf();
                Toast.makeText(getActivity(), "暂无新消息", Toast.LENGTH_SHORT).show();
            }
        });

//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//
//                int totalItemCount = layoutManager.getItemCount();
//
//                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
//                Log.d("123", "开始前loading" + loading + "movieMore" + moreCount + "last" + totalItemCount);
//                if (!loading && totalItemCount < (lastVisibleItem + VISIBLE_THRESHOLD) && dy > 0 && adapter.ifMore()) {
//                    //未在加载、且还有3个就要到底了
//                    moreCount++;
//                    Log.d("123", "开始后loading" + loading + "movieMore" + moreCount);
//                    discussPresenter.getMoreInf();
//                    loading = true;
//                }
//            }
//        });
    }

    @Override
    public void refreshList(Discuss[] discussList) {
        adapter.addHeadItem(discussList);
    }

    @Override
    public void showToastMsg(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refresh(boolean refresh) {
        this.refresh.setRefreshing(refresh);
    }

    @Override
    public void toDelete(int position) {
        adapter.deleteItem(position);
    }
}
