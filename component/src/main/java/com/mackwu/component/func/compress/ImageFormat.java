package com.mackwu.component.func.compress;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author MackWu
 * @since 2022/8/25 19:18
 * 图片格式
 */
@IntDef({ImageFormat.JPEG, ImageFormat.PNG, ImageFormat.GIF, ImageFormat.TIFF, ImageFormat.BMP})
@Retention(RetentionPolicy.SOURCE)
public @interface ImageFormat {
    int JPEG = 0x01;
    int PNG = 0x02;
    int GIF = 0x03;
    int TIFF = 0x04;
    int BMP = 0x05;
}
