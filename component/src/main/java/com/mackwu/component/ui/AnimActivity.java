package com.mackwu.component.ui;

import android.animation.ObjectAnimator;
import android.animation.TimeAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.LogUtil;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.databinding.ActivityAnimBinding;

/**
 * ===================================================
 * Created by MackWu on 2021/11/18 14:52
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class AnimActivity extends BaseActivity<BaseViewModel, ActivityAnimBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        int duration = 300;
        TimeAnimator animator = new TimeAnimator();
        /**
         * 2021-12-19 21:39:36.106 24942-24942/com.mackwu.component D/mack_wu: totalTime==0, deltaTime==0, fraction: 0.0
         * 2021-12-19 21:39:36.109 24942-24942/com.mackwu.component D/mack_wu: totalTime==0, deltaTime==0, fraction: 0.0
         * 2021-12-19 21:39:36.114 24942-24942/com.mackwu.component D/mack_wu: totalTime==8, deltaTime==8, fraction: 0.026666667
         * 2021-12-19 21:39:36.136 24942-24942/com.mackwu.component D/mack_wu: totalTime==27, deltaTime==19, fraction: 0.09
         * 2021-12-19 21:39:36.156 24942-24942/com.mackwu.component D/mack_wu: totalTime==46, deltaTime==19, fraction: 0.15333334
         * 2021-12-19 21:39:36.175 24942-24942/com.mackwu.component D/mack_wu: totalTime==65, deltaTime==19, fraction: 0.21666667
         * 2021-12-19 21:39:36.195 24942-24942/com.mackwu.component D/mack_wu: totalTime==84, deltaTime==19, fraction: 0.28
         * 2021-12-19 21:39:36.213 24942-24942/com.mackwu.component D/mack_wu: totalTime==102, deltaTime==18, fraction: 0.34
         * 2021-12-19 21:39:36.229 24942-24942/com.mackwu.component D/mack_wu: totalTime==121, deltaTime==19, fraction: 0.40333334
         * 2021-12-19 21:39:36.246 24942-24942/com.mackwu.component D/mack_wu: totalTime==140, deltaTime==19, fraction: 0.46666667
         * 2021-12-19 21:39:36.264 24942-24942/com.mackwu.component D/mack_wu: totalTime==159, deltaTime==19, fraction: 0.53
         * 2021-12-19 21:39:36.282 24942-24942/com.mackwu.component D/mack_wu: totalTime==178, deltaTime==19, fraction: 0.5933333
         * 2021-12-19 21:39:36.301 24942-24942/com.mackwu.component D/mack_wu: totalTime==197, deltaTime==19, fraction: 0.6566667
         * 2021-12-19 21:39:36.321 24942-24942/com.mackwu.component D/mack_wu: totalTime==216, deltaTime==19, fraction: 0.72
         * 2021-12-19 21:39:36.340 24942-24942/com.mackwu.component D/mack_wu: totalTime==235, deltaTime==19, fraction: 0.78333336
         * 2021-12-19 21:39:36.358 24942-24942/com.mackwu.component D/mack_wu: totalTime==253, deltaTime==18, fraction: 0.8433333
         * 2021-12-19 21:39:36.377 24942-24942/com.mackwu.component D/mack_wu: totalTime==272, deltaTime==19, fraction: 0.9066667
         * 2021-12-19 21:39:36.396 24942-24942/com.mackwu.component D/mack_wu: totalTime==291, deltaTime==19, fraction: 0.97
         * 2021-12-19 21:39:36.415 24942-24942/com.mackwu.component D/mack_wu: totalTime==310, deltaTime==19, fraction: 1.0
         */
        animator.setTimeListener((animation, totalTime, deltaTime) -> {
            // 时间因子
            float fraction;
            if (totalTime >= duration) {
                animator.end();
                fraction = 1;
            } else {
                fraction = (float) (totalTime / (double) duration);
            }
            LogUtil.d("totalTime==" + totalTime + ", deltaTime==" + deltaTime + ", fraction: " + fraction);
        });

        binding.btnStart.setOnClickListener(v -> {
            animator.start();
        });
    }

    private void initObjectAnimator() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(binding.ivTest, "alpha", 0f, 1f);
        // 设置动画时长
        animator.setDuration(500);
        // 设置延迟时间
        animator.setStartDelay(500);
        // 设置重复播放次数。默认0不重复播放，1为重复播放一次，INFINITE为无限循环。
        animator.setRepeatCount(0);
        // 设置重复播放模式。默认RESTART正序播放、REVERSE倒序回放
        animator.setRepeatMode(ValueAnimator.RESTART);
        // 启动动画
        animator.start();
    }

}
