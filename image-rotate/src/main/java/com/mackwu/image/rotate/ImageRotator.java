package com.mackwu.image.rotate;

import android.content.Context;
import android.util.Log;

import com.facebook.spectrum.Configuration;
import com.facebook.spectrum.EncodedImageSink;
import com.facebook.spectrum.EncodedImageSource;
import com.facebook.spectrum.Spectrum;
import com.facebook.spectrum.SpectrumException;
import com.facebook.spectrum.SpectrumSoLoader;
import com.facebook.spectrum.image.EncodedImageFormat;
import com.facebook.spectrum.logging.SpectrumLogcatLogger;
import com.facebook.spectrum.options.TranscodeOptions;
import com.facebook.spectrum.plugins.SpectrumPluginJpeg;
import com.facebook.spectrum.requirements.EncodeRequirement;
import com.facebook.spectrum.requirements.RotateRequirement;
import com.mackwu.image.rotate.util.Logger;

import java.io.FileNotFoundException;

/**
 * @author MackWu
 * @since 2023/4/18 11:01
 */
public class ImageRotator {

    private static ImageRotator instance;
    private Rotator rotator;

    private ImageRotator() {
    }

    public static ImageRotator getInstance() {
        if (instance == null) {
            instance = new ImageRotator();
        }
        return instance;
    }

    public void init(Context context) {
        rotator = new SpectrumRotator();
        rotator.init(context);
    }

    public void rotate(String sourcePath) {

    }

    public void saveRotate(String sourcePath, String savePath, RotateListener rotateListener) {
        if (rotator != null) {
            rotator.saveRotate(sourcePath, savePath, rotateListener);
        }
    }

}
