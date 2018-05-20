package com.charon.www.younghawkdemo.ui.Fragments;


import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.model.API;

/**
 * A simple {@link Fragment} subclass.
 */
public class TableFragment extends android.app.Fragment {
    private ImageView downloadImg1, downloadImg2, downloadImg3;

    private static TableFragment instance;

    public static TableFragment getInstance() {
        if (instance == null) {
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
        downloadImg3 = (ImageView) view.findViewById(R.id.table_image_download3);
        downloadImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download(API.URI_GET_DOWNLOAD1,"a.txt");
            }
        });
        downloadImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download(API.URI_GET_DOWNLOAD2,"文档1.pdf");
            }
        });
        downloadImg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download(API.URI_GET_DOWNLOAD3,"wd2.docx");
            }
        });
        return view;
    }

    private void download(String uri,String fileString){
        //创建下载任务,downloadUrl就是下载链接
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(uri));
        //指定下载路径和下载文件名
        request.setDestinationInExternalPublicDir("/download/", fileString);
        //获取下载管理器
        DownloadManager downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        //将下载任务加入下载队列，否则不会进行下载
        downloadManager.enqueue(request);
    }
}
