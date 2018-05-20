package com.charon.www.younghawkdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.charon.www.younghawkdemo.model.Discuss;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.charon.www.younghawkdemo.model.Constant.TYPE_END;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_ERROR;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_FOOTER;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_ITEM_DISCUSS;

/**
 * Created by Charon on 2017/5/2.
 */

public class DiscussRecyclerAdapter extends BaseRecyclerAdapter{
    private static boolean noMore = true;
    private static boolean onError = false;


    private ArrayList<Discuss> list;//相关数据
    private Context mContext;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public DiscussRecyclerAdapter(ArrayList<Discuss> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof DiscussViewHolder) {
            ((DiscussViewHolder) holder).mTvName.setText(list.get(position).getTitle());
            ((DiscussViewHolder) holder).mTvTime.setText(formatTime(list.get(position).getTime()));
            ((DiscussViewHolder) holder).mTvContent.setText(list.get(position).getDcContent());
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

//    private String changeTime(int position) {
//        Date timeList = list.get(position).getPubTime();
//        String year = String.valueOf(timeList.getYear());
//        String month = String.valueOf(timeList.getMonth());
//        String day = String.valueOf(timeList.getDay());
//        String hour = String.valueOf(timeList.getHour());
//        String min = String.valueOf(timeList.getMin());
//        return year + "年" + month + "月" + day + "日    " + hour + ":" + min;
//    }



    public void addHeadItem(Discuss[] list) {
        //this.list.add(0,list.get(0));
        //具体在变。
        notifyDataSetChanged();
    }
    public void addData(Discuss[] list) {
        for (int i = 0;i < list.length;i++) {
            //this.list[i] = ;
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
            //deleteItemById(i);
            list.remove(i);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, list.size() - position);
        }
    }
//    private void deleteItemById(int i) {
//        System.arraycopy(list, i + 1, list, i + 1 - 1, list.length - (i + 1));
//        list[list.length-1] = null;
//    }
    public String getContent(int position) {
        return list.get(position).getDcContent();
    }

    public String getTitle(int position) {
        return list.get(position).getTitle();
    }

    private String formatTime(Timestamp timestamp){
        String tsStr = "";
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //方法一
            tsStr = sdf.format(timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tsStr;
    }
}
