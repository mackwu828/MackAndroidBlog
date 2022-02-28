package com.mackwu.component.ui.widget;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.LogUtil;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.databinding.WidgetActivityImageViewBinding;
import com.mackwu.component.util.ImageUtil;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * ===================================================
 * Created by MackWu on 2021/6/24 15:12
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ImageViewActivity extends BaseActivity<BaseViewModel, WidgetActivityImageViewBinding> {

    private String url = "http://mmbiz.qpic.cn/mmbiz/PwIlO51l7wuFyoFwAXfqPNETWCibjNACIt6ydN7vw8LeIwT7IjyG3eeribmK4rhibecvNKiaT2qeJRIWXLuKYPiaqtQ/0";

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
//        test();




        new Thread(() -> {
            try {
                File file = Glide.with(this)
                        .downloadOnly()
                        .load(url)
                        .submit()
                        .get();
                // /data/user/0/com.mackwu.component/cache/image_manager_disk_cache/4aaa7ff7c1.0
                LogUtil.d("file: " + file.getAbsolutePath());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


    private void test() {
        binding.ivTest1.setImageResource(R.drawable.bg_picture_1);

        // setImageResource 115
//        binding.ivTest1.setImageResource(R.drawable.bg_picture_1);

        // setImageDrawable
//        binding.ivTest1.setImageDrawable();

        // setImageBitmap 62
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_picture_1);
//        binding.ivTest1.setImageBitmap(bitmap);

        //
        Bitmap bitmap = ImageUtil.sampleSizeCompress(this, R.drawable.bg_picture_1,
                getResources().getDimensionPixelSize(R.dimen.dp_800),
                getResources().getDimensionPixelSize(R.dimen.dp_400));
        binding.ivTest2.setImageBitmap(bitmap);

        //
        binding.ivTest3.setImageBitmap(ImageUtil.scaleCompress(bitmap,
                getResources().getDimensionPixelSize(R.dimen.dp_800),
                getResources().getDimensionPixelSize(R.dimen.dp_400)));


        /*
         * 用BitmapFactory#decodeResource获取bitmap时，原图的分辨率会被压缩
         * 实际图片分辨率=原图分辨率*(设备dpi/res目录dpi)
         *
         * 如加载xhdpi目录下一张宽高1280x800的图片:
         * 新图宽=1280*(213/320)=852
         * 新图高=800*(213/320)=533
         * bitmap大小=852*533*4=1816464 1.73MB
         */
//        LogUtil.d(" \n" +
//                "device dpi==" + DisplayUtil.getDpi(this) + " \n" +
//                "getByteCount==" + bitmap.getByteCount() + ", " + ByteUtil.bytesToStr(bitmap.getByteCount()) + " \n" +
//                "ccc==" + (852 * 533 * 4) + " \n" +
//                "width==" + bitmap.getWidth() + " \n" +
//                "height==" + bitmap.getHeight() + " \n"
//        );
    }


}