package com.mackwu.image.rotate;

import android.content.Context;
import android.util.Log;

import com.facebook.spectrum.Configuration;
import com.facebook.spectrum.EncodedImageSink;
import com.facebook.spectrum.EncodedImageSource;
import com.facebook.spectrum.Spectrum;
import com.facebook.spectrum.SpectrumResult;
import com.facebook.spectrum.SpectrumSoLoader;
import com.facebook.spectrum.image.EncodedImageFormat;
import com.facebook.spectrum.image.ImageSize;
import com.facebook.spectrum.logging.SpectrumLogcatLogger;
import com.facebook.spectrum.options.TranscodeOptions;
import com.facebook.spectrum.plugins.SpectrumPluginJpeg;
import com.facebook.spectrum.requirements.EncodeRequirement;
import com.facebook.spectrum.requirements.ResizeRequirement;
import com.facebook.spectrum.requirements.RotateRequirement;
import com.mackwu.image.rotate.util.FileUtil;
import com.mackwu.image.rotate.util.Logger;

import java.io.File;

/**
 * @author MackWu
 * @since 2023/4/19 10:27
 */
public class SpectrumRotator implements Rotator {

    private Context context;
    private Spectrum spectrum;

    @Override
    public void init(Context context) {
        this.context = context;
        SpectrumSoLoader.init(context.getApplicationContext());
        spectrum = Spectrum.make(new SpectrumLogcatLogger(Log.INFO),
                Configuration.makeEmpty(),
                SpectrumPluginJpeg.get());
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void saveRotate(String sourcePath, String savePath, RotateListener rotateListener) {
        Logger.d("saveRotate start...");
        long start = System.currentTimeMillis();
        try {
            File sourceFile = new File(sourcePath);
            if (!sourceFile.exists()) {
                rotateListener.onRotateFailed(RotateError.FILE_NOT_FOUND, "source file not found!");
                return;
            }
            TranscodeOptions transcodeOptions = TranscodeOptions
                    .Builder(new EncodeRequirement(EncodedImageFormat.JPEG, 75))
                    .rotate(new RotateRequirement(90, false, false, true))
//                    .resize(ResizeRequirement.Mode.EXACT_OR_SMALLER, new ImageSize(context.getResources().getDisplayMetrics().widthPixels, context.getResources().getDisplayMetrics().heightPixels))
                    .build();
            SpectrumResult spectrumResult = spectrum.transcode(
                    EncodedImageSource.from(sourceFile),
                    EncodedImageSink.from(savePath),
                    transcodeOptions,
                    "my_callsite_identifier");
            Logger.d("saveRotate...  " + spectrumResult.toString());
            if (!spectrumResult.isSuccessful()) {
                rotateListener.onRotateFailed(RotateError.UNKNOWN, "unknown");
                return;
            }
            // 删除源文件
            sourceFile.delete();
            // 修改旋转文件为源文件名称
            File saveFile = new File(savePath);
            File renameFile = new File(sourcePath.replaceAll(FileUtil.getFileName(sourcePath), ""), "a.jpg");
            saveFile.renameTo(renameFile);
            rotateListener.onRotateSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            rotateListener.onRotateFailed(RotateError.UNKNOWN, e.getMessage());
        }
        Logger.d("saveRotate completed...  cost=" + (System.currentTimeMillis() - start));
    }

}
