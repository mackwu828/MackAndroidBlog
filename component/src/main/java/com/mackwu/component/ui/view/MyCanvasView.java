package com.mackwu.component.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.mackwu.component.R;


/**
 * ===================================================
 * Created by MackWu on 2022/2/22 17:47
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class MyCanvasView extends View {

    private Paint paint;
    private Path path;
    private Bitmap bitmap;

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
        // 画笔
        paint = new Paint();
        // 设置抗锯齿
        paint.setAntiAlias(true);
        // 设置画笔颜色
        paint.setColor(Color.BLACK);
        // 设置画笔粗细
        paint.setStrokeWidth(5);
        // 设置画面样式。Paint.Style.FILL只填充不描边、Paint.Style.STROKE只描边不填充、Paint.Style.FILL_AND_STROKE填充+描边
        paint.setStyle(Paint.Style.STROKE);


        // 设置画笔的argb值
//        paint.setARGB();
        // 设置着色器
//        paint.setShader()
        // 设置画笔透明度
//        paint.setAlpha();

//        // 设置连线处的样式。
//        // Paint.Join.MITER：直角
//        // Paint.Join.ROUND：圆弧
//        // Paint.Join.BEVEL：在直角样式上切了一个三角形。
//        paint.setStrokeJoin(Paint.Join.MITER);
//        // 设置线冒样式。
//        // Paint.Cap.BUTT：无
//        // Paint.Cap.SQUARE：方形
//        // Paint.Cap.ROUND：半圆形
//        // SQUARE和ROUND会在画笔头尾添加setStrokeWidth设置的宽度。
//        paint.setStrokeCap(Paint.Cap.SQUARE);


        // 设置字体大小
//        paint.setTextSize(20);
        // 设置字体对齐方式
//        paint.setTextAlign();
        // 设置文本的下划线
//        paint.setUnderlineText();
        // 设置文本的删除线
//        paint.setStrikeThruText(true);
        // 设置文本粗体
//        paint.setFakeBoldText(true);
        // 设置文本斜体
//        paint.setTextSkewX(-0.5f);
        // 设置文本阴影
//        paint.setShadowLayer(5, 5, 5, Color.YELLOW);

//        blackPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));


        //
        path = new Path();
//      path.moveTo(0,0); // 起点默认是(0,0)


        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_home);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // left, top距离图片左上角的距离。
//        canvas.drawBitmap(bitmap, 200, 300, paint);

        // src指定需要绘制图片的区域。null表示原图
        Rect src = new Rect(0, 0, bitmap.getWidth() / 2, bitmap.getHeight());
        // dst指定在屏幕上显示的区域。
        Rect dst = new Rect(100, 100, 250, 250);
        canvas.drawBitmap(bitmap, src, dst, null);
    }


    /**
     * 画三角形
     */
    private void drawTriangle(Canvas canvas) {
        path.moveTo(50, 0);
        path.lineTo(100, 100);
        path.lineTo(0, 100);
        path.close();
        canvas.drawPath(path, paint);
    }

    /**
     * 设置路径。
     * moveTo()、setLastPoint()、lineTo()、close()
     */
    private void pathTest(Canvas canvas) {

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
        canvas.drawPath(path, paint);
    }

    /**
     * 在路径中添加弧形路径。
     * addArc、arcTo
     */
    private void pathTest3() {

    }
}
