package com.charon.www.younghawkdemo.ui.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charon.www.younghawkdemo.R;

/**
 * Created by Administrator on 2017/4/24.
 */

public class DiscussFragment extends Fragment {
    private static DiscussFragment instance;

    public static DiscussFragment getInstance() {
        if (instance == null) {
            instance = new DiscussFragment();
        }

        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discuss, container, false);
        return view;
    }
}
