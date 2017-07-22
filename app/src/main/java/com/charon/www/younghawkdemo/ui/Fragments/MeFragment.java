package com.charon.www.younghawkdemo.ui.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.ui.Activities.MainActivity;
import com.charon.www.younghawkdemo.ui.Activities.NMIDActivity;
import com.charon.www.younghawkdemo.ui.MPoPuWindow;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;
import static com.charon.www.younghawkdemo.model.Constant.CAMERA;
import static com.charon.www.younghawkdemo.model.Constant.PHONE;

/**
 * Created by Charon on 2017/4/24.
 */

public class MeFragment extends Fragment {
    private File file;
    private Uri imgUri;
    private MPoPuWindow puWindow;
    private int type;
    private static MeFragment instance;
    private CircleImageView mCivHead;
    private TextView mTvName;
    private LinearLayout mLlAt, mLlSet, mLlProject, mLlScan, mLlAboutUs;


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
        mLlScan = (LinearLayout) view.findViewById(R.id.me_scan);
        mLlAboutUs = (LinearLayout) view.findViewById(R.id.me_about_us);
        setClick();
    }

    private void setClick() {
        mCivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //改头像
                puWindow = new MPoPuWindow(MeFragment.getInstance().getActivity(), MeFragment.getInstance());
                puWindow.showPopupWindow(LayoutInflater.from(MeFragment.getInstance().getActivity()).inflate(R.layout.fragment_me, null));
                puWindow.setOnGetTypeClckListener(new MPoPuWindow.onGetTypeClckListener() {
                    @Override
                    public void getType(int type) {
                        MeFragment.this.type = type;
                    }

                    @Override
                    public void getImgUri(Uri imgUri, File file) {
                        MeFragment.this.file = file;
                        MeFragment.this.imgUri = imgUri;
                    }
                });

            }
        });
        mLlAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AtMe
                Toast.makeText(MeFragment.getInstance().getActivity(), "暂未开发", Toast.LENGTH_SHORT).show();
            }
        });
        mLlSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相关设置
                Toast.makeText(MeFragment.getInstance().getActivity(), "暂未开发", Toast.LENGTH_SHORT).show();
            }
        });
        mLlProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //我的项目
                Toast.makeText(MeFragment.getInstance().getActivity(), "暂未开发", Toast.LENGTH_SHORT).show();
            }
        });
        mLlScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //扫一扫
                Toast.makeText(MeFragment.getInstance().getActivity(), "暂未开发", Toast.LENGTH_SHORT).show();
            }
        });
        mLlAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeFragment.getInstance().getActivity(), NMIDActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //选择了确定
        if (resultCode == RESULT_OK) {
            Log.d("123", requestCode + "code");
            if (requestCode == 1) {
                if (imgUri != null) {
                    puWindow.onPhoto(imgUri, 300, 300);
                }
            } else if (requestCode == 2) {
                if (data != null) {
                    Uri uri = data.getData();
                    puWindow.onPhoto(uri, 300, 300);
                }
            } else if (requestCode == 3) {
                Log.d("123", type + "type");
                if (data != null) {
                    Bundle extras = data.getExtras();
                    Bitmap bitmap = (Bitmap) extras.get("data");
                    if (bitmap != null) {
                        mCivHead.setImageBitmap(bitmap);
                        Log.d("123", "bitmap"+bitmap.toString());
                    }
                }
            }
        }

    }
}
