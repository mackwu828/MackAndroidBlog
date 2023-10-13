package com.mackwu.image.scale;

import android.view.View;

import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.mackwu.image.scale.util.ScaleLogger;

/**
 * @author MackWu
 * @since 2023/9/9 19:20
 */
public class FitCenterDownsample extends DownsampleStrategy {

    private final View view;

    public FitCenterDownsample(View view) {
        this.view = view;
    }

    @Override
    public float getScaleFactor(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
        int width = view.getWidth();
        int height = view.getHeight();
        final float widthPercentage = width / (float) sourceWidth;
        final float heightPercentage = height / (float) sourceHeight;
        float percentage = Math.min(widthPercentage, heightPercentage);
        ScaleLogger.d(String.format("getScaleFactor...  (%s, %s), (%s, %s), %s", width, height, sourceWidth, sourceHeight, percentage));
        return percentage;
    }

    @Override
    public SampleSizeRounding getSampleSizeRounding(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
        return SampleSizeRounding.QUALITY;
    }

}
