package com.mackwu.component.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityImageCompressBinding;
import com.mackwu.component.ui.viewmodel.ImageCompressViewModel;

/**
 * ===================================================
 * Created by MackWu on 2021/6/24 15:12
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ImageCompressActivity extends BaseActivity<ImageCompressViewModel, ActivityImageCompressBinding> {

    private final String url = "http://mmbiz.qpic.cn/mmbiz/PwIlO51l7wuFyoFwAXfqPNETWCibjNACIt6ydN7vw8LeIwT7IjyG3eeribmK4rhibecvNKiaT2qeJRIWXLuKYPiaqtQ/0";

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        binding.btnFileToBitmap.setOnClickListener(v -> {
            viewModel.fileToBitmap();
        });

    }


}