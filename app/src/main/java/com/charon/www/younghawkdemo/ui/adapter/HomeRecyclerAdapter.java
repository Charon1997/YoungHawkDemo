package com.charon.www.younghawkdemo.ui.adapter;

import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.biz.MyItemClickListener;
import com.charon.www.younghawkdemo.biz.MyItemLongClickListener;
import com.charon.www.younghawkdemo.model.HomeItem;
import com.charon.www.younghawkdemo.model.Time;
import com.charon.www.younghawkdemo.ui.Fragments.HomeFragment;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Charon on 2017/4/25.
 */

public class HomeRecyclerAdapter extends RecyclerView.Adapter {
    private List<HomeItem> list;//相关数据
    private Context mContext;
    private int position;

    public int getPosition1() {
        return position;
    }

    private void setPosition(int position) {
        this.position = position;
    }


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
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
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



    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener,View.OnLongClickListener,MenuItem.OnMenuItemClickListener {
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
            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnLongClickListener(this);
        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem edit = menu.add(0, 1, 0, "编辑");
            MenuItem delete =menu.add(0, 2, 0, "删除");
            Log.d("123", "创建了");
            edit.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }



        @Override
        public boolean onLongClick(View v) {
            return true;
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int position = -1;
            position = getPosition1();
            if (position >= 0) {
                switch (item.getItemId()) {
                    case 1:
                        Log.d("123", "编辑" + position);
                        break;
                    case 2:
                        Log.d("123", "删除" + position);
                        deleteItem(((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position);
                        break;
                }
            }
            return true;
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
    public void deleteItem(int i) {
        int size = list.size();
        if (size > 0) {
            list.remove(i);
            notifyDataSetChanged();
        }
    }


}
