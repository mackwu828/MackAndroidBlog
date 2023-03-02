package com.mackwu.component.ui.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.mackwu.base.util.Logger;

import java.security.MessageDigest;

/**
 * @author MackWu
 * @since 2022/12/22 17:29
 */
public class MyTransformation extends BitmapTransformation {

    private int width;
    private int height;

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        width = width == 0 ? toTransform.getWidth() : width;
        height = height == 0 ? toTransform.getHeight() : height;

        Logger.d("width=" + width + ", height=" + height);

        Bitmap.Config config = toTransform.getConfig() != null ? toTransform.getConfig() : Bitmap.Config.ARGB_8888;
        Bitmap bitmap = pool.get(width, height, config);

        Logger.d("bitmap=" + bitmap);

        bitmap.setHasAlpha(true);

        float scaleX = (float) width / toTransform.getWidth();
        float scaleY = (float) height / toTransform.getHeight();
        float scale = Math.max(scaleX, scaleY);

        float scaledWidth = scale * toTransform.getWidth();
        float scaledHeight = scale * toTransform.getHeight();
        float left = (width - scaledWidth) / 2;
        float top = (height - scaledHeight) / 2;
        RectF targetRect = new RectF(left, top, left + scaledWidth, top + scaledHeight);
        bitmap.setDensity(toTransform.getDensity());

        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(toTransform, null, targetRect, null);

        return bitmap;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }
}
