package com.charon.www.younghawkdemo.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.util.TextCircleImageView;

import java.util.List;
import java.util.Map;

/**
 * Created by Rye on 2017/3/10.
 */

public class ItemAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String,Object>> data;
    public ItemAdapter(Context context, List<Map<String, Object>> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View myView = LayoutInflater.from(context).inflate(R.layout.project_list_mail,null);
        TextView textView = (TextView) myView.findViewById(R.id.mail_list_projectName);
        TextCircleImageView textImageView = (TextCircleImageView) myView.findViewById(R.id.text_image);
        String  name = (String) data.get(i).get("name");
        textView.setText(name);
        textImageView.setText(name);
        textImageView.setDefaultBackgroundColor(i);
        return myView;
    }
}
