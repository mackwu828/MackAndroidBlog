package com.mackwu.component.ui;

import android.os.Bundle;
import android.os.Environment;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityVideoBinding;
import com.mackwu.storage.util.Logger;
import com.mackwu.storage.util.StorageUtil;
import com.mackwu.video_compress.VideoCompressor;

/**
 * @author MackWu
 * @since 2023/2/6 16:53
 */
public class VideoActivity extends BaseActivity<BaseViewModel, ActivityVideoBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        binding.btnTest.setOnClickListener(v -> {
            String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getPath();
            String inPath = filePath + "video.mp4";
            String outPath = filePath + "xxx.mp4";
            Logger.d("filePath=" + filePath);
            VideoCompressor videoCompressor = new VideoCompressor.Builder()
                    .inPath(inPath)
                    .outPath(outPath)
                    .build();
            videoCompressor.startCompress(new VideoCompressor.CompressListener() {
                @Override
                public void onCompressProgressChanged(int progress) {

                }

                @Override
                public void onCompressComplete() {

                }

                @Override
                public void onCompressFail() {

                }
            });
        });
    }
}
