package com.mackwu.component.ui.view.puzzle;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * @author MackWu
 * @since 2022/11/14 16:21
 */
public class PuzzlePiece {

    private int resId;
    private Bitmap bitmap;
    private Rect destRect;

    public PuzzlePiece(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Rect getDestRect() {
        return destRect;
    }

    public void setDestRect(Rect destRect) {
        this.destRect = destRect;
    }
}


