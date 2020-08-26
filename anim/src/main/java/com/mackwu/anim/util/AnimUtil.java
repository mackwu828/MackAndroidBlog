package com.mackwu.anim.util;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;

/**
 * ===================================================
 * Created by MackWu on 2020/8/24 15:52
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class AnimUtil {

    @SuppressLint("ObjectAnimatorBinding")
    public void startScale(float x, float y) {
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, x);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleX", 1f, y);
        ObjectAnimator.ofPropertyValuesHolder(this, scaleX, scaleY).start();
    }

}
