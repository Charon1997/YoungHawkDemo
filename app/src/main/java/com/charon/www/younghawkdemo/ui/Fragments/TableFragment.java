package com.charon.www.younghawkdemo.ui.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.charon.www.younghawkdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TableFragment extends android.app.Fragment {
    private ImageView downloadImg1,downloadImg2;

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
        View view = inflater.inflate(R.layout.fragment_table, container, false);
        downloadImg1 = (ImageView) view.findViewById(R.id.table_image_download1);
        downloadImg2 = (ImageView) view.findViewById(R.id.table_image_download2);
        downloadImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "亲，再等一等才能下载哟", Toast.LENGTH_SHORT).show();
            }
        });
        downloadImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "亲，再等一等才能下载哟", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


}
