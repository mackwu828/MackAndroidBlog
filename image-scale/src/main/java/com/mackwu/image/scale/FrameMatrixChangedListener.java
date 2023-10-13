package com.mackwu.image.scale;

import android.graphics.Matrix;

/**
 * @author MackWu
 * @since 2023/9/14 18:18
 */
public interface FrameMatrixChangedListener {

    void onMatrixChangeCompleted(Matrix matrix);
}
