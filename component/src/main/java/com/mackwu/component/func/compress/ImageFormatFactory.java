package com.mackwu.component.func.compress;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MackWu
 * @since 2022/8/25 19:26
 */
public class ImageFormatFactory {

    static Map<String, Integer> imageFormatMap = new HashMap<>();

    static {
        imageFormatMap.put("FFD8FF", ImageFormat.JPEG);
        imageFormatMap.put("89504E47", ImageFormat.JPEG);
        imageFormatMap.put("47494638", ImageFormat.GIF);
        imageFormatMap.put("49492A00", ImageFormat.TIFF);
        imageFormatMap.put("424D", ImageFormat.BMP);
    }

    @ImageFormat
    public static int getImageFormat(String magicNumber) {
        //noinspection ConstantConditions
        return imageFormatMap.get(magicNumber);
    }

}
