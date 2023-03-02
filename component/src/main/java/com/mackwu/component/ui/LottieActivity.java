package com.mackwu.component.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;

import com.mackwu.base.util.Logger;
import com.mackwu.component.databinding.ActivityLottieBinding;
import com.mackwu.component.ui.viewmodel.MainViewModel;
import com.mackwu.base.BaseActivity;

/**
 * ===================================================
 * Created by MackWu on 2020/9/1 20:19
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class LottieActivity extends BaseActivity<MainViewModel, ActivityLottieBinding> {

    private final Handler handler = new Handler();

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        // lottie动画文件
        binding.lottieAnimationView.setAnimation("error.json");
        // 动画重复播放次数。默认0，不重复播放。
        binding.lottieAnimationView.setRepeatCount(0);
        // 动画监听
        binding.lottieAnimationView.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Logger.d("onAnimationEnd...");
                binding.lottieAnimationView.setVisibility(View.GONE);
            }
        });
        // 动画更新监听
        binding.lottieAnimationView.addAnimatorUpdateListener(animation -> {
            Logger.d("animation...  " + animation.getAnimatedFraction());
        });


        //
        binding.btnStart.setOnClickListener(v -> startAnimation());
        binding.btnStop.setOnClickListener(v -> stopAnimation());
    }

    /**
     * 启动动画
     */
    private void startAnimation() {
        binding.lottieAnimationView.setVisibility(View.VISIBLE);
        binding.lottieAnimationView.playAnimation();
    }

    /**
     * 停止动画
     */
    private void stopAnimation() {
        binding.lottieAnimationView.setVisibility(View.GONE);
        binding.lottieAnimationView.cancelAnimation();
    }

    /**
     * 暂停动画
     */
    private void pauseAnimation() {
        binding.lottieAnimationView.pauseAnimation();
    }
}
