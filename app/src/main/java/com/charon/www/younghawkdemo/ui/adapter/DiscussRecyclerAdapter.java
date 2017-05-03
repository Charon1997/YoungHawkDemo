package com.charon.www.younghawkdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.model.PlanItem;
import com.charon.www.younghawkdemo.model.Time;

import java.util.List;

/**
 * Created by Charon on 2017/5/2.
 */

public class DiscussRecyclerAdapter extends RecyclerView.Adapter {
    private List<PlanItem> list;//相关数据
    private Context mContext;


    public DiscussRecyclerAdapter(List<PlanItem> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discuss, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((DiscussRecyclerAdapter.MyViewHolder) holder).mTvName.setText(list.get(position).getUserName());
        ((DiscussRecyclerAdapter.MyViewHolder) holder).mTvTime.setText(changeTime(position));
        ((DiscussRecyclerAdapter.MyViewHolder) holder).mTvContent.setText(list.get(position).getPubContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvName, mTvTime, mTvContent;
        MyViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.item_discuss_name);
            mTvTime = (TextView) itemView.findViewById(R.id.item_discuss_time);
            mTvContent = (TextView) itemView.findViewById(R.id.item_discuss_content);
        }
    }

    private String changeTime(int position) {
        Time timeList = list.get(position).getPubTime();
        String year = String.valueOf(timeList.getYear());
        String month = String.valueOf(timeList.getMonth());
        String day = String.valueOf(timeList.getDay());
        String hour = String.valueOf(timeList.getHour());
        String min = String.valueOf(timeList.getMin());
        return year + "年" + month + "月" + day + "日    " + hour + ":" + min;
    }

    public void addHeadItem(List<PlanItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
