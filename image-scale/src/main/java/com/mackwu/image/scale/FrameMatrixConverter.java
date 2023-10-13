package com.mackwu.image.scale;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;


/**
 * @author MackWu
 * @since 2023/9/13 18:22
 */
public class FrameMatrixConverter {

    private final ImageView imageView;
    private final float[] matrixValues = new float[9];
    private final RectF displayRect = new RectF();

    public FrameMatrixConverter(ImageView imageView) {
        this.imageView = imageView;
    }


    /**
     * 获得当前的缩放比例
     */
    public float getScale(Matrix matrix) {
        matrix.getValues(matrixValues);
        return matrixValues[Matrix.MSCALE_X];
    }

    /**
     * 如果没有限制图片的范围，图片可能会超出ImageView的边界，这时候就会出现白边
     */
    public void checkMatrixBounds(Matrix matrix) {
        RectF rect = getDisplayRect(matrix);
        if (rect == null) {
            return;
        }
        float deltaX = 0;
        float deltaY = 0;
        int width = imageView.getWidth();
        int height = imageView.getHeight();
        // 如果宽或高大于屏幕，则控制范围
        if (rect.width() >= width) {
            if (rect.left > 0) {
                deltaX = -rect.left;
            }
            if (rect.right < width) {
                deltaX = width - rect.right;
            }
        }
        if (rect.height() >= height) {
            // 如果 rect.top 大于 0，说明图片顶部已经超出ImageView的顶部边界，需要向下移动
            if (rect.top > 0) {
                deltaY = -rect.top;
            }
            // 如果 rect.bottom 小于 height，说明图片底部已经超出ImageView的底部边界，需要向上移动
            if (rect.bottom < height) {
                deltaY = height - rect.bottom;
            }
        }
        // 如果宽或高小于屏幕，则让其居中
        if (rect.width() < width) {
            deltaX = width * 0.5f - rect.right + 0.5f * rect.width();
        }
        if (rect.height() < height) {
            deltaY = height * 0.5f - rect.bottom + 0.5f * rect.height();
        }
        matrix.postTranslate(deltaX, deltaY);
    }

    /**
     * 根据当前图片的Matrix获得图片的范围
     */
    private RectF getDisplayRect(Matrix matrix) {
        Drawable d = imageView.getDrawable();
        if (d != null) {
            displayRect.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            matrix.mapRect(displayRect);
            return displayRect;
        }
        return null;
    }

    public Matrix getScaleTypeMatrix(@PendingScaleType int scaleType) {
        Drawable drawable = imageView.getDrawable();
        Matrix matrix = new Matrix();
        float viewWidth = imageView.getWidth();
        float viewHeight = imageView.getHeight();
        float drawableWidth = drawable.getIntrinsicWidth();
        float drawableHeight = drawable.getIntrinsicHeight();
        float widthScale = viewWidth / drawableWidth;
        float heightScale = viewHeight / drawableHeight;
        // FIT_CENTER
        if (scaleType == PendingScaleType.FIT_CENTER) {
            RectF mTempSrc = new RectF(0, 0, drawableWidth, drawableHeight);
            RectF mTempDst = new RectF(0, 0, viewWidth, viewHeight);
            matrix.setRectToRect(mTempSrc, mTempDst, Matrix.ScaleToFit.CENTER);
        }
        // CENTER_CROP
        else if (scaleType == PendingScaleType.CENTER_CROP) {
            float scale = Math.max(widthScale, heightScale);
            matrix.postScale(scale, scale);
            matrix.postTranslate((viewWidth - drawableWidth * scale) / 2F, (viewHeight - drawableHeight * scale) / 2F);
        }
        return matrix;
    }

}
