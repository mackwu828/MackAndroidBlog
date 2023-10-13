package com.mackwu.image.scale;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;
import com.mackwu.image.scale.util.ScaleLogger;
import com.mackwu.image.scale.util.TransformationUtil;

import java.security.MessageDigest;
import java.util.logging.Logger;

/**
 * @author MackWu
 * @since 2023/10/11 15:06
 */
public class FitCenterTransformation extends BitmapTransformation {

    private static final String ID = "com.mackwu.image.scale.FitCenterTransformation";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return fitCenter(pool, toTransform, outWidth, outHeight);
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }

    public static Bitmap fitCenter(
            @NonNull BitmapPool pool, @NonNull Bitmap inBitmap, int width, int height) {
//        if (inBitmap.getWidth() == width && inBitmap.getHeight() == height) {
//            if (Log.isLoggable(TAG, Log.VERBOSE)) {
//                Log.v(TAG, "requested target size matches input, returning input");
//            }
//            return inBitmap;
//        }
        final float widthPercentage = width / (float) inBitmap.getWidth();
        final float heightPercentage = height / (float) inBitmap.getHeight();
        final float minPercentage = Math.min(widthPercentage, heightPercentage);

        ScaleLogger.d(String.format("fitCenter...  (%s, %s), (%s, %s)", width, height, inBitmap.getWidth(), inBitmap.getHeight()));

        // Round here in case we've decoded exactly the image we want, but take the floor below to
        // avoid a line of garbage or blank pixels in images.
        int targetWidth = Math.round(minPercentage * inBitmap.getWidth());
        int targetHeight = Math.round(minPercentage * inBitmap.getHeight());

//        if (inBitmap.getWidth() == targetWidth && inBitmap.getHeight() == targetHeight) {
//            if (Log.isLoggable(TAG, Log.VERBOSE)) {
//                Log.v(TAG, "adjusted target size matches input, returning input");
//            }
//            return inBitmap;
//        }

        // Take the floor of the target width/height, not round. If the matrix
        // passed into drawBitmap rounds differently, we want to slightly
        // overdraw, not underdraw, to avoid artifacts from bitmap reuse.
        targetWidth = (int) (minPercentage * inBitmap.getWidth());
        targetHeight = (int) (minPercentage * inBitmap.getHeight());

        Bitmap.Config config = TransformationUtil.getNonNullConfig(inBitmap);
        Bitmap toReuse = pool.get(targetWidth, targetHeight, config);

        // We don't add or remove alpha, so keep the alpha setting of the Bitmap we were given.
        TransformationUtils.setAlpha(inBitmap, toReuse);
        Matrix matrix = new Matrix();
        matrix.setScale(minPercentage, minPercentage);
        TransformationUtil.applyMatrix(inBitmap, toReuse, matrix);

        return toReuse;
    }

}
