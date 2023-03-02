package com.mackwu.component.func.compress;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.File;

/**
 * ===================================================
 * Created by MackWu on 2021/12/20 14:54
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class BitmapUtil {

    /**
     * resourceId to bitmap
     *
     * @param context    context
     * @param resourceId See {@link BitmapFactory#decodeResource(Resources, int)}
     */
    public static Bitmap resourceToBitmap(Context context, int resourceId) {
        return BitmapFactory.decodeResource(context.getResources(), resourceId);
    }

    /**
     * drawable to bitmap
     *
     * @param drawable See {@link BitmapDrawable#getBitmap()}
     *                 See {@link Bitmap#createBitmap(int, int, Bitmap.Config)}
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;
        // 如果drawable是BitmapDrawable，直接通过BitmapDrawable获取bitmap
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                bitmap = bitmapDrawable.getBitmap();
            }
        }
        // 否则，通过drawable的宽高生成bitmap
        else {
            if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
                bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565); // Single color bitmap will be created of 1x1 pixel
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.RGB_565);
            }
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }
        return bitmap;
    }


    /**
     * file to bitmap
     * 慎用。大文件容易出现OOM，需要先压缩文件再转成Bitmap。
     *
     * @param file See {@link BitmapFactory#decodeFile(String)}
     */
    @Deprecated
    public static Bitmap fileToBitmap(File file) {
        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }

//    /**
//     * 获取像素格式 See {@link Bitmap.Config}
//     * @param bitmap bitmap
//     */
//    public static Bitmap.Config getPixelConfig(Bitmap bitmap){
//        return bitmap.getConfig();
//    }

    public static Bitmap urlToBitmap(String url) {
        return null;
    }

}
