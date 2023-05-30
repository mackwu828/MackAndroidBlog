package com.mackwu.image.rotate;

import android.content.Context;

/**
 * @author MackWu
 * @since 2023/4/19 10:26
 */
public interface Rotator {

    void init(Context context);

    void saveRotate(String sourcePath, String savePath, RotateListener rotateListener);
}
