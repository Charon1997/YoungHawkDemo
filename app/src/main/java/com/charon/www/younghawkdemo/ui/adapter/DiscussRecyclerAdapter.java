package com.charon.www.younghawkdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.model.PlanBean;
import com.charon.www.younghawkdemo.model.Time;

import java.util.List;

/**
 * Created by Charon on 2017/5/2.
 */

public class DiscussRecyclerAdapter extends RecyclerView.Adapter {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private List<PlanBean> list;//相关数据
    private Context mContext;
    private int position;
    private int isEnd;

    public int getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(int isEnd) {
        this.isEnd = isEnd;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public DiscussRecyclerAdapter(List<PlanBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discuss, parent, false);
            return new MyViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_footer, parent, false);
            return new FooterViewHolder(view);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).mTvName.setText(list.get(position).getUserName());
            ((MyViewHolder) holder).mTvTime.setText(changeTime(position));
            ((MyViewHolder) holder).mTvContent.setText(list.get(position).getPubContent());
            Log.d("123", "绑定了holder");
            ((MyViewHolder) holder).itemView.setTag(position);

            ((MyViewHolder) holder).mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "click" + position, Toast.LENGTH_SHORT).show();
                }
            });

            ((MyViewHolder) holder).mCardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    setPosition(position);
                    return false;
                }
            });
        }
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).mCardView.setOnLongClickListener(null);
            super.onViewRecycled(holder);
        }
    }

    @Override
    public int getItemCount() {
        return list.size() == 0 ? 0 : list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else return TYPE_ITEM;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private TextView mTvName, mTvTime, mTvContent;
        private CardView mCardView;

        MyViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.item_discuss_card);
            mTvName = (TextView) itemView.findViewById(R.id.item_discuss_name);
            mTvTime = (TextView) itemView.findViewById(R.id.item_discuss_time);
            mTvContent = (TextView) itemView.findViewById(R.id.item_discuss_content);
            mCardView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(0, 0, 0, "编辑");
            menu.add(0, 1, 0, "删除");
        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar mPb;

        FooterViewHolder(View itemView) {
            super(itemView);
            mPb = (ProgressBar) itemView.findViewById(R.id.footer_progress_bar);
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



    public void addHeadItem(List<PlanBean> list) {
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }

    public void addFooterItem(List<PlanBean> list) {
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }
}
