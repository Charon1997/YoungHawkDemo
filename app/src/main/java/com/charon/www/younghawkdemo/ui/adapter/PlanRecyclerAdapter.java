package com.charon.www.younghawkdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.model.Date;
import com.charon.www.younghawkdemo.model.PlanBean;
import com.charon.www.younghawkdemo.ui.Fragments.PlanFragment;

import java.util.List;


import static com.charon.www.younghawkdemo.model.Constant.TYPE_END;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_ERROR;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_FOOTER;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_ITEM;

/**
 * Created by Charon on 2017/4/26.
 */

public class PlanRecyclerAdapter extends RecyclerView.Adapter {
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plan, parent, false);
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
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).mTvName.setText(list.get(position).getUserName());
            ((MyViewHolder) holder).mTvTime.setText(changeTime(position));
            ((MyViewHolder) holder).mTvSummary.setText(list.get(position).getSummary());
            ((MyViewHolder) holder).mTvSummary.setTag(position);
            ((MyViewHolder) holder).mTvPlan.setText(list.get(position).getPlan());
            ((MyViewHolder) holder).mTvPlan.setTag(position);

            ((MyViewHolder) holder).mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "点击了" + position, Toast.LENGTH_SHORT).show();
                    planFragment.showInf(list, position);
                }
            });

            ((MyViewHolder) holder).mCardView.setOnLongClickListener(new View.OnLongClickListener() {
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
            return TYPE_ITEM;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private TextView mTvName, mTvTime, mTvSummary, mTvPlan;
        private CardView mCardView;

        MyViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.item_plan_card);
            mTvName = (TextView) itemView.findViewById(R.id.item_plan_name);
            mTvTime = (TextView) itemView.findViewById(R.id.item_plan_time);
            mTvSummary = (TextView) itemView.findViewById(R.id.item_plan_summary);
            mTvPlan = (TextView) itemView.findViewById(R.id.item_plan_plan);
            //mTvMore = (TextView) itemView.findViewById(R.id.item_plan_more);

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
            notifyDataSetChanged();
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
