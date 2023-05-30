package com.mackwu.component.ui;

import android.os.Bundle;
import android.os.Environment;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityVideoBinding;
import com.mackwu.component.ui.viewmodel.VideoViewModel;
import com.mackwu.storage.util.Logger;
import com.mackwu.video.compress.VideoCompressor;

/**
 * @author MackWu
 * @since 2023/2/6 16:53
 */
public class VideoActivity extends BaseActivity<VideoViewModel, ActivityVideoBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        binding.btnGetVideoData.setOnClickListener(v -> {
            viewModel.getVideoData();
        });
        binding.btnGetVideoFirstFrame.setOnClickListener(v -> {
            viewModel.getVideoFirstFrame();
        });
    }
}
