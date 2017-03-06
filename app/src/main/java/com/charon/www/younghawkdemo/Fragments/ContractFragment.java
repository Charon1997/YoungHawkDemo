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
public class ContractFragment extends android.app.Fragment {
    private static ContractFragment instance;

    public ContractFragment() {
        // Required empty public constructor
    }

    public static ContractFragment getInstance() {
        if(instance == null){
            instance = new ContractFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contract, container, false);
    }


}
