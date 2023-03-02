package com.mackwu.component.ui.view.puzzle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.mackwu.base.util.Logger;

import java.util.List;

/**
 * @author MackWu
 * @since 2022/11/14 15:17
 */
public class PuzzleView extends View {

//    private Paint linePaint;

    Paint paint;


    List<PuzzlePiece> puzzlePieces;

    public PuzzleView(Context context) {
        this(context, null);
    }

    public PuzzleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PuzzleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
//        linePaint = new Paint();
//        linePaint.setAntiAlias(true);
//        linePaint.setColor(Color.WHITE);
//        linePaint.setStrokeWidth(4);
//        linePaint.setStyle(Paint.Style.STROKE);
//        linePaint.setStrokeJoin(Paint.Join.ROUND);
//        linePaint.setStrokeCap(Paint.Cap.SQUARE);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
    }

    int width;
    int height;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Logger.d("w=" + w + ", h=" + h);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < puzzlePieces.size(); i++) {
            PuzzlePiece puzzlePiece = puzzlePieces.get(i);
            if (i == 0) {
//                canvas.drawBitmap(puzzlePiece.getBitmap(), null, new Rect( 0, 0, width, height), paint);
                canvas.drawBitmap(puzzlePiece.getBitmap(), generateMatrix(canvas, puzzlePiece), paint);
            } else {
//                canvas.drawBitmap(puzzlePiece.getBitmap(), puzzlePiece.getSrcRect(), new Rect( width/ 2, 0, width, height), paint);
            }
        }
    }

    public void setPuzzlePieces(List<PuzzlePiece> puzzlePieces) {
        this.puzzlePieces = puzzlePieces;
    }

    private Matrix generateMatrix(Canvas canvas, PuzzlePiece puzzlePiece) {
        Matrix matrix = new Matrix();
        Bitmap bitmap = puzzlePiece.getBitmap();
        int imageWidth = bitmap.getWidth();
        int imageHeight = bitmap.getHeight();
        int viewWidth = width;
        int viewHeight = height;
        Logger.d("imageWidth=" + imageWidth + ", imageHeight=" + imageHeight
                + ", viewWidth=" + viewWidth + ", viewHeight=" + viewHeight
        );

        Rect rect = new Rect(viewWidth / 2, 0, viewWidth, viewHeight / 2);
        canvas.drawRect(rect, paint);

        rect.centerX();


        float scale;
        float dx = 0, dy = 0;
        dx = rect.width();
        Logger.d("dx=" + dx + ", dy=" + dy);
        matrix.postTranslate(dx, dy);

//        scale = (float) viewWidth / (float) imageWidth;
//        matrix.postScale(scale, scale, rect.centerX(), rect.centerY());

        return matrix;
    }

    private Matrix generateCenterCropMatrix(PuzzlePiece puzzlePiece) {
        Matrix matrix = new Matrix();
        Bitmap bitmap = puzzlePiece.getBitmap();
        int imageWidth = bitmap.getWidth();
        int imageHeight = bitmap.getHeight();
        int viewWidth = width;
        int viewHeight = height;
        Logger.d("imageWidth=" + imageWidth + ", imageHeight=" + imageHeight
                + ", viewWidth=" + viewWidth + ", viewHeight=" + viewHeight
        );
        float scale;
        float dx = 0, dy = 0;
        /*
         * 图片的宽高比大于控件的宽高比，则可以保证高度可以完全显示。否则可以保证宽度可以完全显示
         * imageWidth/imageHeight > viewWidth/imageHeight
         * 1440/810
         * 1920/338
         */
        if (imageWidth * viewHeight > viewWidth * imageHeight) {
            scale = (float) viewHeight / (float) imageHeight;
            dx = (viewWidth - imageWidth * scale) * 0.5f;
        } else {
            scale = (float) viewWidth / (float) imageWidth;
            dy = (viewHeight - imageHeight * scale) * 0.5f;
        }
        Logger.d("scale=" + scale + ", dx=" + dx + ", dy=" + dy);
        matrix.postScale(scale, scale);
        matrix.postTranslate(Math.round(dx), Math.round(dy));
        return matrix;
    }

}
