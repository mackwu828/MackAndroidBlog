package com.mackwu.component.func.dpad;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

/**
 * @author MackWu
 * @since 2023/2/28 14:37
 */
public class ShakeAnimator {

    // 震动动画
    private ObjectAnimator shakeAnimator;
    private final Interpolator interpolator;
    private final Animator.AnimatorListener animatorListener;
    private final PropertyValuesHolder shakeVertical;

    public ShakeAnimator() {
        animatorListener = new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                shakeAnimator.removeAllListeners();
                shakeAnimator = null;
            }
        };
        interpolator = new AccelerateDecelerateInterpolator();
        shakeVertical = ShakeUtil.getShakeVertical();
    }

    public void start(View view) {
        if (shakeAnimator != null && shakeAnimator.isRunning()) {
            return;
        }
        shakeAnimator = ObjectAnimator.ofPropertyValuesHolder(view, shakeVertical);
        shakeAnimator.setDuration(450);
        shakeAnimator.setInterpolator(interpolator);
        shakeAnimator.addListener(animatorListener);
        shakeAnimator.start();
    }

    public void cancel() {
        if (shakeAnimator != null) {
            shakeAnimator.cancel();
            shakeAnimator.removeAllListeners();
            shakeAnimator = null;
        }
    }

}
