package com.charon.www.younghawkdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.model.HomeBean;
import com.charon.www.younghawkdemo.model.Time;
import com.charon.www.younghawkdemo.ui.Fragments.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.charon.www.younghawkdemo.model.Constant.TYPE_END;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_ERROR;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_FOOTER;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_ITEM;

/**
 * Created by Charon on 2017/4/25.
 */

public class HomeRecyclerAdapter extends RecyclerView.Adapter  {
    private static boolean noMore = false;
    private static boolean onError = false;

    private List<HomeBean> list;//相关数据
    private Context mContext;
    private int position;
    private HomeFragment homeFragment = new HomeFragment();


    public int getPosition() {
        return position;
    }

    private void setPosition(int position) {
        this.position = position;
    }


    public HomeRecyclerAdapter(List<HomeBean> list, Context context) {
        this.list = list;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
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
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).mCivHead.setImageResource(list.get(position).getImg());
            ((MyViewHolder) holder).mTvName.setText(list.get(position).getUserName());
            ((MyViewHolder) holder).mTvTime.setText(changeTime(position));
            ((MyViewHolder) holder).mTvContent.setText(list.get(position).getPubContent());
            ((MyViewHolder) holder).mIvLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "喜欢了一下", Toast.LENGTH_SHORT).show();
                    homeFragment.clickLike(position);
                }
            });
            ((MyViewHolder) holder).mIvComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "评论了一下", Toast.LENGTH_SHORT).show();
                    homeFragment.clickComment(position);
                }
            });

            ((MyViewHolder)holder).mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    homeFragment.showInf(list,position);
                }
            });
            ((MyViewHolder)holder).mCardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    setPosition(position);
                    return false;
                }
            });
        }

    }
    @Override
    public int getItemCount() {
        return list.size() == 0?0:list.size()+1;
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

    private String changeTime(int position) {
        Time timeList = list.get(position).getPubTime();
        String year = String.valueOf(timeList.getYear());
        String month = String.valueOf(timeList.getMonth());
        String day = String.valueOf(timeList.getDay());
        String hour = String.valueOf(timeList.getHour());
        String min = String.valueOf(timeList.getMin());
        return year + "年" + month + "月" + day + "日    " + hour + ":" + min;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        private TextView mTvName, mTvTime, mTvContent;
        private ImageView mIvLike, mIvComment;
        private CircleImageView mCivHead;
        private CardView mCardView;

        MyViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.item_home_card);
            mTvName = (TextView) itemView.findViewById(R.id.item_home_name);
            mTvTime = (TextView) itemView.findViewById(R.id.item_home_time);
            mTvContent = (TextView) itemView.findViewById(R.id.item_home_content);
            mIvLike = (ImageView) itemView.findViewById(R.id.item_home_like);
            mIvComment = (ImageView) itemView.findViewById(R.id.item_home_img_comment);
            mCivHead = (CircleImageView) itemView.findViewById(R.id.item_home_img_head);

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

    public void addHeadItem(List<HomeBean> list) {
        this.list.add(0,list.get(0));
        //具体在变。
        notifyDataSetChanged();
    }

    public void deleteItem(int i) {
        int size = list.size();
        if (size > 0) {
            list.remove(i);
            notifyDataSetChanged();
        }
    }

    public void addData(List<HomeBean> homeBeanList) {
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
}
