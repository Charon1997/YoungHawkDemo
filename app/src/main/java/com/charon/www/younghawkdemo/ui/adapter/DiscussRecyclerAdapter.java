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
import com.charon.www.younghawkdemo.model.Date;
import com.charon.www.younghawkdemo.model.DiscussBean;

import java.util.List;

import static com.charon.www.younghawkdemo.model.Constant.TYPE_END;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_ERROR;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_FOOTER;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_ITEM;

/**
 * Created by Charon on 2017/5/2.
 */

public class DiscussRecyclerAdapter extends RecyclerView.Adapter {
    private static boolean noMore = false;
    private static boolean onError = false;


    private List<DiscussBean> list;//相关数据
    private Context mContext;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public DiscussRecyclerAdapter(List<DiscussBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discuss, parent, false);
                return new MyViewHolder(view);

            case TYPE_FOOTER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_footer, parent, false);
                return new FooterHolder(view);
            case TYPE_END:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyler_end, parent, false);
                return new EndHolder(view);
            case TYPE_ERROR:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyler_error, parent, false);
                return new ErrorHolder(view);
            default:return null;
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).mTvName.setText(list.get(position).getPubTitle());
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
        if (position+1 == getItemCount()){
            if (noMore)
                return TYPE_END;
            else if (onError)
                return TYPE_ERROR;
            else
                return TYPE_FOOTER;
        }
        else
            return TYPE_ITEM;
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

    private class FooterHolder extends RecyclerView.ViewHolder {
        ProgressBar mPbFooter;
        FooterHolder(View itemView) {
            super(itemView);
            mPbFooter = (ProgressBar) itemView.findViewById(R.id.footer_progress_bar);
        }
    }

    private class EndHolder extends RecyclerView.ViewHolder {
        public EndHolder(View itemView) {
            super(itemView);
        }
    }

    private class ErrorHolder extends RecyclerView.ViewHolder {
        public ErrorHolder(View itemView) {
            super(itemView);
        }
    }

    private String changeTime(int position) {
        Date timeList = list.get(position).getPubTime();
        String year = String.valueOf(timeList.getYear());
        String month = String.valueOf(timeList.getMonth());
        String day = String.valueOf(timeList.getDay());
        String hour = String.valueOf(timeList.getHour());
        String min = String.valueOf(timeList.getMin());
        return year + "年" + month + "月" + day + "日    " + hour + ":" + min;
    }



    public void addHeadItem(List<DiscussBean> list) {
        this.list.add(0,list.get(0));
        //具体在变。
        notifyDataSetChanged();
    }
    public void addData(List<DiscussBean> list) {
        for (int i = 0;i < list.size();i++) {
            this.list.add(list.get(i));
        }
        notifyDataSetChanged();
    }

    public boolean ifMore() {
        //如果没有更多就返回false，noMore = ture;
        return true;
    }
    public void deleteItem(int i) {
        int size = list.size();
        if (size > 0) {
            list.remove(i);
            notifyDataSetChanged();
        }
    }

    public String getContent(int position) {
        return list.get(position).getPubContent();
    }

    public String getTitle(int position) {
        return list.get(position).getPubTitle();
    }
}
