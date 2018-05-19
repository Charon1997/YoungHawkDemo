package com.charon.www.younghawkdemo.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.charon.www.younghawkdemo.R;
import com.charon.www.younghawkdemo.util.PhotoUtils;

import java.io.File;

import static com.charon.www.younghawkdemo.model.Constant.CAMERA;
import static com.charon.www.younghawkdemo.model.Constant.PHONE;

/**
 * Created by Administrator on 2017/7/22.
 */

public class MPoPuWindow extends PopupWindow implements View.OnClickListener {
    public Context mContext;

    private int type;

    private Fragment mFragment;
    private Activity activity;
    private File file;
    private Uri ImgUri;

    private TextView mTakePhoto, mAlbumPhoto, mCancel;
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    public MPoPuWindow(Context context, Fragment mFragment,Activity activity) {
        initView(context);
        this.mFragment = mFragment;
        this.activity = activity;
    }

    private void initView(Context mContext) {
        this.mContext = mContext;
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_popu,
                null);
        setContentView(v);

        mTakePhoto = (TextView) v.findViewById(R.id.photo_take);
        mAlbumPhoto = (TextView) v.findViewById(R.id.photo_album);
        mCancel = (TextView) v.findViewById(R.id.photo_cancel);

        mTakePhoto.setOnClickListener(this);
        mAlbumPhoto.setOnClickListener(this);
        mCancel.setOnClickListener(this);

        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        // 设置SelectPicPopupWindow弹出窗体可点�?
        this.setTouchable(true);
        this.setFocusable(true);
        this.setOutsideTouchable(true);

        // 刷新状�?
        this.update();
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.popuwindow_from_bottom);
        // 实例化一个ColorDrawable颜色为半透明
        //ColorDrawable dw = new ColorDrawable(0xffffff);
        // 设置SelectPicPopupWindow弹出窗体的背景
        //this.setBackgroundDrawable(dw);
    }

    public void showPopupWindow(View parent) {

        if (!this.isShowing()) {
            this.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        } else {
            this.dismiss();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.photo_take:
                file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
                ImgUri = Uri.fromFile(file);
                Log.d("123", "take photo file"+file+"uri"+ImgUri);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                    //通过FileProvider创建一个content类型的Uri
                    ImgUri = FileProvider.getUriForFile(mContext, "com.charon.www.younghawkdemo.fileprovider", file);
                }
                PhotoUtils.takePicture(mFragment, ImgUri, CODE_CAMERA_REQUEST);
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
//                }
//                file = new File(Environment.getExternalStorageDirectory(),
//                        System.currentTimeMillis() + ".jpg");
//                Log.d("123", "file" + file.getPath());
//                //ImgUri = FileProvider.getUriForFile(mContext, mContext.getApplicationContext().getPackageName() + "com.charon.www.younghawkdemo.fileprovider", file);
//                ImgUri = FileProvider.getUriForFile(mContext,"com.charon.www.younghawkdemo.fileprovider", file);
//
//                Log.d("123", "take photo"+ImgUri);
//                int currentapiVersion = Build.VERSION.SDK_INT;
//                if (currentapiVersion < 24) {
//                    //ImgUri = FileProvider.getUriForFile(mContext,"com.charon.www.younghawkdemo.fileprovider", file);
//                    intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, ImgUri);
//                    mFragment.startActivityForResult(intent, 1);
//                } else {
//                    ContentValues contentValues = new ContentValues(1);
//                    contentValues.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
//                    Uri uri =  mFragment.getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//                    mFragment.startActivityForResult(intent, 1);
//                }

                Log.d("123", "take photo");
                type = CAMERA;
                if (listener != null) {
                    listener.getType(type);
                    listener.getImgUri(ImgUri, file);
                }

                this.dismiss();
                break;
            case R.id.photo_album:
                PhotoUtils.openPic(mFragment, CODE_GALLERY_REQUEST);
//                Intent intent2 = new Intent("android.intent.action.PICK");
//                intent2.setType("image/*");
//                Log.d("123", "album");
//                mFragment.startActivityForResult(intent2, 2);
//                Log.d("123", "album");
                type = PHONE;
                if (listener != null) {
                    listener.getType(type);
                }
                this.dismiss();
                break;
            case R.id.photo_cancel:
                this.dismiss();
                break;
            default:
                break;
        }
    }

    public void onPhoto(Uri uri, int outputX, int outputY) {
        Intent intent = null;

        intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        mFragment.startActivityForResult(intent, 3);
    }

    public interface onGetTypeClickListener {
        void getType(int type);

        void getImgUri(Uri imgUri, File file);
    }

    private onGetTypeClickListener listener;

    public void setOnGetTypeClickListener(onGetTypeClickListener listener) {
        this.listener = listener;
    }
}
