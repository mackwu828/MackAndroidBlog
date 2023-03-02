package com.mackwu.component.func.compress;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Pair;

import com.mackwu.base.util.Logger;
import com.mackwu.component.util.ByteUtil;
import com.mackwu.component.util.IOUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * ===================================================
 * Created by MackWu on 2022/3/22 14:32
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class CompressUtil {

    /**
     * 质量压缩。通过改变图片质量降低图片文件的大小。不会降低图片在内存中的大小。
     *
     * @param context    context
     * @param resourceId resourceId
     * @param quality    质量。0-100
     * @param format     格式
     */
    public static Bitmap qualityCompress(Context context, int resourceId, int quality, Bitmap.CompressFormat format) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        boolean result = bitmap.compress(format, quality, outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        bitmap = BitmapFactory.decodeStream(inputStream);
        Logger.d("qualityCompress...  result=" + result);
        return bitmap;
    }

    /**
     * 质量压缩
     *
     * @param bitmap   bitmap
     * @param outPath 目标文件路径
     * @param format   Bitmap.CompressFormat.JPEG
     * @return 是否压缩成功
     */
    public static boolean qualityCompress(Bitmap bitmap, String outPath, int quality, Bitmap.CompressFormat format) {
        FileOutputStream fos = null;
        try {
            long startTime = System.currentTimeMillis();
            File outFile = new File(outPath);
            fos = new FileOutputStream(outFile);
            boolean result = bitmap.compress(format, quality, fos);
            Logger.d("qualityCompress...  result=" + result + ", length=" + ByteUtil.bytesToStr(outFile.length()) + ", costTime=" + (System.currentTimeMillis() - startTime));
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(fos);
        }
        return false;
    }

    /**
     * 采样率压缩。通过改变图片分辨率降低图片在内存中的大小。也叫临近采样。
     *
     * @param context    context
     * @param resourceId 资源id
     * @param destWidth  目标宽度
     * @param destHeight 目标高度
     */
    public static Bitmap sampleSizeCompress(Context context, int resourceId, int destWidth, int destHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        // 获取图片原始宽高
        options.inJustDecodeBounds = true;
        // inJustDecodeBounds=true时，bitmap并未加载到内存中，只用来读取图片的宽高。
        BitmapFactory.decodeResource(context.getResources(), resourceId, options);

        // 设置采样率
        options.inSampleSize = calculateInSampleSize(options, destWidth, destHeight);

        // 压缩后的bitmap
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);
        Logger.d("sampleSizeCompress...  inSampleSize=" + options.inSampleSize + ", " + ByteUtil.getByteCount(bitmap));
        return bitmap;
    }

    /**
     * 采样率压缩
     *
     * @param sourcePath 源路径
     * @param destWidth  目标宽度
     * @param destHeight 目标高度
     */
    public static Bitmap sampleSizeCompress(String sourcePath, int destWidth, int destHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        // 获取图片原始宽高
        options.inJustDecodeBounds = true;
        // inJustDecodeBounds=true时，bitmap并未加载到内存中，只用来读取图片的宽高。
        BitmapFactory.decodeFile(sourcePath, options);

        // 设置采样率
        options.inSampleSize = calculateInSampleSize(options, destWidth, destHeight);

        // 压缩后的bitmap
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(sourcePath, options);
        Logger.d("sampleSizeCompress...  inSampleSize=" + options.inSampleSize + ", " + ByteUtil.getByteCount(bitmap));
        return bitmap;
    }

    /**
     * 计算采样率
     *
     * @param options    BitmapFactory.Options
     * @param destWidth  目标宽度
     * @param destHeight 目标高度
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int destWidth, int destHeight) {
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;
        while ((width / inSampleSize > destWidth) || (height / inSampleSize > destHeight)) {
            inSampleSize *= 2;
        }
        return inSampleSize;
    }

    /**
     * 采样率压缩
     *
     * @param sourcePath 文件路径
     */
    public static Bitmap sampleSizeCompress(String sourcePath) {
        Pair<Integer, Integer> pair = calculateWidthHeightByDatum(sourcePath);
        return sampleSizeCompress(sourcePath, pair.first, pair.second);
    }

    /**
     * 计算宽高。根据基准值
     *
     * @param filePath 文件路径
     */
    public static Pair<Integer, Integer> calculateWidthHeightByDatum(String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        int width = options.outWidth;
        int height = options.outHeight;

        // 压缩基准值
        int datum = 1280;
        float proportion = 1.0f;
        int newWidth = width;
        int newHeight = height;

        // 当宽高都大于基准值时，最小边的压缩值取基准值，最小边除以基准值得出压缩比例，最大边再除以该比例，得出最大边的压缩值
        if (width > datum && height > datum) {
            proportion = (float) Math.min(width, height) / datum;
            int max = (int) (Math.max(width, height) / proportion);
            newWidth = width > height ? max : datum;
            newHeight = height >= width ? max : datum;
        }

        Logger.d("calculateWidthAndHeightByDatum...  width=" + width + ", height=" + height + ", proportion=" + proportion + ", newWidth=" + newWidth + ", newHeight=" + newHeight);
        return new Pair<>(newWidth, newHeight);
    }

    /**
     * 矩阵缩放压缩。通过改变图片分辨率降低图片在内存中的大小。是采样率压缩的一种，也叫双线性采样。
     *
     * @param bitmap     bitmap
     * @param destWidth  目标宽度
     * @param destHeight 目标高度
     */
    public static Bitmap scaleCompress(Bitmap bitmap, int destWidth, int destHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        if (width != destWidth || height != destHeight) {
            float sx = destWidth * 1.0f / width;
            float sy = destHeight * 1.0f / height;
            matrix.setScale(sx, sy);
        }
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        Logger.d("scaleCompress...  " + ByteUtil.getByteCount(bitmap));
        return bitmap;
    }

    /**
     * 修改像素点的数据格式。通过修改单位像素的大小来降低图片的内存大小。
     *
     * @param context    context
     * @param resourceId 资源id
     * @param config     See {@link Bitmap.Config} 如 Bitmap.Config.RGB_565
     * @return bitmap
     */
    public static Bitmap pixelCompress(Context context, int resourceId, Bitmap.Config config) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = config;
        return BitmapFactory.decodeResource(context.getResources(), resourceId, options);
    }

}
