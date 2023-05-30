package com.mackwu.component.ui;

import android.os.Bundle;
import android.os.Environment;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityImageRotateBinding;
import com.mackwu.image.rotate.ImageRotator;
import com.mackwu.image.rotate.RotateListener;

import java.io.File;

/**
 * @author MackWu
 * @since 2023/4/18 19:00
 */
public class ImageRotateActivity extends BaseActivity<BaseViewModel, ActivityImageRotateBinding> {

    int degrees = 0;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        ImageRotator.getInstance().init(this);

        binding.btnRotate.setOnClickListener(v -> {

        });
        binding.btnSaveRotate.setOnClickListener(v -> {
            File directoryDownload = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File sourceFile = new File(directoryDownload, "a.jpg");
            File saveFile = new File(directoryDownload, "b.jpg");
            ImageRotator.getInstance().saveRotate(sourceFile.getPath(), saveFile.getPath(), new RotateListener() {
                @Override
                public void onRotateSuccess() {

                }

                @Override
                public void onRotateFailed(int error, String message) {

                }
            });
        });
    }

}
