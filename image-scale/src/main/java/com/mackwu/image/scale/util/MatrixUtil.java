package com.mackwu.image.scale.util;


import android.graphics.Matrix;
import android.text.TextUtils;

/**
 * @author MackWu
 * @since 2023/9/13 18:15
 */
public class MatrixUtil {

    public static String matrixToString(Matrix matrix){
        float[] values = new float[9];
        matrix.getValues(values);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            builder.append(values[i]);
            if (i < values.length - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }

    public static Matrix stringToMatrix(String matrixString){
        String[] matrixStr = matrixString.split(",");
        float[] values = new float[matrixStr.length];
        for (int i = 0; i < matrixStr.length; i++) {
            values[i] = Float.parseFloat(matrixStr[i]);
        }
        Matrix matrix = new Matrix();
        matrix.setValues(values);
        return matrix;
    }

    public static float getScale(Matrix matrix) {
        if (matrix == null) {
            return 0;
        }
        final float[] matrixValues = new float[9];
        matrix.getValues(matrixValues);
        return matrixValues[Matrix.MSCALE_X];
    }

    public static float getTransX(Matrix matrix) {
        if (matrix == null) {
            return 0;
        }
        final float[] matrixValues = new float[9];
        matrix.getValues(matrixValues);
        return matrixValues[Matrix.MTRANS_X];
    }

    public static float getTransY(Matrix matrix) {
        if (matrix == null) {
            return 0;
        }
        final float[] matrixValues = new float[9];
        matrix.getValues(matrixValues);
        return matrixValues[Matrix.MTRANS_Y];
    }

}
