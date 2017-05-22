package com.charon.www.younghawkdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.model.HomeItem;
import com.charon.www.younghawkdemo.model.PlanItem;
import com.charon.www.younghawkdemo.model.Time;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Charon on 2017/4/26.
 */

public class PlanRecyclerAdapter extends RecyclerView.Adapter {
    private List<PlanItem> list;//相关数据
    private Context mContext;
    private boolean mMore = false;//false代表最大3行

    private int position;
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public PlanRecyclerAdapter(List<PlanItem> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plan, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((MyViewHolder) holder).mTvName.setText(list.get(position).getUserName());
        ((MyViewHolder) holder).mTvTime.setText(changeTime(position));
        ((MyViewHolder) holder).mTvContent.setText(list.get(position).getPubContent());

        ((MyViewHolder) holder).mTvContent.setTag(position);

        ((MyViewHolder)holder).mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "编辑", Toast.LENGTH_SHORT).show();
            }
        });

        ((MyViewHolder)holder).mCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(position);
                return false;
            }
        });

        ((MyViewHolder) holder).mTvContent.post(new Runnable() {
            @Override
            public void run() {
                if (((MyViewHolder) holder).mTvContent.getLineCount() <= 4) {
                    ((MyViewHolder) holder).mTvMore.setVisibility(View.GONE);
                } else ((MyViewHolder) holder).mTvMore.setVisibility(View.VISIBLE);
            }
        });

        Log.d("123", "count" + ((MyViewHolder) holder).mTvContent.getLineCount());
        ((MyViewHolder) holder).mTvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int durationMillis = 200;
                final int startHight = ((MyViewHolder) holder).mTvContent.getLineHeight() * 4;
                final int endCount = ((MyViewHolder) holder).mTvContent.getLineCount();
                final int endHight = ((MyViewHolder) holder).mTvContent.getHeight();
                Log.d("123", "endCount" + endCount + "start" + startHight + "endHight" + endHight);
                if (!mMore) {
                    ((MyViewHolder) holder).mTvMore.setText("收起");
                    mMore = true;

                    Animation animation = new Animation() {
                        //interpolatedTime 为当前动画帧对应的相对时间，值总在0-1之间

                        protected void applyTransformation(float interpolatedTime, Transformation t) { //根据ImageView旋转动画的百分比来显示textview高度，达到动画效果
                            int tempHight;
                            tempHight = ((MyViewHolder) holder).mTvContent.getLineHeight() * endCount - startHight; //单行高度 * 行数 = 总高度 - 开始的高度 = 高度差
                            Log.d("123", "temp true" + tempHight );
                            ((MyViewHolder) holder).mTvContent.setHeight((int) (startHight + tempHight * interpolatedTime));//原始长度+高度差*（从0到1的渐变）即表现为动画效果
                        }
                    };
                    animation.setDuration(durationMillis);
                    ((MyViewHolder) holder).mTvContent.startAnimation(animation);
                } else {
                    ((MyViewHolder) holder).mTvMore.setText("更多");
                    mMore = false;

                    Animation animation = new Animation() {
                        //interpolatedTime 为当前动画帧对应的相对时间，值总在0-1之间
                        protected void applyTransformation(float interpolatedTime, Transformation t) { //根据ImageView旋转动画的百分比来显示textview高度，达到动画效果
                            int tempHight;
                            tempHight = ((MyViewHolder) holder).mTvContent.getLineHeight() * (endCount - 4 );
                            Log.d("123", "temp false" + tempHight );
                            ((MyViewHolder) holder).mTvContent.setHeight((int) (endHight - tempHight * interpolatedTime));//原始长度+高度差*（从0到1的渐变）即表现为动画效果
                        }
                    };
                    animation.setDuration(durationMillis);
                    ((MyViewHolder) holder).mTvContent.startAnimation(animation);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private TextView mTvName, mTvTime, mTvContent, mTvMore;
        private CardView mCardView;

        MyViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.item_plan_name);
            mTvTime = (TextView) itemView.findViewById(R.id.item_plan_time);
            mTvContent = (TextView) itemView.findViewById(R.id.item_plan_content);
            mTvMore = (TextView) itemView.findViewById(R.id.item_plan_more);
            mCardView = (CardView) itemView.findViewById(R.id.item_plan_card);
            mCardView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(0, 0, 0, "编辑");
            menu.add(0, 1, 0, "删除");
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
