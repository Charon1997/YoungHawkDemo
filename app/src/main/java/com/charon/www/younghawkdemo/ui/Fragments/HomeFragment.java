package com.charon.www.younghawkdemo.ui.Fragments;

import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.model.Moment;
import com.charon.www.younghawkdemo.presenter.HomePresenter;
import com.charon.www.younghawkdemo.ui.Activities.FabHomeActivity;
import com.charon.www.younghawkdemo.ui.adapter.HomeRecyclerAdapter;
import com.charon.www.younghawkdemo.view.IHomeView;

import java.util.ArrayList;
import java.util.List;

import static com.charon.www.younghawkdemo.model.Constant.HOME_CONTENT;

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
    private Context context;
    private HomePresenter homePresenter;

    private ImageView mIvLike,mIvComment;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
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
        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_recycler);
        refresh = (SwipeRefreshLayout) view.findViewById(R.id.home_refresh);
        refresh.setColorSchemeResources(R.color.colorPrimary);

        homePresenter = new HomePresenter(context,this);
        homePresenter.getHomeInf();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        int position = adapter.getPosition();
        switch (id) {
            case 0:
                homePresenter.editItem(position);
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
    public void addView(ArrayList<Moment> homeList) {
        if (moreCount == 0) {
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            adapter = new HomeRecyclerAdapter(homeList, getActivity());
            mRecyclerView.setAdapter(adapter);
            adapter.setOnGetImageClickListener(new HomeRecyclerAdapter.onGetImageClickListener() {
                @Override
                public void getLike(ImageView likeImage) {
                    mIvLike = likeImage;
                }

                @Override
                public void getComment(ImageView commentImage) {
                    mIvComment = commentImage;
                }
            });
        } else {
            adapter.addData(homeList);
        }

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homePresenter.getHeadInf();
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
//                    homePresenter.getMoreInf();
//                    loading = true;
//                }
//            }
//        });
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
        Intent intent = new Intent(HomeFragment.getInstance().getActivity(), FabHomeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(HOME_CONTENT,adapter.getContent(position));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void deleteItem(final int position) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getActivity());
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("删除动态");
        normalDialog.setMessage("是否删除此项动态");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do传入数据库，建立连接，退出
                        homePresenter.deleteItem(position);
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
    public void refreshList(Moment[] homeList) {
//        adapter.addHeadItem(homeList);
    }

    @Override
    public void refresh(boolean refresh) {
        this.refresh.setRefreshing(refresh);
    }

    @Override
    public void toDelete(int position) {
        adapter.deleteItem(position);
    }

    @Override
    public void changeLikeView(boolean isBlue) {
        if (isBlue) {
            //如果是点亮的
            mIvLike.setImageResource(R.drawable.item_home_like_off);
        } else {
            mIvLike.setImageResource(R.drawable.item_home_like_on);
        }
    }

    @Override
    public void changeCommentView(boolean isBlue) {
        if (isBlue) {
            //如果是点亮的
            mIvComment.setImageResource(R.drawable.item_home_comment_off);
        } else {
            mIvComment.setImageResource(R.drawable.item_home_comment_on);
        }
    }

    @Override
    public void showToastMsg(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showInf(Moment list) {
        //Toast.makeText(getActivity(), "点击的名字" + list.get(position).getUserName(), Toast.LENGTH_SHORT).show();
    }

    public void clickLong() {
        Toast.makeText(HomeFragment.getInstance().getActivity(), "long", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clickLike(int position) {
        Toast.makeText(HomeFragment.getInstance().getActivity(), "喜欢了一下", Toast.LENGTH_SHORT).show();
        homePresenter.clickLike(position);
    }

    @Override
    public void clickComment(int position) {
        Toast.makeText(HomeFragment.getInstance().getActivity(), "评论了一下", Toast.LENGTH_SHORT).show();
        homePresenter.clickComment(position);
    }


}
