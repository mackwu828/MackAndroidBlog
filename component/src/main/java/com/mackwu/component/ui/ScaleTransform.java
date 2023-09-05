package com.mackwu.component.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.mackwu.base.util.Logger;

import java.security.MessageDigest;

/**
 * @author MackWu
 * @since 2023/9/5 16:37
 */
public class ScaleTransform extends BitmapTransformation {

    private int screenWidth;
    private int screenHeight;
    private float scale;
    private float dx;
    private  float dy;

    public ScaleTransform(int screenWidth, int screenHeight, float scale, float dx, float dy) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.scale = scale;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap inBitmap, int outWidth, int outHeight) {
        final float widthPercentage = screenWidth / (float) inBitmap.getWidth();
        final float heightPercentage = screenHeight / (float) inBitmap.getHeight();
        final float minPercentage = Math.min(widthPercentage, heightPercentage);
        int targetWidth = Math.round(minPercentage * inBitmap.getWidth());
        int targetHeight = Math.round(minPercentage * inBitmap.getHeight());
        targetWidth = (int) (minPercentage * inBitmap.getWidth());
        targetHeight = (int) (minPercentage * inBitmap.getHeight());
        // inBitmap.getWidth()=1920, inBitmap.getHeight()4160, width=1920, height=1008, widthPercentage=1.0, heightPercentage=0.2423077, targetWidth=465, targetHeight=1008
        Logger.d("inBitmap.getWidth()=" + inBitmap.getWidth() + ", inBitmap.getHeight()" + inBitmap.getHeight()
                + ", screenWidth=" + screenWidth + ", screenHeight=" + screenHeight
                + ", widthPercentage=" + widthPercentage
                + ", heightPercentage=" + heightPercentage
                + ", targetWidth=" + targetWidth
                + ", targetHeight=" + targetHeight
                + ", scale=" + scale
                + ", dx=" + dx
                + ", dy=" + dy
        );

        Bitmap targetBitmap = pool.get(screenWidth, screenHeight,  Bitmap.Config.ARGB_4444);
        Matrix matrix = new Matrix();
        matrix.preScale(minPercentage, minPercentage);
        matrix.postScale(scale, scale);
        matrix.postTranslate(dx, dy);
        Canvas canvas = new Canvas(targetBitmap);
        canvas.drawBitmap(inBitmap, matrix, null);
        return targetBitmap;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }

}
