package com.mackwu.component.func.anim;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * @author MackWu
 * @since 2022/12/1 17:24
 */
public class TweenAnimUtil {

    private TranslateAnimation translate() {
        // 平移动画
        // fromXDelta、toXDelta 水平方向移动。从fromX到toX
        // fromYDelta、toYDelta 垂直方向移动
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 100, 0, 100);
        // 动画时长
        translateAnimation.setDuration(3000);
        // 动画播放完成后，视图是否停留在动画开始的状态。默认true
        translateAnimation.setFillBefore(true);
        // 动画播放完成后，视图是否停留在动画结束的状态。默认false。设置了FillAfter，则以FillAfter为准。
        translateAnimation.setFillAfter(true);
        // 是否应用FillBefore，对FillAfter无影响。默认true
        translateAnimation.setFillEnabled(true);
        // 动画重复播放次数。
        translateAnimation.setRepeatCount(2);
        // 动画播放模式。restart代表正序重放，reverse代表倒序回放，默认为restart。
        translateAnimation.setRepeatMode(Animation.RESTART);
        // 插值器
        translateAnimation.setInterpolator(new LinearInterpolator());
        return translateAnimation;
    }

    private void scale() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1);
        scaleAnimation.setDuration(800);
        scaleAnimation.setFillAfter(true);
    }

    private void alpha() {
        // 透明度动画
        // fromAlpha动画开始时透明度，toAlpha动画结束时透明度。0完全透明，1不透明
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
    }

    private void animationSet() {
        // animationSet
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(800);
        animationSet.setFillAfter(true);

        // scaleAnimation
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1);
        animationSet.addAnimation(scaleAnimation);

        // alphaAnimation
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        animationSet.addAnimation(alphaAnimation);
    }


}
