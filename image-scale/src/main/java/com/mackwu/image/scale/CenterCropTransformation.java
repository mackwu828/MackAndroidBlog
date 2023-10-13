package com.mackwu.image.scale;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;
import com.mackwu.image.scale.util.ScaleLogger;
import com.mackwu.image.scale.util.TransformationUtil;

import java.security.MessageDigest;

/**
 * @author MackWu
 * @since 2023/9/15 19:11
 */
public class CenterCropTransformation extends BitmapTransformation {

    private static final String ID = "com.zeasn.frame.CenterCropTransformation";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);
    private final int screenWidth;
    private final int screenHeight;
    private final Rect rect;

    public CenterCropTransformation(Context context, Rect rect) {
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
//        screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        screenHeight = getRealHeight(context);
        this.rect = rect;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return centerCrop(pool, toTransform);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof CenterCrop;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }

    private Bitmap centerCrop(@NonNull BitmapPool pool, @NonNull Bitmap inBitmap) {
        ScaleLogger.d("centerCrop...  " + inBitmap.getWidth() + ", " + inBitmap.getHeight());
        if (inBitmap.getWidth() == screenWidth && inBitmap.getHeight() == screenHeight) {
            return inBitmap;
        }
        // From ImageView/Bitmap.createScaledBitmap.
        final float scale;
        final float dx;
        final float dy;
        Matrix m = new Matrix();
        float focusX = rect == null ? 0.5f : rect.left / 100f;
        float focusY = rect == null ? 0.5f : rect.top / 100f;
        if (inBitmap.getWidth() * screenHeight > screenWidth * inBitmap.getHeight()) {
            scale = (float) screenHeight / (float) inBitmap.getHeight();
            dx = (screenWidth - inBitmap.getWidth() * scale) * focusX;
            dy = 0;
        } else {
            scale = (float) screenWidth / (float) inBitmap.getWidth();
            dx = 0;
            dy = (screenHeight - inBitmap.getHeight() * scale) * focusY;
        }
        m.postTranslate(0, 0);
        m.setScale(scale, scale);
        m.postTranslate((int) (dx + 0.5f), (int) (dy + 0.5f));

        Bitmap result = pool.get(screenWidth, screenHeight, TransformationUtil.getNonNullConfig(inBitmap));
        // We don't add or remove alpha, so keep the alpha setting of the Bitmap we were given.
        TransformationUtils.setAlpha(inBitmap, result);
        TransformationUtil.applyMatrix(inBitmap, result, m);
        return result;
    }

    private int getRealHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getRealSize(size);
        return size.y;
    }

}
