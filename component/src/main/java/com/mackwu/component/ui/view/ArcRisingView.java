package com.mackwu.component.ui.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.mackwu.component.R;

/**
 * @author MackWu
 * @since 2023/4/17 15:59
 */
public class ArcRisingView extends View {

    private Paint paint;
    private Paint bgPaint;
    private Path path;
    private final RectF rect = new RectF();
    private int viewWidth;
    private int viewHeight;
    // 弧形矩形高度
    private int arcRectHeight;
    // 弧形区域高度
    private int arcAreaHeight;
    private ValueAnimator animator;
    private float factor;

    public ArcRisingView(Context context) {
        this(context, null);
    }

    public ArcRisingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcRisingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        // bgPaint
        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setColor(Color.parseColor("#595861"));

        // paint
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#E2E0DE"));

        // path
        path = new Path();

        // 弧形矩形高度
        arcRectHeight = getResources().getDimensionPixelSize(R.dimen.dp_53);
        // 弧形区域高度
        arcAreaHeight = getResources().getDimensionPixelSize(R.dimen.dp_231);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 背景弧形区域
        path.reset();
        if (factor == 1f) {
            path.reset();
            rect.set(0, 0, viewWidth, viewHeight);
            canvas.drawRect(rect, bgPaint);
        } else {
            // 起始点y趋于0
            float y = (viewHeight - arcAreaHeight + arcRectHeight) * (1f - factor);
            path.moveTo(0, y);
            // 贝塞尔曲线控制点慢慢趋于0
            float controlY = (viewHeight - arcAreaHeight) * (1f - factor);
            path.quadTo(viewWidth / 2f, controlY, viewWidth, y);
            path.lineTo(viewWidth, viewHeight);
            path.lineTo(0, viewHeight);
            path.close();
            canvas.drawPath(path, bgPaint);
        }

        // 底部弧形区域
        path.reset();
        float y = viewHeight - arcAreaHeight + arcRectHeight;
        path.moveTo(0, y);
        path.quadTo(viewWidth / 2f, viewHeight - arcAreaHeight, viewWidth, y);
        path.lineTo(viewWidth, viewHeight);
        path.lineTo(0, viewHeight);
        path.close();
        canvas.drawPath(path, paint);
    }

    /**
     * 上升
     */
    public void rise() {
        animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(animation -> {
            factor = (float) animation.getAnimatedValue();
            invalidate();
        });
        animator.setDuration(250);
        animator.start();
    }

    /**
     * 下降
     */
    public void drop() {
        animator = ValueAnimator.ofFloat(1f, 0f);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(animation -> {
            factor = (float) animation.getAnimatedValue();
            invalidate();
        });
        animator.setDuration(250);
        animator.start();
    }

}
