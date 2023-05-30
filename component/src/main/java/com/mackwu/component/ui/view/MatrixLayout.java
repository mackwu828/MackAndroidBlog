package com.mackwu.component.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author MackWu
 * @since 2023/3/30 16:28
 */
public class MatrixLayout extends LinearLayout {

    float degrees;
    float sx, sy;
    float delta;

    public MatrixLayout(Context context) {
        this(context, null);
    }

    public MatrixLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatrixLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();

        //
        Matrix matrix = new Matrix();
        //
        View child = getChildAt(0);
        int width = child.getWidth();
        int height = child.getHeight();
        // 旋转
        matrix.postRotate(degrees, width / 2f, height / 2f);
        // 缩放
        matrix.postScale(sx, sy, width / 2f, height / 2f);
        // 平移纵坐标，使子View对齐
        // 乘以一个比例delta，让对齐的水平角度更平滑
        matrix.postTranslate(0, (height - sy * height) * delta);
        canvas.concat(matrix);

        super.dispatchDraw(canvas);
        canvas.restore();
    }

    public void setDegrees(float degrees) {
        this.degrees = degrees;
    }

    public void setSx(float sx) {
        this.sx = sx;
    }

    public void setSy(float sy) {
        this.sy = sy;
        delta = sy - 0.1f;
    }
}
