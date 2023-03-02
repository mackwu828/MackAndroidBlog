package com.mackwu.component.func.anim;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * ===================================================
 * Created by MackWu on 2020/8/24 15:52
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class ObjectAnimUtil {


    /**
     * 获取平移动画
     */
    public static ObjectAnimator getTranslationAnimator(View view, float fromX, float toX) {
        // fromX移动到toX
        fromX = 0;
        toX = 300;
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", fromX, toX);


//        PropertyValuesHolder translationX = PropertyValuesHolder.ofFloat("translationX", view.getTranslationX(), toX);
//        PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("translationY", view.getTranslationY(), toY);
//        return ObjectAnimator.ofPropertyValuesHolder(view, translationX, translationY);

        return animator;
    }


    private ObjectAnimator XXX(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        // 设置动画时长
        animator.setDuration(500);
        // 设置延迟时间
        animator.setStartDelay(500);
        // 设置重复播放次数。默认0不重复播放，1为重复播放一次，INFINITE为无限循环。
        animator.setRepeatCount(0);
        // 设置重复播放模式。默认RESTART正序播放、REVERSE倒序回放
        animator.setRepeatMode(ValueAnimator.RESTART);
        // 启动动画
//        animator.start();
        return animator;
    }


//
//    /**
//     * 获取透明度动画
//     *
//     * @param view     view
//     * @param from     透明度起始值
//     * @param to       透明度结束值
//     * @param duration 持续时间
//     */
//    public static ObjectAnimator getAlphaAnimator(View view, float from, float to, long duration) {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", from, to);
//        animator.setDuration(duration);
//        return animator;
//    }
//
//    /**
//     * 获取旋转动画
//     *
//     * @param view     view
//     * @param from     角度起始值
//     * @param to       角度结束值
//     * @param duration 持续时间
//     */
//    public static ObjectAnimator getRotationAnimator(View view, float from, float to, float pivotX, float pivotY, long duration) {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", from, to);
//        animator.setDuration(duration);
//        view.setPivotX(pivotX);
//        view.setPivotY(pivotY);
//        return animator;
//    }
//
//    /**
//     * 获取缩放动画
//     *
//     * @param view   view
//     * @param toX    向x轴放大倍数
//     * @param toY    向y轴放大倍数
//     * @param pivotX x轴坐标。默认从View中心点坐标开始，坐标是（view.getWidth()/2, view.getHeight()/2）。
//     * @param pivotY y轴坐标
//     */
//    public static ObjectAnimator getScaleAnimator(View view, float toX, float toY, float pivotX, float pivotY) {
//        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, toX);
//        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, toY);
//        view.setPivotX(pivotX);
//        view.setPivotY(pivotY);
//        return ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY);
//    }
//
//    public static ObjectAnimator getScaleAnimator(View view, float toX, float toY) {
//        return getScaleAnimator(view, toX, toY, view.getWidth() / 2f, view.getHeight() / 2f);
//    }


}
