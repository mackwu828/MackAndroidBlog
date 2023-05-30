package com.mackwu.component.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * @author MackWu
 * @since 2023/4/3 15:05
 */
public class RoundImageVIew extends AppCompatImageView {

    private Paint paint;
    private int viewWidth;
    private int viewHeight;

    public RoundImageVIew(Context context) {
        this(context, null);
    }

    public RoundImageVIew(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageVIew(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xff424242);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap roundBitmap = getRoundBitmap(bitmap);
            canvas.drawBitmap(roundBitmap, 0, 0, null);
        } else {
            super.onDraw(canvas);
        }
    }

    private Bitmap getRoundBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawARGB(0, 0, 0, 0);
        // 绘制圆角矩形
        RectF rectF = new RectF(0, 0, viewWidth, viewHeight);
        float round = 12f;
        canvas.drawRoundRect(rectF, round, round, paint);
        // 设置SRC_IN
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // 绘制图片
        // 取图片的中心
        Rect src = new Rect(bitmap.getWidth() / 2 - viewWidth, Math.max(0, bitmap.getHeight() / 2 - viewHeight),
                bitmap.getWidth() / 2 + viewWidth, Math.min(bitmap.getHeight() / 2 + viewHeight, viewHeight));
        Rect dst = new Rect(0, 0, viewWidth, viewHeight);
        canvas.drawBitmap(bitmap, src, dst, paint);
        paint.setXfermode(null);
        return output;
    }

}
