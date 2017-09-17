package com.charon.www.younghawkdemo.ui.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.charon.www.younghawkdemo.R;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.charon.www.younghawkdemo.model.Constant.TYPE_END;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_ERROR;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_FOOTER;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_ITEM_DISCUSS;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_ITEM_HOME;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_ITEM_PLAN;
import static com.charon.www.younghawkdemo.model.Constant.TYPE_TITLE;

/**
 * 项目名称：YoungHawkDemo
 * 类描述：
 * 创建人：Charon
 * 创建时间：2017/9/17 16:31
 * 修改人：Charon
 * 修改时间：2017/9/17 16:31
 * 修改备注：
 */

public class BaseRecyclerAdapter extends RecyclerView.Adapter{
    private static final String TAG = BaseRecyclerAdapter.class.getSimpleName();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_ITEM_HOME:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
                return new HomeViewHolder(view);
            case TYPE_ITEM_PLAN:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plan, parent, false);
                return new PlanViewHolder(view);
            case TYPE_ITEM_DISCUSS:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discuss, parent, false);
                return new DiscussViewHolder(view);
            case TYPE_FOOTER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_footer, parent, false);
                return new FooterHolder(view);
            case TYPE_END:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_end, parent, false);
                return new EndHolder(view);
            case TYPE_ERROR:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_error, parent, false);
                return new ErrorHolder(view);
            case TYPE_TITLE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_title, parent, false);
                return new TitleHolder(view);
            default:return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private class FooterHolder extends RecyclerView.ViewHolder {
        ProgressBar mPbFooter;
        FooterHolder(View itemView) {
            super(itemView);
            mPbFooter = (ProgressBar) itemView.findViewById(R.id.footer_progress_bar);
        }
    }

    class EndHolder extends RecyclerView.ViewHolder {
        public EndHolder(View itemView) {
            super(itemView);
        }
    }

    class ErrorHolder extends RecyclerView.ViewHolder {
        public ErrorHolder(View itemView) {
            super(itemView);
        }
    }

    class TitleHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        public TitleHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.item_recycler_title);
        }
    }

    class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
         TextView mTvName, mTvTime, mTvContent;
         ImageView mIvLike, mIvComment;
         CircleImageView mCivHead;
         CardView mCardView;

        HomeViewHolder(View itemView) {
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

     class PlanViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
         TextView mTvName, mTvTime, mTvSummary, mTvPlan;
         CardView mCardView;

        PlanViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.item_plan_card);
            mTvName = (TextView) itemView.findViewById(R.id.item_plan_name);
            mTvTime = (TextView) itemView.findViewById(R.id.item_plan_time);
            mTvSummary = (TextView) itemView.findViewById(R.id.item_plan_summary);
            mTvPlan = (TextView) itemView.findViewById(R.id.item_plan_plan);

            mCardView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(0, 0, 0, "编辑");
            menu.add(0, 1, 0, "删除");
        }
    }

     class DiscussViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
         TextView mTvName, mTvTime, mTvContent;
         CardView mCardView;

         DiscussViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.item_discuss_card);
            mTvName = (TextView) itemView.findViewById(R.id.item_discuss_name);
            mTvTime = (TextView) itemView.findViewById(R.id.item_discuss_time);
            mTvContent = (TextView) itemView.findViewById(R.id.item_discuss_content);
            mCardView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(0, 0, 0, "编辑");
            menu.add(0, 1, 0, "删除");
        }
    }
}
