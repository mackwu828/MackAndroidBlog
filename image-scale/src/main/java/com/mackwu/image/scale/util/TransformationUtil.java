package com.mackwu.image.scale.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import androidx.annotation.NonNull;

/**
 * @author MackWu
 * @since 2023/9/15 19:13
 */
public class TransformationUtil {

    @NonNull
    public static Bitmap.Config getNonNullConfig(@NonNull Bitmap bitmap) {
        return bitmap.getConfig() != null ? bitmap.getConfig() : Bitmap.Config.ARGB_8888;
    }

    public static void applyMatrix(
            @NonNull Bitmap inBitmap, @NonNull Bitmap targetBitmap, Matrix matrix) {
        Canvas canvas = new Canvas(targetBitmap);
        canvas.drawBitmap(inBitmap, matrix, null);
        clear(canvas);
    }

    private static void clear(Canvas canvas) {
        canvas.setBitmap(null);
    }
}
