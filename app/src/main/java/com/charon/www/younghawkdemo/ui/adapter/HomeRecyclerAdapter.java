package com.charon.www.younghawkdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.charon.www.younghawkdemo.model.Date;
import com.charon.www.younghawkdemo.model.Moment;
import com.charon.www.younghawkdemo.ui.Fragments.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import static com.charon.www.younghawkdemo.model.Constant.TYPE_END;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_ERROR;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_FOOTER;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_ITEM_HOME;

/**
 * Created by Charon on 2017/4/25.
 */

public class HomeRecyclerAdapter extends BaseRecyclerAdapter  {
    private static final String TAG = HomeRecyclerAdapter.class.getSimpleName();
    private static boolean noMore = false;
    private static boolean onError = false;
    private static Date day ;
    private static List<Integer> titleCount = new ArrayList<>();

    private List<Moment> list;//相关数据
    private Context mContext;
    private int position;
    private HomeFragment homeFragment = new HomeFragment();


    public int getPosition() {
        return position;
    }

    private void setPosition(int position) {
        this.position = position;
    }


    public HomeRecyclerAdapter(List<Moment> list, Context context) {
        this.list = list;
        this.mContext = context;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HomeViewHolder) {
            ((HomeViewHolder) holder).mCivHead.setImageResource(list.get(position).getImg());
            ((HomeViewHolder) holder).mTvName.setText(list.get(position).getUserName());
            ((HomeViewHolder) holder).mTvTime.setText(changeTime(position));
            ((HomeViewHolder) holder).mTvContent.setText(list.get(position).getPubContent());
            ((HomeViewHolder) holder).mIvLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //((MyViewHolder) holder).mIvLike.setImageResource(R.drawable.item_home_like_on);
                    homeFragment.clickLike(position);
                }
            });
            ((HomeViewHolder) holder).mIvComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //((MyViewHolder) holder).mIvComment.setImageResource(R.drawable.item_home_comment_on);
                    homeFragment.clickComment(position);
                }
            });

            ((HomeViewHolder)holder).mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    homeFragment.showInf(list,position);
                }
            });
            ((HomeViewHolder)holder).mCardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    setPosition(position);
                    return false;
                }
            });
        } else if (holder instanceof TitleHolder) {
            ((TitleHolder)holder).mTitle.setText(changeDay());
        }
    }

    private String changeDay() {
        String year = String.valueOf(day.getYear());
        String month = String.valueOf(day.getMonth());
        String days = String.valueOf(day.getDay());
        return year + "-" + month + "-" + days;
    }

    @Override
    public int getItemCount() {
        return list.size() == 0?0:list.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "getItemViewType: "+position);
//        if (day == null) {
//            day = list.get(0).getPubDay();
//            titleCount.add(position);
//            return TYPE_TITLE;
//        } else {
//            if (titleCount.contains(position)) return TYPE_TITLE;
//            if (!day .equals(list.get(position -1 +titleCount.size()).getPubDay()) ) {
//                day = list.get(position -1 +titleCount.size()).getPubDay();
//                titleCount.add(position);
//                return TYPE_TITLE;
//            }
//        }
        if (position+1 == getItemCount()){
            if (noMore)
                return TYPE_END;
            else if (onError)
                return TYPE_ERROR;
            else
                return TYPE_FOOTER;
        }
        else
            return TYPE_ITEM_HOME;
    }

    private String changeTime(int position) {
        Date timeList = list.get(position).getPubTime();
        Date dayList = list.get(position).getPubDay();
        String year = String.valueOf(dayList.getYear());
        String month = String.valueOf(dayList.getMonth());
        String day = String.valueOf(dayList.getDay());
        String hour = String.valueOf(timeList.getHour());
        String min = String.valueOf(timeList.getMin());
        return year + "年" + month + "月" + day + "日    " + hour + ":" + min;
//        return hour + ":" + min;
    }

    public void addHeadItem(List<Moment> list) {
        this.list.add(0,list.get(0));
        //具体在变。
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

    public void addData(List<Moment> homeBeanList) {
        for (int i = 0;i < homeBeanList.size();i++) {
            list.add(homeBeanList.get(i));
        }
        notifyDataSetChanged();
    }

    public void onError() {

    }

    public boolean ifMore() {
        //如果没有更多就返回false，noMore = ture;
        return true;
    }

    public String getContent(int position) {
        return list.get(position).getPubContent();
    }

    public interface onGetImageClickListener {
        void getLike(ImageView likeImage);

        void getComment(ImageView commentImage);
    }

    private onGetImageClickListener listener;

    public void setOnGetImageClickListener(onGetImageClickListener listener) {
        this.listener = listener;
    }
}
