package com.mackwu.component.ui.view.focus;

import android.animation.TimeAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

/**
 * ===================================================
 * Created by MackWu on 2021/12/28 11:02
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class FocusAnimator implements TimeAnimator.TimeListener {

    private final View view;
    private final TimeAnimator animator = new TimeAnimator();
    private float focusLevel = 0f;
    private float focusLevelStart;
    private float focusLevelDelta;
    private final AnimateConfig animateConfig;

    public FocusAnimator(View view, AnimateConfig animateConfig) {
        this.view = view;
        this.animateConfig = animateConfig;
        animator.setTimeListener(this);
    }

    @Override
    public void onTimeUpdate(TimeAnimator animation, long totalTime, long deltaTime) {
        // fraction
        float fraction;
        if (totalTime >= animateConfig.duration) {
            fraction = 1;
            animator.end();
        } else {
            fraction = (float) (totalTime / (double) animateConfig.duration);
        }
        fraction = animateConfig.interpolator.getInterpolation(fraction);

        // level
        float level = focusLevelStart + fraction * focusLevelDelta;
        focusLevel = level;

        // scale
        float scale = 1f + level * animateConfig.scaleFactor;
        view.setScaleX(scale);
        view.setScaleY(scale);

        // padding
        view.setPadding((int) (animateConfig.leftPadding * level),
                (int) (animateConfig.topPadding * level),
                (int) (animateConfig.rightPadding * level),
                (int) (animateConfig.bottomPadding * level));


        // timeListener
        if (animateConfig.timeListener != null) {
            animateConfig.timeListener.onTimeUpdate(level);
        }
    }

    public void animateFocus(boolean hasFocus) {
        animator.end();
        final float end = hasFocus ? 1 : 0;
        if (focusLevel != end) {
            focusLevelStart = focusLevel;
            focusLevelDelta = end - focusLevelStart;
            animator.start();
        }
    }

    private String getHashCode(View view) {
        return Integer.toHexString(System.identityHashCode(view));
    }

    /**
     * 动画配置
     */
    public static class AnimateConfig {
        // 动画时长
        private long duration;
        // 缩放比例。如原比例为1，放大比例为1.1，则scaleFactor=1.1-1=0.1
        private float scaleFactor;
        // 动画插值器
        private Interpolator interpolator;
        /*
         * 上下左右间距
         */
        private int leftPadding;
        private int topPadding;
        private int rightPadding;
        private int bottomPadding;
        //
        private TimeListener timeListener;

        public AnimateConfig() {
            this.duration = 150;
            this.interpolator = new AccelerateDecelerateInterpolator();
        }

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        public float getScaleFactor() {
            return scaleFactor;
        }

        public void setScaleFactor(float scaleFactor) {
            this.scaleFactor = scaleFactor;
        }

        public Interpolator getInterpolator() {
            return interpolator;
        }

        public void setInterpolator(Interpolator interpolator) {
            this.interpolator = interpolator;
        }

        public int getLeftPadding() {
            return leftPadding;
        }

        public void setLeftPadding(int leftPadding) {
            this.leftPadding = leftPadding;
        }

        public int getTopPadding() {
            return topPadding;
        }

        public void setTopPadding(int topPadding) {
            this.topPadding = topPadding;
        }

        public int getRightPadding() {
            return rightPadding;
        }

        public void setRightPadding(int rightPadding) {
            this.rightPadding = rightPadding;
        }

        public int getBottomPadding() {
            return bottomPadding;
        }

        public void setBottomPadding(int bottomPadding) {
            this.bottomPadding = bottomPadding;
        }

        public TimeListener getTimeListener() {
            return timeListener;
        }

        public void setTimeListener(TimeListener timeListener) {
            this.timeListener = timeListener;
        }
    }

    public interface TimeListener {
        void onTimeUpdate(float level);
    }
}