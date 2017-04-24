package com.charon.www.younghawkdemo.ui.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.charon.www.younghawkdemo.R;
import com.zzhoujay.richtext.RichText;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroPage1Fragment extends Fragment {


    public IntroPage1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro_page1, container, false);
        TextView text = (TextView) view.findViewById(R.id.text_intro_pager1);
        RichText.fromMarkdown(getString(R.string.text_introduce_page1)).into(text);
        return view;
    }

}
