package com.mackwu.component.ui.view.puzzle;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * @author MackWu
 * @since 2022/11/18 16:31
 */
public class MatrixUtil {

    public static void a() {

    }

    public static Matrix generateCenterCropMatrix(Rect rectF, int width, int height,
                                                  float extraSize) {
        Matrix matrix = new Matrix();

        float offsetX = rectF.centerX() - width / 2;
        float offsetY = rectF.centerY() - height / 2;

        matrix.postTranslate(offsetX, offsetY);

        float scale;

        if (width * rectF.height() > rectF.width() * height) {
            scale = (rectF.height() + extraSize) / height;
        } else {
            scale = (rectF.width() + extraSize) / width;
        }

        matrix.postScale(scale, scale, rectF.centerX(), rectF.centerY());

        return matrix;
    }
}
