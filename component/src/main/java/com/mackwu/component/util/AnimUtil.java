package com.mackwu.component.util;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.animation.Animation;

/**
 * ===================================================
 * Created by MackWu on 2020/8/24 15:52
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class AnimUtil {

    /**
     * 启动透明度动画
     *
     * @param view     view
     * @param from     透明度起始值
     * @param to       透明度结束值
     * @param duration 持续时间
     */
    public static void starAlpha(View view, float from, float to, long duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", from, to);
        animator.setDuration(duration);
        animator.start();
    }

    public static ObjectAnimator getAlpha(View view, float from, float to, long duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", from, to);
        animator.setDuration(duration);
        return animator;
    }

    /**
     * 启动旋转动画
     *
     * @param view     view
     * @param from     角度起始值
     * @param to       角度结束值
     * @param duration 持续时间
     */
    public static void starRotation(View view, float from, float to, long duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", from, to);
        animator.setDuration(duration);
        animator.start();
    }

    public static ObjectAnimator getRotation(View view, float from, float to, long duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", from, to);
        animator.setDuration(duration);
        return animator;
    }

    public static ObjectAnimator getRotation(View view, float pivotX, float pivotY, float from, float to, long duration) {
        view.setPivotX(pivotX);
        view.setPivotY(pivotY);
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", from, to);
        animator.setDuration(duration);
        return animator;
    }

    public static ObjectAnimator getInfiniteRotation(View view, float from, float to, long duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", from, to);
        animator.setDuration(duration);
        animator.setRepeatCount(Animation.INFINITE);
        return animator;
    }

    /**
     * 获取透明度动画
     *
     * @param view view
     */
    public static void startScale(View view, float x, float y) {
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, x);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, y);
        ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY).start();
    }

    public static void startScale(View view, float xy) {
        startScale(view, xy, xy);
    }

}
