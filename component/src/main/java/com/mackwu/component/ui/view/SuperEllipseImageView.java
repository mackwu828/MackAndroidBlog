package com.mackwu.component.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * @author MackWu
 * @since 2023/4/3 16:43
 * 超椭圆
 */
public class SuperEllipseImageView extends AppCompatImageView {

    private Paint paint;
    private Path path;
    private int viewWidth;
    private int viewHeight;
    // 半径
    float r;
    // n<2的超椭圆称为次椭圆
    // n>2的超椭圆称为过椭圆
    // n=4的超椭圆称为方圆形
    float n = 3.5f;

    public SuperEllipseImageView(Context context) {
        this(context, null);
    }

    public SuperEllipseImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperEllipseImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        paint = new Paint();
        paint.setAntiAlias(true);
        // path
        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
        r = viewWidth / 2f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap superEllipseBitmap = getSuperEllipseBitmap(bitmap);
            canvas.drawBitmap(superEllipseBitmap, 0, 0, null);
        } else {
            super.onDraw(canvas);
        }
    }

    private Bitmap getSuperEllipseBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawARGB(0, 0, 0, 0);
        // 绘制超椭圆曲线
        drawSuperEllipsePath();
        canvas.drawPath(path, paint);
        // 设置SRC_IN
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // 绘制图片
        canvas.drawBitmap(bitmap, 0, 0, paint);
        paint.setXfermode(null);
        return output;
    }

    /**
     * 绘制超椭圆曲线。
     * 拉梅曲线公式：|x/a|^n+|y/b|^n=1
     */
    private void drawSuperEllipsePath() {
        // 以View中心为原点
        // 绘制下方的曲线
        for (float x = -r; x <= r; x++) {
            float y = -(float) (Math.pow(Math.abs((1 - Math.pow(Math.abs(x / r), n))), 1 / n) * r);
            if (x == -r) {
                path.moveTo(0, r);
            } else {
                path.lineTo(x + r, y * -1 + r);
            }
        }
        // 绘制上方的曲线
        for (float x = r; x >= -r; x--) {
            float y = (float) (Math.pow(Math.abs((1 - Math.pow(Math.abs(x / r), n))), 1 / n) * r);
            path.lineTo(x + r, y * -1 + r);
        }
        path.close();
    }

}
