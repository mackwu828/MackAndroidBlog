package com.mackwu.component.ui;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityAnimBinding;

/**
 * @author MackWu
 * @since 2023/6/28 16:32
 */
public class AnimActivity extends BaseActivity<BaseViewModel, ActivityAnimBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {


        binding.btnStart.setOnClickListener(v -> {
        });
    }

    private void a(View view) {
        // 水平移动。从0移动到100。
        ObjectAnimator.ofFloat(view, "translationX", 0, 100);
        // 垂直移动。从0移动到100。
        ObjectAnimator.ofFloat(view, "translationY", 0, 100);

        PropertyValuesHolder translationX = PropertyValuesHolder.ofFloat("translationX", 0, 100);
        PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("translationY", 0, 100);
        ObjectAnimator.ofPropertyValuesHolder(view, translationX, translationY);
    }

    public void b(View view) {
        // ViewPropertyAnimator
    }

    private void aaa(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", 0, 100);
        // 设置动画时长。默认300毫秒。
        animator.setDuration(500);
        // 设置延迟启动时间
        animator.setStartDelay(500);
        // 设置重复播放次数。默认0不重复播放，1为重复播放一次，INFINITE=-1为无限循环。
        animator.setRepeatCount(0);
        // 设置重复播放模式。默认RESTART正序播放、REVERSE倒序回放
        animator.setRepeatMode(ValueAnimator.RESTART);
        // 开始动画
        animator.start();
        // 还原动画
//        animator.reverse();
    }


}
