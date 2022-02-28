package com.mackwu.component.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.mackwu.base.util.LogUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * ===================================================
 * Created by MackWu on 2021/12/20 14:54
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class ImageUtil {

    /**
     * resourceId to bitmap
     *
     * @param context    context
     * @param resourceId resourceId
     */
    public static Bitmap resourceToBitmap(Context context, int resourceId) {
        return BitmapFactory.decodeResource(context.getResources(), resourceId);
    }

    /**
     * drawable to bitmap
     *
     * @param drawable drawable
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap;

        // BitmapDrawable
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        //
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.RGB_565);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }


    /**
     * 质量压缩。不会改变图片在内存中的大小，仅会在图片在磁盘中的大小，即压缩的是图片文件的大小。
     * 原理：通过改变图片的位深和透明度来减小图片占用的磁盘大小。
     */
    public static Bitmap qualityCompress(Context context, int resourceId, int quality) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.WEBP, quality, outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        return BitmapFactory.decodeStream(inputStream);
    }


    /**
     * 尺寸压缩、采样率压缩。临近采样。
     * 原理：通过设置inSampleSize，请求解码器对原图进行二次采样，来改变图片的分辨率。
     *
     * @param context    context
     * @param resourceId 资源id
     * @param reqWidth   目标宽度
     * @param reqHeight  目标高度
     */
    public static Bitmap sampleSizeCompress(Context context, int resourceId, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        // 获取图片原始宽高
        options.inJustDecodeBounds = true;
        // inJustDecodeBounds=true时，bitmap并未加载到内存中，只用来读取图片的宽高。
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);
        int width = options.outWidth;
        int height = options.outHeight;

        // 设置采样率
        options.inJustDecodeBounds = false;
        int inSampleSize = 1;
        while ((width / inSampleSize > reqWidth) || (height / inSampleSize > reqHeight)) {
            inSampleSize *= 2;
        }
        options.inSampleSize = inSampleSize;

        // 压缩后的bitmap
        bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);
        LogUtil.d("bitmap inSampleSize==" + inSampleSize + ", getByteCount==" + bitmap.getByteCount() + ", " + ByteUtil.bytesToStr(bitmap.getByteCount()));
        return bitmap;
    }

    /**
     * 尺寸压缩、采样率压缩。双线性采样。矩阵缩放压缩。
     *
     * @param bitmap    bitmap
     * @param reqWidth  目标宽度
     * @param reqHeight 目标高度
     */
    public static Bitmap scaleCompress(Bitmap bitmap, int reqWidth, int reqHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        if (width != reqWidth || height != reqHeight) {
            float sx = reqWidth * 1.0f / width;
            float sy = reqHeight * 1.0f / height;
            matrix.setScale(sx, sy);
        }
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        LogUtil.d("bitmap getByteCount==" + bitmap.getByteCount() + ", " + ByteUtil.bytesToStr(bitmap.getByteCount()));
        return bitmap;
    }


    /**
     * 修改像素点的数据格式。通过修改单位像素的大小来实现图片压缩。
     *
     * @param context    context
     * @param resourceId 资源id
     * @param config     Bitmap.Config.RGB_565
     * @return bitmap
     */
    public static Bitmap pixelCompress(Context context, int resourceId, Bitmap.Config config) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = config;
        return BitmapFactory.decodeResource(context.getResources(), resourceId, options);
    }

}
