package com.mackwu.component.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityExoplayerBinding;

/**
 * @author MackWu
 * @since 2022/6/8 15:45
 */
public class ExoPlayerActivity extends BaseActivity<BaseViewModel, ActivityExoplayerBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        SimpleExoPlayer simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this);
    }
}
