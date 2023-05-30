package com.mackwu.image.rotate;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author MackWu
 * @since 2023/4/19 18:44
 */
@IntDef({RotateError.FILE_NOT_FOUND, RotateError.UNKNOWN})
@Retention(RetentionPolicy.SOURCE)
public @interface RotateError {
    // 文件不存在
    int FILE_NOT_FOUND = 1;
    int UNKNOWN = 2;
}
