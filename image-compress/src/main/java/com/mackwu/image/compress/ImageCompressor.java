package com.mackwu.image.compress;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.mackwu.image.compress.util.ByteUtil;
import com.mackwu.image.compress.util.IOUtil;
import com.mackwu.image.compress.util.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author MackWu
 * @since 2023/4/20 11:41
 */
public class ImageCompressor {

    private static ImageCompressor instance;

    private ImageCompressor() {
    }

    public static ImageCompressor getInstance() {
        if (instance == null) {
            instance = new ImageCompressor();
        }
        return instance;
    }


    public void compress() {

    }

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

    public boolean qualityCompress(Bitmap bitmap, String outPath, Bitmap.CompressFormat format, int quality) {
        FileOutputStream fos = null;
        try {
            File destFile = new File(outPath);
            fos = new FileOutputStream(destFile);
            bitmap.compress(format, quality, fos);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            IOUtil.close(fos);
        }
    }

    public boolean qualityCompress(Bitmap bitmap, String outPath) {
        return qualityCompress(bitmap, outPath, Bitmap.CompressFormat.JPEG, 80);
    }
}
