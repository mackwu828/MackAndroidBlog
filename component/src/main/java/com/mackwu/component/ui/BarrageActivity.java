package com.mackwu.component.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityBarrageBinding;
import com.mackwu.component.ui.view.barrage.Barrage;

/**
 * @author MackWu
 * @since 2022/8/30 10:52
 */
public class BarrageActivity extends BaseActivity<BaseViewModel, ActivityBarrageBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        binding.btnTest.setOnClickListener(v -> {
            binding.barrageView.showBarrage(new Barrage("你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊"));
        });

        binding.barrageView.setOnClickListener(v -> {
            binding.btnTest.setVisibility(View.VISIBLE);
        });
    }
}
