package com.charon.www.younghawkdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import com.charon.www.younghawkdemo.model.Date;
import com.charon.www.younghawkdemo.model.PlanBean;
import com.charon.www.younghawkdemo.ui.Fragments.PlanFragment;

import java.util.List;


import static com.charon.www.younghawkdemo.model.Constant.TYPE_END;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_ERROR;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_FOOTER;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_ITEM_PLAN;

/**
 * Created by Charon on 2017/4/26.
 */

public class PlanRecyclerAdapter extends BaseRecyclerAdapter {
    private static boolean noMore = false;
    private static boolean onError = false;

    private PlanFragment planFragment = new PlanFragment();
    private List<PlanBean> list;//相关数据
    private Context mContext;
    private boolean mMore = false;//false代表最大3行

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public PlanRecyclerAdapter(List<PlanBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof PlanViewHolder) {
            ((PlanViewHolder) holder).mTvName.setText(list.get(position).getUserName());
            ((PlanViewHolder) holder).mTvTime.setText(changeTime(position));
            ((PlanViewHolder) holder).mTvSummary.setText(list.get(position).getSummary());
            ((PlanViewHolder) holder).mTvSummary.setTag(position);
            ((PlanViewHolder) holder).mTvPlan.setText(list.get(position).getPlan());
            ((PlanViewHolder) holder).mTvPlan.setTag(position);

            ((PlanViewHolder) holder).mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "点击了" + position, Toast.LENGTH_SHORT).show();
                    planFragment.showInf(list, position);
                }
            });

            ((PlanViewHolder) holder).mCardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    setPosition(position);
                    return false;
                }
            });
//            ((MyViewHolder) holder).mTvContent.post(new Runnable() {
//                @Override
//                public void run() {
//                    if (((MyViewHolder) holder).mTvContent.getLineCount() <= 4) {
//                        ((MyViewHolder) holder).mTvMore.setVisibility(View.GONE);
//                    } else ((MyViewHolder) holder).mTvMore.setVisibility(View.VISIBLE);
//                }
//            });
//
//            Log.d("123", "count" + ((MyViewHolder) holder).mTvContent.getLineCount());
//            ((MyViewHolder) holder).mTvMore.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    final int durationMillis = 200;
//                    final int startHight = ((MyViewHolder) holder).mTvContent.getLineHeight() * 4;
//                    final int endCount = ((MyViewHolder) holder).mTvContent.getLineCount();
//                    final int endHight = ((MyViewHolder) holder).mTvContent.getHeight();
//                    Log.d("123", "endCount" + endCount + "start" + startHight + "endHight" + endHight);
//                    if (!mMore) {
//                        ((MyViewHolder) holder).mTvMore.setText("收起");
//                        mMore = true;
//
//                        Animation animation = new Animation() {
//                            //interpolatedTime 为当前动画帧对应的相对时间，值总在0-1之间
//
//                            protected void applyTransformation(float interpolatedTime, Transformation t) { //根据ImageView旋转动画的百分比来显示textview高度，达到动画效果
//                                int tempHight;
//                                tempHight = ((MyViewHolder) holder).mTvContent.getLineHeight() * endCount - startHight; //单行高度 * 行数 = 总高度 - 开始的高度 = 高度差
//                                Log.d("123", "temp true" + tempHight );
//                                ((MyViewHolder) holder).mTvContent.setHeight((int) (startHight + tempHight * interpolatedTime));//原始长度+高度差*（从0到1的渐变）即表现为动画效果
//                            }
//                        };
//                        animation.setDuration(durationMillis);
//                        ((MyViewHolder) holder).mTvContent.startAnimation(animation);
//                    } else {
//                        ((MyViewHolder) holder).mTvMore.setText("更多");
//                        mMore = false;
//
//                        Animation animation = new Animation() {
//                            //interpolatedTime 为当前动画帧对应的相对时间，值总在0-1之间
//                            protected void applyTransformation(float interpolatedTime, Transformation t) { //根据ImageView旋转动画的百分比来显示textview高度，达到动画效果
//                                int tempHight;
//                                tempHight = ((MyViewHolder) holder).mTvContent.getLineHeight() * (endCount - 4 );
//                                Log.d("123", "temp false" + tempHight );
//                                ((MyViewHolder) holder).mTvContent.setHeight((int) (endHight - tempHight * interpolatedTime));//原始长度+高度差*（从0到1的渐变）即表现为动画效果
//                            }
//                        };
//                        animation.setDuration(durationMillis);
//                        ((MyViewHolder) holder).mTvContent.startAnimation(animation);
//                    }
//                }
//            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size() == 0 ? 0 : list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            if (noMore)
                return TYPE_END;
            else if (onError)
                return TYPE_ERROR;
            else
                return TYPE_FOOTER;
        } else
            return TYPE_ITEM_PLAN;
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

    public void addHeadItem(List<PlanBean> list) {
        this.list.add(0, list.get(0));
        //
        notifyDataSetChanged();
    }

    public void addData(List<PlanBean> homeBeanList) {
        for (int i = 0; i < homeBeanList.size(); i++) {
            list.add(homeBeanList.get(i));
        }
        notifyDataSetChanged();
    }

    public void deleteItem(int i) {
        int size = list.size();
        if (size > 0) {
            list.remove(i);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, list.size() - position);
        }
    }

    public boolean ifMore() {
        //如果没有更多就返回false，noMore = ture;
        return true;
    }

    public String getPlan(int position) {
        return list.get(position).getPlan();
    }

    public String getSummary(int position) {
        return list.get(position).getSummary();
    }
}
