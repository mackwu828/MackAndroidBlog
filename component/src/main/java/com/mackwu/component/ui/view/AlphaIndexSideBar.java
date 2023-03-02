package com.mackwu.component.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author MackWu
 * @since 2023/1/3 17:41
 * 字母索引侧边栏
 * https://www.jianshu.com/p/94c682782f01
 */
public class AlphaIndexSideBar extends View {

    String[] alphaList = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};

    Paint paint;
    // 选中的索引
    int selectIndex;

    public AlphaIndexSideBar(Context context) {
        this(context, null);
    }

    public AlphaIndexSideBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AlphaIndexSideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        // paint
        paint = new Paint();
        paint.setColor(Color.BLACK);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paintText(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    private void paintText(Canvas canvas) {
        // 计算每个字母的高度
        int height = getHeight() / alphaList.length;
        for (int i = 0; i < alphaList.length; i++) {
            if (i == selectIndex) {

            }else {

            }
            // 计算每个字母x轴
            float x = getWidth() / 2F - paint.measureText(alphaList[i]) / 2;
            // 计算每个字母Y轴
            int y = height * i + height;
            // 绘制字母
            canvas.drawText(alphaList[i], x, y, paint);
            paint.reset();
        }
    }
}
