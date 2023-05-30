package com.mackwu.component.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.mackwu.base.util.Logger;

/**
 * @author MackWu
 * @since 2023/3/30 16:55
 */
public class RotateImageView extends AppCompatImageView {

    public RotateImageView(Context context) {
        this(context, null);
    }

    public RotateImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotateImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable drawable = getDrawable();
//        if (drawable != null) {
//            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
//            Matrix matrix = new Matrix();
//            matrix.setRotate(45, getWidth() / 2f, getHeight() / 2f);
//            canvas.drawBitmap(bitmap, matrix, null);
//        }
    }

}
