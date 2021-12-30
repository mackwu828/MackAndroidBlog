package com.mackwu.component.util;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;

/**
 * ===================================================
 * Created by MackWu on 2020/8/24 15:52
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class AnimUtil {


    /**
     * 获取平移动画
     * translationX：下个位置大于上个上个位置时，向右移动，反之向左移动；
     * translationY：下个位置大于上个上个位置时，向下移动，反之向上移动
     *
     * @param view view
     * @param toX
     * @param toY
     */
    public static ObjectAnimator getTranslationAnimator(View view, float toX, float toY) {
//        PropertyValuesHolder translationX = PropertyValuesHolder.ofFloat("translationX", view.getTranslationX(), toX);
//        PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("translationY", view.getTranslationY(), toY);
//        return ObjectAnimator.ofPropertyValuesHolder(view, translationX, translationY);

        ObjectAnimator.ofFloat(view, "translationX", 0f, -300f, 0f);
        return null;
    }

    /**
     * 获取透明度动画
     *
     * @param view     view
     * @param from     透明度起始值
     * @param to       透明度结束值
     * @param duration 持续时间
     */
    public static ObjectAnimator getAlphaAnimator(View view, float from, float to, long duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", from, to);
        animator.setDuration(duration);
        return animator;
    }

    /**
     * 获取旋转动画
     *
     * @param view     view
     * @param from     角度起始值
     * @param to       角度结束值
     * @param duration 持续时间
     */
    public static ObjectAnimator getRotationAnimator(View view, float from, float to, float pivotX, float pivotY, long duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", from, to);
        animator.setDuration(duration);
        view.setPivotX(pivotX);
        view.setPivotY(pivotY);
        return animator;
    }

    /**
     * 获取缩放动画
     *
     * @param view   view
     * @param toX    向x轴放大倍数
     * @param toY    向y轴放大倍数
     * @param pivotX x轴坐标。默认从View中心点坐标开始，坐标是（view.getWidth()/2, view.getHeight()/2）。
     * @param pivotY y轴坐标
     */
    public static ObjectAnimator getScaleAnimator(View view, float toX, float toY, float pivotX, float pivotY) {
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, toX);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, toY);
        view.setPivotX(pivotX);
        view.setPivotY(pivotY);
        return ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY);
    }

    public static ObjectAnimator getScaleAnimator(View view, float toX, float toY) {
        return getScaleAnimator(view, toX, toY, view.getWidth() / 2f, view.getHeight() / 2f);
    }


}
