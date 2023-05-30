package com.mackwu.image.compress;

import android.graphics.Bitmap;

import com.mackwu.image.compress.util.IOUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author MackWu
 * @since 2023/3/10 16:55
 */
public class QualityCompressor {

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
