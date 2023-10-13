package com.mackwu.component.ui;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.Logger;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityVideoViewBinding;

/**
 * @author MackWu
 * @since 2023/9/20 17:01
 */
public class VideoViewActivity extends BaseActivity<BaseViewModel, ActivityVideoViewBinding> {

//    String path = "content://com.android.externalstorage.documents/tree/1E74-3D9D%3A%E8%A7%86%E9%A2%91%2FFLV/document/1E74-3D9D%3A%E8%A7%86%E9%A2%91%2FFLV%2F480-30.flv";
    String path = "content://com.android.externalstorage.documents/tree/8671-A0A0%3A%E5%BF%AB%E9%80%9F%E9%AA%8C%E8%AF%81-%E6%B5%8B%E8%AF%95%E7%B4%A0%E6%9D%90%2F%E8%A7%86%E9%A2%91%2FMP4/document/8671-A0A0%3A%E5%BF%AB%E9%80%9F%E9%AA%8C%E8%AF%81-%E6%B5%8B%E8%AF%95%E7%B4%A0%E6%9D%90%2F%E8%A7%86%E9%A2%91%2FMP4%2F480-30.mp4";

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
//        binding.videoView.setVideoURI(Uri.parse(path));
        binding.videoView.setVideoPath(path);

        binding.videoView.setOnPreparedListener(mp -> Logger.d("onPrepared..."));
        binding.videoView.setOnCompletionListener(mp -> {
            Logger.d("onCompletion...");
        });
        binding.videoView.setOnErrorListener((mp, what, extra) -> {
            Logger.d("onError...  what=" + what + ", extra=" + extra);
            return false;
        });
        //
        binding.btnPlay.setOnClickListener(v -> {
            Logger.d("start");
            binding.videoView.start();
        });

        binding.videoView.start();
    }

}
