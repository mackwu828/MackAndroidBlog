package com.mackwu.component.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.mackwu.base.util.LogUtil;


/**
 * ===================================================
 * Created by MackWu on 2022/2/22 17:47
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class TestView extends View {

    private Paint blackPaint;
    private Paint redPaint;
    private Path path;

    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        LogUtil.d("  \n" +
                "top=" + getTop() + "\n" +
                "left=" + getLeft()
        );

        // blackPaint
        blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
//        blackPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        blackPaint.setStyle(Paint.Style.STROKE);
        blackPaint.setAntiAlias(true);

        // redPaint
        redPaint = new Paint();
        redPaint.setColor(Color.RED);
        redPaint.setStyle(Paint.Style.STROKE);
        redPaint.setAntiAlias(true);

        //
        path = new Path();
//      path.moveTo(0,0); // 起点默认是(0,0)

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



//        path.lineTo();

        RectF rectF = new RectF(0, 0, 100, 100);
//        path.addArc(rectF, 0, 90);
        path.arcTo(rectF, 0, 90, true);


        canvas.drawRect(rectF, redPaint);
        canvas.drawPath(path, blackPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.d("  \n" +
                "x=" + event.getX() + "\n" +
                "y=" + event.getY() + "\n" +
                "rawX=" + event.getRawX() + "\n" +
                "rawY=" + event.getRawY()
        );
        return super.onTouchEvent(event);
    }


    /**
     * 设置路径。
     * moveTo()、setLastPoint()、lineTo()、close()
     */
    private void pathTest1(Canvas canvas) {

    }

    /**
     * 画三角形
     */
    private void drawTriangle(Canvas canvas){
        path.moveTo(50,0);
        path.lineTo(100, 100);
        path.lineTo(0, 100);
        path.close();
        canvas.drawPath(path, blackPaint);
    }

    /**
     * 在路径中添加基本图形。
     * addRect、addCircle、
     */
    private void pathTest2(Canvas canvas) {
        RectF rectf = new RectF(0, 0, 400, 400);
        // 顺时针
        path.addRect(rectf, Path.Direction.CW);
//        // 逆时针
//        path.addRect(rectf, Path.Direction.CCW);
        canvas.drawPath(path, blackPaint);
    }

    /**
     * 在路径中添加弧形路径。
     * addArc、arcTo
     */
    private void pathTest3() {

    }
}
