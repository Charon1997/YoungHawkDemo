package com.charon.www.younghawkdemo.ui.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.ui.Activities.MainActivity;
import com.charon.www.younghawkdemo.ui.Activities.NMIDActivity;
import com.charon.www.younghawkdemo.ui.MPoPuWindow;
import com.charon.www.younghawkdemo.util.PhotoUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.R.attr.path;
import static android.app.Activity.RESULT_OK;
import static com.charon.www.younghawkdemo.model.Constant.CAMERA;
import static com.charon.www.younghawkdemo.model.Constant.PHONE;

/**
 * Created by Charon on 2017/4/24.
 */

public class MeFragment extends Fragment {
    private MPoPuWindow puWindow;
    private int type;
    private Context mContext;
    private static MeFragment instance;
    private CircleImageView mCivHead;
    private TextView mTvName;
    private RelativeLayout mLlAt, mLlSet, mLlProject, mLlScan, mLlAboutUs;
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;
    public static MeFragment getInstance() {
        if (instance == null) {
            instance = new MeFragment();
        }
        return instance;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
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
        mLlAt = (RelativeLayout) view.findViewById(R.id.me_lin_at);
        mLlSet = (RelativeLayout) view.findViewById(R.id.me_set);
        mLlProject = (RelativeLayout) view.findViewById(R.id.me_project);
        mLlScan = (RelativeLayout) view.findViewById(R.id.me_scan);
        mLlAboutUs = (RelativeLayout) view.findViewById(R.id.me_about_us);
        setClick();
    }

    private void setClick() {
        mCivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //改头像
                puWindow = new MPoPuWindow(MeFragment.getInstance().getActivity(), MeFragment.getInstance(),getActivity());
                puWindow.showPopupWindow(LayoutInflater.from(MeFragment.getInstance().getActivity()).inflate(R.layout.fragment_me, null));
                puWindow.setOnGetTypeClickListener(new MPoPuWindow.onGetTypeClickListener() {
                    @Override
                    public void getType(int type) {
                        MeFragment.this.type = type;
                    }

                    @Override
                    public void getImgUri(Uri imgUri, File file) {
                        MeFragment.this.fileUri = file;
                        MeFragment.this.imageUri = imgUri;
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
            if (resultCode == RESULT_OK) {
                switch (requestCode) {

                        case CODE_CAMERA_REQUEST://拍照完成回调
                            cropImageUri = Uri.fromFile(fileCropUri);
                            Log.d("123", "onActivityResult: "+imageUri);
                            PhotoUtils.cropImageUri(MeFragment.getInstance(), imageUri, cropImageUri, 1, 1, 480, 480, CODE_RESULT_REQUEST);
                            break;
                        case CODE_GALLERY_REQUEST://访问相册完成回调
                            if (hasSdcard()) {
                                cropImageUri = Uri.fromFile(fileCropUri);
                                Uri newUri = Uri.parse(PhotoUtils.getPath(mContext, data.getData()));
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                                    newUri = FileProvider.getUriForFile(getActivity(), "com.charon.www.younghawkdemo.fileprovider", new File(newUri.getPath()));
                                }
                                PhotoUtils.cropImageUri(MeFragment.getInstance(), newUri, cropImageUri, 1, 1, 480, 480, CODE_RESULT_REQUEST);
                            } else {
                                //ToastUtils.showShort(this, "设备没有SD卡！");
                            }
                            break;
                        case CODE_RESULT_REQUEST:
                            Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, mContext);
                            if (bitmap != null) {
                                showImages(bitmap);
                            }
                            break;
                        default:
                           break;
                }
//            if (requestCode == 1) {
//                if (imgUri != null) {
//                    puWindow.onPhoto(imgUri, 300, 300);
//                }
//            } else if (requestCode == 2) {
//                if (data != null) {
//                    Uri uri = data.getData();
//                    puWindow.onPhoto(uri, 300, 300);
//                }
//            } else if (requestCode == 3) {
//                Log.d("123", type + "type");
//                if (data != null) {
//                    Bundle extras = data.getExtras();
//                    Bitmap bitmap = (Bitmap) extras.get("data");
//                    if (bitmap != null) {
//                        mCivHead.setImageBitmap(bitmap);
//                        Log.d("123", "bitmap"+bitmap.toString());
//                        saveImageToGallery(MeFragment.getInstance().getActivity(),bitmap,file);
//                    }
//                }
//            }
            }
        }
    }
    private void showImages(Bitmap bitmap) {
        mCivHead.setImageBitmap(bitmap);
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    public static void saveImageToGallery(Context context, Bitmap bmp,File file) {
        // 首先保存图片
        if (!file.exists()) {
            file.mkdir();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(),System.currentTimeMillis()+".jpg", null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
    }
}
