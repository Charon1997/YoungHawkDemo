package com.charon.www.younghawkdemo.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charon.www.younghawkdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TableFragment extends android.app.Fragment {

    private static TableFragment instance;

    public static TableFragment getInstance(){
        if(instance == null){
            instance = new TableFragment();
        }
        return instance;
    }

    public TableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_table, container, false);
    }

}
