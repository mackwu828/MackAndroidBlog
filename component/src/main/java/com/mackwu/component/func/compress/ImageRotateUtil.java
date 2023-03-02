package com.mackwu.component.func.compress;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.mackwu.base.util.Logger;
import com.mackwu.component.util.ByteUtil;

/**
 * ===================================================
 * Created by MackWu on 2022/3/23 18:11
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ImageRotateUtil {

    /**
     * @param bitmap
     * @param degrees
     * @return
     */
    public static Bitmap rotate(Bitmap bitmap, int degrees) {
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap rotateBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        Logger.d("rotate...  degrees=" + degrees + ", getByteCount=" + rotateBitmap.getByteCount() + ", " + ByteUtil.bytesToStr(rotateBitmap.getByteCount()));
        return rotateBitmap;
    }

}
