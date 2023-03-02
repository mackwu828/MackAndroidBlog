package com.mackwu.component.ui;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.Logger;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.func.compress.BitmapUtil;
import com.mackwu.component.func.compress.CompressUtil;
import com.mackwu.component.databinding.WidgetActivityImageViewBinding;
import com.mackwu.component.util.ByteUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import net.bither.util.NativeUtil;

import java.io.File;

import io.reactivex.disposables.Disposable;

/**
 * ===================================================
 * Created by MackWu on 2021/6/24 15:12
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ImageViewActivity extends BaseActivity<BaseViewModel, WidgetActivityImageViewBinding> {

    private final String url = "http://mmbiz.qpic.cn/mmbiz/PwIlO51l7wuFyoFwAXfqPNETWCibjNACIt6ydN7vw8LeIwT7IjyG3eeribmK4rhibecvNKiaT2qeJRIWXLuKYPiaqtQ/0";
    private int degrees = 0;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        RxPermissions rxPermissions = new RxPermissions(this);
        Disposable subscribe = rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(isGranted -> {

                });

        binding.btnTest.setOnClickListener(v -> {

            Glide.with(this)
                    .load(url)
                    .into(binding.ivTest);

//            FutureTarget<Bitmap> submit = Glide.with(this)
//                    .asBitmap()
//                    .load(url)
//                    .override(300, 300)
//                    .submit();
//            Bitmap bitmap = submit.get();
        });
        // 文件转bitmap
        // /sdcard/Android/data/com.mackwu.component/files/
        binding.btnFileToBitmap.setOnClickListener(v -> {
            Logger.d("btnFileToBitmap...");
            File sourceFile = new File(getExternalFilesDir(null), "big.jpg");
            Bitmap sourceBitmap = BitmapFactory.decodeFile(sourceFile.getPath());
            Logger.d(ByteUtil.getByteCount(sourceBitmap));
        });

        /*
         * 质量压缩
         */
        // png压缩成png
        binding.btnPngToPng.setOnClickListener(v -> {
            File sourceFile = new File(getExternalFilesDir(null), "source.png");
            Bitmap sourceBitmap = BitmapFactory.decodeFile(sourceFile.getPath());
            sourceBitmap.recycle();

            File destFile = new File(getExternalFilesDir(null), "dest.png");
            CompressUtil.qualityCompress(sourceBitmap, destFile.getPath(), 100, Bitmap.CompressFormat.PNG);
        });
        // png压缩成jpg
        binding.btnPngToJpg.setOnClickListener(v -> {
            File sourceFile = new File(getExternalFilesDir(null), "source.png");
            Bitmap sourceBitmap = BitmapFactory.decodeFile(sourceFile.getPath());

            File destFile = new File(getExternalFilesDir(null), "dest.jpg");
            CompressUtil.qualityCompress(sourceBitmap, destFile.getPath(), 100, Bitmap.CompressFormat.JPEG);
        });
        // jpg压缩成jpg
        binding.btnJpgToJpg.setOnClickListener(v -> {
            Logger.d("btnJpgToJpg...");
            // 530KB 888x1920 位深24
            // 1.06MB
            // 1.13MB
            // 1.18MB
            // 1.24MB
            File sourceFile = new File(getExternalFilesDir(null), "source.jpg");
            File destFile = new File(getExternalFilesDir(null), "dest.jpg");
            Bitmap sourceBitmap = BitmapFactory.decodeFile(sourceFile.getPath());
            CompressUtil.qualityCompress(sourceBitmap, destFile.getPath(), 100, Bitmap.CompressFormat.JPEG);
        });
        // jpg压缩成jpg
        binding.btnLibJpgToJpg.setOnClickListener(v -> {
            Logger.d("btnLibJpgToJpg...");
            new Thread(() -> {
                try {
//                    Bitmap bitmap = Glide.with(this)
//                            .asBitmap()
//                            .load(R.drawable.x)
//                            .submit(1280, 1920)
//                            .get();
//                    LogUtil.d(BitmapUtil.getByteCountLog(bitmap));

                    long startTime = System.currentTimeMillis();
                    File sourceFile = new File(getExternalFilesDir(null), "source.jpg");
                    File destFile = new File(getExternalFilesDir(null), "dest.jpg");

                    Bitmap bitmap = BitmapUtil.fileToBitmap(destFile);
                    NativeUtil.saveBitmap(bitmap, 100, destFile.getPath(), true);

                    // 一张图的最小边，如果小于屏幕的最大边，则不压缩。
//                    CompressArgs args = new CompressArgs.Builder()
//                            .width(888)
//                            .height(1920)
//                            .quality(100)
//                            .ignoreSize(false)
//                            .autoRotation(false)
//                            .autoRecycle(true)
//                            .bitmapConfig(Bitmap.Config.RGB_565)
//                            .build();
//                    Light.getInstance().compress(sourceFile, args, destFile.getPath());


                    Logger.d("sourceFile.length=" + ByteUtil.bytesToStr(sourceFile.length()) +
                            ", destFile.length=" + ByteUtil.bytesToStr(destFile.length()) +
                            ", costTime=" + (System.currentTimeMillis() - startTime));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        });

        // 采样率压缩
        binding.btnSampleSizeCompress.setOnClickListener(v -> {
            // adb pull /sdcard/Android/data/com.mackwu.component/files
            File sourceFile = new File(getExternalFilesDir(null), "big.jpg");
            CompressUtil.sampleSizeCompress(sourceFile.getPath(), 1280, 800);
        });
    }


    private void rotate() {
        File file = new File(getExternalFilesDir(null).getPath(), "a.jpg");
        Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
        switch (degrees) {
            case 0:
                degrees = 90;
                break;
            case 90:
                degrees = 180;
                break;
            case 180:
                degrees = 270;
                break;
            case 270:
                degrees = 0;
                break;
        }

//        Bitmap rotateBitmap = BitmapUtil.rotate(bitmap, degrees);
//        binding.ivTest.setImageBitmap(rotateBitmap);
//        ImageUtil.bitmapToFile(rotateBitmap, file, compressFormat);
    }


    private void test() {
        // setImageResource
//        binding.ivTest.setImageResource(R.drawable.bg_picture_1);

        // setImageDrawable
//        binding.ivTest.setImageDrawable();

        // setImageBitmap
//        binding.ivTest.setImageBitmap(ImageUtil.resourceToBitmap(this, R.drawable.bg_picture_1));

        //
        binding.ivTest.setImageBitmap(CompressUtil.sampleSizeCompress(this, R.drawable.home_bg_1,
                getResources().getDimensionPixelSize(R.dimen.dp_800),
                getResources().getDimensionPixelSize(R.dimen.dp_400)));

        //
        binding.ivTest.setImageBitmap(CompressUtil.scaleCompress(BitmapUtil.resourceToBitmap(this, R.drawable.home_bg_1),
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