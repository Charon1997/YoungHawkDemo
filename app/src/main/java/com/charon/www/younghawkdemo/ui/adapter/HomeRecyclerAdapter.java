package com.charon.www.younghawkdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.model.HomeItem;
import com.charon.www.younghawkdemo.model.Time;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/4/25.
 */

public class HomeRecyclerAdapter extends RecyclerView.Adapter {
    private List<HomeItem> list;//相关数据
    private Context mContext;

    public HomeRecyclerAdapter(List<HomeItem> list, Context context) {
        this.list = list;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
            return new MyViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_footer, parent, false);
            return new FooterHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).mCivHead.setImageResource(list.get(position).getImg());
        ((MyViewHolder) holder).mTvName.setText(list.get(position).getUserName());
        ((MyViewHolder) holder).mTvTime.setText(changeTime(position));
        ((MyViewHolder) holder).mTvContent.setText(list.get(position).getPubContent());
        ((MyViewHolder) holder).mIvLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "喜欢了一下", Toast.LENGTH_SHORT).show();
            }
        });
        ((MyViewHolder) holder).mIvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "评论了一下", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size())
            return 1;
        else
            return 0;
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

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvName, mTvTime, mTvContent;
        private ImageView mIvLike, mIvComment;
        private CircleImageView mCivHead;


        MyViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.item_home_name);
            mTvTime = (TextView) itemView.findViewById(R.id.item_home_time);
            mTvContent = (TextView) itemView.findViewById(R.id.item_home_content);
            mIvLike = (ImageView) itemView.findViewById(R.id.item_home_like);
            mIvComment = (ImageView) itemView.findViewById(R.id.item_home_img_comment);
            mCivHead = (CircleImageView) itemView.findViewById(R.id.item_home_img_head);
        }
    }

    private class FooterHolder extends RecyclerView.ViewHolder {
        ProgressBar mPbFooter;
        TextView mTvFooterEnd;

        FooterHolder(View itemView) {
            super(itemView);
            mPbFooter = (ProgressBar) itemView.findViewById(R.id.footer_progress_bar);
            mTvFooterEnd = (TextView) itemView.findViewById(R.id.footer_text_end);
        }

        public void setData(int status) {
            switch (status) {
                case 0:
                    setAllGone();
                    break;
                case 1:
                    setAllGone();
                    mPbFooter.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    setAllGone();
                    mTvFooterEnd.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }

        void setAllGone() {
            if (mPbFooter != null) {
                mPbFooter.setVisibility(View.GONE);
            }
            if (mTvFooterEnd != null) {
                mTvFooterEnd.setVisibility(View.GONE);
            }
        }
    }

    public void addHeadItem(List<HomeItem> list) {

        this.list = list;
        notifyDataSetChanged();
    }


}
