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
import static com.charon.www.younghawkdemo.model.Constant.TYPE_ITEM_DISCUSS;

/**
 * Created by Charon on 2017/5/2.
 */

public class DiscussRecyclerAdapter extends BaseRecyclerAdapter{
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof DiscussViewHolder) {
            ((DiscussViewHolder) holder).mTvName.setText(list.get(position).getPubTitle());
            ((DiscussViewHolder) holder).mTvTime.setText(changeTime(position));
            ((DiscussViewHolder) holder).mTvContent.setText(list.get(position).getPubContent());
            Log.d("123", "绑定了holder");
            ((DiscussViewHolder) holder).itemView.setTag(position);

            ((DiscussViewHolder) holder).mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "click" + position, Toast.LENGTH_SHORT).show();
                }
            });

            ((DiscussViewHolder) holder).mCardView.setOnLongClickListener(new View.OnLongClickListener() {
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
        if (holder instanceof DiscussViewHolder) {
            ((DiscussViewHolder) holder).mCardView.setOnLongClickListener(null);
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
            return TYPE_ITEM_DISCUSS;
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
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, list.size() - position);
        }
    }

    public String getContent(int position) {
        return list.get(position).getPubContent();
    }

    public String getTitle(int position) {
        return list.get(position).getPubTitle();
    }
}
