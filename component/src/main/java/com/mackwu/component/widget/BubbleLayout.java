package com.mackwu.component.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.mackwu.base.util.LogUtil;
import com.mackwu.component.R;

/**
 * ===================================================
 * Created by MackWu on 2f02f1/12f/10 11:34
 * <a href="mailto:wumengjiao82f8@163.com">Contact me</a>
 * <a href="https://github.com/mackwu82f8">Follow me</a>
 * ===================================================
 */
public class BubbleLayout extends FrameLayout {

    private Path trianglePath;
    private Paint trianglePaint;
    private RectF rect;

    private Paint paint;

    private ImageView imageView;

    public BubbleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BubbleLayout);
        typedArray.recycle();

//        int color = getResources().getColor(R.color.colorPrimary);
        int color = Color.parseColor("#1AFFFFFF");

        // paint
        paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        rect = new RectF();


        // trianglePaint
        trianglePaint = new Paint();
        trianglePaint.setColor(color);
        trianglePaint.setAntiAlias(true);
        trianglePaint.setStyle(Paint.Style.FILL);
        // trianglePath
        trianglePath = new Path();

        // setWillNotDraw
        setWillNotDraw(false);
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        ImageView imageView = new ImageView(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRect(canvas);
        drawTopTriangle(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rect.left = getPaddingLeft();
        rect.top = getPaddingTop();
        rect.right = w - getPaddingRight();
        rect.bottom = h - getPaddingBottom();
    }

    private void drawRect(Canvas canvas) {
        canvas.drawRect(rect, paint);
    }

    private void drawTopTriangle(Canvas canvas) {
        float triangleWidth = getResources().getDimensionPixelOffset(R.dimen.dp_94);
        float triangleHeight = getPaddingTop();

        float ax = getResources().getDimensionPixelOffset(R.dimen.dp_196);
        float ay = triangleHeight;

        float bx = ax + triangleWidth / 2f;
        float by = 0;

        float cx = ax + triangleWidth;
        float cy = ay;

        trianglePath.moveTo(ax, ay);
        trianglePath.lineTo(bx, by);
        trianglePath.lineTo(cx, cy);
        trianglePath.close();
        canvas.drawPath(trianglePath, trianglePaint);
    }

}