package com.mackwu.component.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author MackWu
 * @since 2023/3/27 11:49
 */
public class MyCanvasView extends View {

    Paint paint;
    Path path;

    public MyCanvasView(Context context) {
        this(context, null);
    }

    public MyCanvasView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCanvasView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }

}
