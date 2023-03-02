package com.mackwu.component.func.compress;

/**
 * @author MackWu
 * @since 2022/8/19 16:43
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

    public void compress(ImageArgs imageArgs) {

    }


}
