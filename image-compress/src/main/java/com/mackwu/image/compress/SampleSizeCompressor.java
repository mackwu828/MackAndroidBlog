package com.mackwu.image.compress;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.mackwu.image.compress.util.ByteUtil;
import com.mackwu.image.compress.util.Logger;

/**
 * @author MackWu
 * @since 2023/3/10 17:21
 */
public class SampleSizeCompressor {

    /**
     * 采样率压缩
     *
     * @param path      文件路径
     * @param outWidth  目标宽度
     * @param outHeight 目标高度
     */
    public Bitmap sampleSizeCompress(String path, int outWidth, int outHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 获取图片原始宽高
        options.inJustDecodeBounds = true;
        // inJustDecodeBounds=true时，bitmap并未加载到内存中，只用来读取图片的宽高。
        BitmapFactory.decodeFile(path, options);
        // 设置采样率
        options.inSampleSize = calculateInSampleSize(options, outWidth, outHeight);
        // 压缩后的bitmap
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        Logger.d("sampleSizeCompress...  inSampleSize=" + options.inSampleSize + ", " + ByteUtil.getByteCount(bitmap));
        return bitmap;
    }

    /**
     * 计算采样率
     *
     * @param options   BitmapFactory.Options
     * @param outWidth  目标宽度
     * @param outHeight 目标高度
     */
    private int calculateInSampleSize(BitmapFactory.Options options, int outWidth, int outHeight) {
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;
        int imageMinSize = Math.min(width, height);
        int destMaxSize = Math.max(outWidth, outHeight);
        while ((imageMinSize / inSampleSize > destMaxSize)) {
            inSampleSize *= 2;
        }
        return inSampleSize;
    }

}
