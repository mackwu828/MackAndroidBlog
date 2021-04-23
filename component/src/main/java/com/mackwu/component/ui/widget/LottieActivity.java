package com.mackwu.component.ui.widget;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieDrawable;
import com.mackwu.component.databinding.LottieActivityBinding;
import com.mackwu.component.ui.viewmodel.MainViewModel;
import com.mackwu.base.BaseActivity;

/**
 * ===================================================
 * Created by MackWu on 2020/9/1 20:19
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class LottieActivity extends BaseActivity<MainViewModel, LottieActivityBinding> {

    private final Handler handler = new Handler();

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        //        binding.lottieAnimationView.addAnimatorUpdateListener(animation -> {
//            LogUtil.d("addAnimatorUpdateListener...  getAnimatedFraction: " + animation.getAnimatedFraction());
//        });

        binding.btnStart.setOnClickListener(v -> {
            startAnimation("error.json");
        });
        binding.btnStop.setOnClickListener(v -> {
            startAnimation("error.json");
        });
    }

    /**
     * 启动动画
     */
    private void startAnimation(final String assetName) {
        binding.lottieAnimationView.setAnimation(assetName);
        binding.lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);
//        binding.lottieAnimationView.setRepeatCount(0);
        binding.lottieAnimationView.playAnimation();
    }

    /**
     * 停止动画
     */
    private void stopAnimation() {
        binding.lottieAnimationView.cancelAnimation();
    }

    /**
     * 暂停动画
     */
    private void pauseAnimation() {
        binding.lottieAnimationView.pauseAnimation();
    }
}
