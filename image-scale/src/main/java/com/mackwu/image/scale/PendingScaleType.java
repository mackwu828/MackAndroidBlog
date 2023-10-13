package com.mackwu.image.scale;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author MackWu
 * @since 2023/9/14 16:40
 */
@IntDef({PendingScaleType.FIT_CENTER, PendingScaleType.CENTER_CROP})
@Retention(RetentionPolicy.SOURCE)
public @interface PendingScaleType {
    int FIT_CENTER = 1;
    int CENTER_CROP = 2;
}
