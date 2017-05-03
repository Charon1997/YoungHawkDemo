package com.charon.www.younghawkdemo.ui.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.charon.www.younghawkdemo.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Charon on 2017/4/24.
 */

public class MeFragment extends Fragment {
    private static MeFragment instance;
    private CircleImageView mCivHead;
    private TextView mTvName;
    private LinearLayout mLlAt,mLlSet,mLlProject;



    public static MeFragment getInstance() {
        if (instance == null) {
            instance = new MeFragment();
        }
        return instance;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);

        addView(view);
        return view;
    }

    private void addView(View view) {
        mCivHead = (CircleImageView) view.findViewById(R.id.me_img_head);
        mTvName = (TextView) view.findViewById(R.id.me_title_name);
        mLlAt = (LinearLayout) view.findViewById(R.id.me_lin_at);
        mLlSet = (LinearLayout) view.findViewById(R.id.me_set);
        mLlProject = (LinearLayout) view.findViewById(R.id.me_project);
    }
}
