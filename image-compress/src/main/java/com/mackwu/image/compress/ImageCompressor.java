package com.mackwu.image.compress;

/**
 * @author MackWu
 * @since 2023/4/20 11:41
 */
public class ImageCompressor {

    private static ImageCompressor instance;

    private ImageCompressor() {
    }

    public static ImageCompressor getInstance() {
        if (instance == null) {
            instance = new ImageCompressor();
        }
        return instance;
    }


    public void compress() {

    }
}
