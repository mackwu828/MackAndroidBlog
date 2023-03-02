package com.mackwu.component.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.mackwu.base.util.Logger;
import com.mackwu.component.util.ByteUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2022/3/7 19:59
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class AdjustImageView extends AppCompatImageView {

    private Bitmap bitmap;

    public AdjustImageView(Context context) {
        this(context, null);
    }

    public AdjustImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdjustImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }


    private void initView() {
        // inSampleSize==8, getByteCount==110684, 108.09KB
//        bitmap = ImageUtil.sampleSizeCompress(getContext(), R.drawable.ic_long_pic,
//                DisplayUtil.getWidth(getContext()),
//                DisplayUtil.getHeight(getContext())
//        );

        setImageBitmap(bitmap);
    }

    public boolean flag = true;
    public void set(){
        if (flag) {
            b(bitmap);
        } else {
            setScaleType(ScaleType.CENTER_CROP);
//            Bitmap bitmap = ImageUtil.sampleSizeCompress(getContext(), R.drawable.ic_long_pic,
//                    DisplayUtil.getWidth(getContext()),
//                    DisplayUtil.getHeight(getContext())
//            );
            setImageBitmap(bitmap);
        }
        flag = !flag;
    }

    private void b(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        try {
            BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(inputStream, false);
            Rect rect = new Rect();
            BitmapFactory.Options options = new BitmapFactory.Options();
            int imgWidth = decoder.getWidth();
            int imgHeight = decoder.getHeight();
            int screenWidth = getContext().getResources().getDisplayMetrics().widthPixels;
            int screenHeight = getContext().getResources().getDisplayMetrics().heightPixels;
            Logger.d("imgWidth=" + imgWidth + ", imgHeight=" + imgHeight + ", screenWidth=" + screenWidth + ", screenHeight=" + screenHeight);
            rect.set(0, imgHeight / 2 - screenHeight / 2, imgWidth, imgHeight / 2 + screenHeight / 2);
//            rect.set(0, imgHeight / 2 - screenHeight, screenWidth, imgHeight / 2 + screenHeight);
            Bitmap b = decoder.decodeRegion(rect, options);
            setImageBitmap(b);


            setScaleType(ScaleType.MATRIX);
            Matrix matrix = getImageMatrix();
            float scale;
            if (imgWidth * screenHeight > imgHeight * screenWidth) {
                scale = screenHeight * 1f / imgHeight;
            } else {
                scale = screenWidth * 1f / imgWidth;
            }
            Logger.d("scale=" + scale);
            matrix.setScale(scale, scale);
            setImageMatrix(matrix);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private Bitmap a(Bitmap bitmap) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        try {
            BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(inputStream, false);
            int width = decoder.getWidth();
            int height = decoder.getHeight();
            int DEFAULT_MAX_BITMAP_DIMENSION = 2048;
            // sum
            int sum = height / DEFAULT_MAX_BITMAP_DIMENSION;
            // redundant
            int redundant = height % DEFAULT_MAX_BITMAP_DIMENSION;
            // bitmaps
            List<Bitmap> bitmaps = new ArrayList<>();

            // 图片的长度小于2048
            if (sum == 0) {
                bitmaps.add(bitmap);
            }
            // 图片的长度大于2048，需要切分图片
            else {
                Rect rect = new Rect();
                BitmapFactory.Options options = new BitmapFactory.Options();
                for (int i = 0; i < sum; i++) {
                    rect.set(0, i * DEFAULT_MAX_BITMAP_DIMENSION, width, (i + 1) * DEFAULT_MAX_BITMAP_DIMENSION);
                    Bitmap b = decoder.decodeRegion(rect, options);
                    bitmaps.add(b);
                }
                if (redundant > 0) {
                    rect.set(0, sum * DEFAULT_MAX_BITMAP_DIMENSION, width, height);
                    Bitmap b = decoder.decodeRegion(rect, options);
                    bitmaps.add(b);
                }
            }

            // 长图的bitmap
            Bitmap bigBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            // 拼接长图
            Canvas bigCanvas = new Canvas(bigBitmap);
            Paint paint = new Paint();
            int h = 0;
            for (Bitmap b : bitmaps) {
                bigCanvas.drawBitmap(b, 0, h, paint);
                h += b.getHeight();
                b.recycle();
                b = null;
            }

            Logger.d("sum==" + sum + ", redundant==" + redundant + ", getByteCount==" + bigBitmap.getByteCount() + ", " + ByteUtil.bytesToStr(bigBitmap.getByteCount()));
            return bigBitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
