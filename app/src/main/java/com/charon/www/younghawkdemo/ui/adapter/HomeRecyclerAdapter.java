package com.charon.www.younghawkdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.charon.www.younghawkdemo.model.Moment;
import com.charon.www.younghawkdemo.ui.Fragments.HomeFragment;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    private static boolean noMore = true;
    private static boolean onError = false;
    private static List<Integer> titleCount = new ArrayList<>();

    private ArrayList<Moment> list = new ArrayList<>();//相关数据
    private Context mContext;
    private int position;
    private HomeFragment homeFragment = new HomeFragment();


    public int getPosition() {
        return position;
    }

    private void setPosition(int position) {
        this.position = position;
    }


    public HomeRecyclerAdapter(ArrayList<Moment> list, Context context) {
        this.list = list;
        this.mContext = context;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HomeViewHolder) {
            Glide.with(mContext).load(list.get(position).getUserBean().getAvatarUrl()).into(((HomeViewHolder) holder).mCivHead);
            ((HomeViewHolder) holder).mTvName.setText(list.get(position).getUserBean().getUserName());
            ((HomeViewHolder) holder).mTvTime.setText(formatTime(list.get(position).getTime()));
            ((HomeViewHolder) holder).mTvContent.setText(list.get(position).getContent());
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
                    homeFragment.showInf(list.get(position));
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
            //((TitleHolder)holder).mTitle.setText(changeDay());
        } else if (holder instanceof EndHolder){

        }
    }

//    private String changeDay() {
////        String year = String.valueOf(day.getYear());
////        String month = String.valueOf(day.getMonth());
////        String days = String.valueOf(day.getDay());
////        return year + "-" + month + "-" + days;
//    }

    @Override
    public int getItemCount() {
        return list.size() == 0?0:list.size() +1;
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


//    private String changeTime(int position) {
//        Date timeList = list.get(position).getPubTime();
//        Date dayList = list.get(position).getPubDay();
//        String year = String.valueOf(dayList.getYear());
//        String month = String.valueOf(dayList.getMonth());
//        String day = String.valueOf(dayList.getDay());
//        String hour = String.valueOf(timeList.getHour());
//        String min = String.valueOf(timeList.getMin());
//        return year + "年" + month + "月" + day + "日    " + hour + ":" + min;
//        return hour + ":" + min;
//    }

    public void addHeadItem(List<Moment> list) {
        //this.list.add(0,list.get(0));
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

//    private void deleteItemById(int i) {
//        System.arraycopy(list, i + 1, list, i + 1 - 1, list.length - (i + 1));
//        list[list.length-1] = null;
//    }

    public void addData(ArrayList<Moment> homeBeanList) {
        for (int i = 0;i < homeBeanList.size();i++) {
            //list.add(homeBeanList.get(i));
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
        return list.get(position).getContent();
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
