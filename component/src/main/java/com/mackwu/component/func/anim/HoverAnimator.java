package com.mackwu.component.func.anim;

import android.animation.TimeAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

/**
 * @author MackWu
 * @since 2021/12/28 11:02
 */
public class HoverAnimator implements TimeAnimator.TimeListener {

    private final String TAG = HoverAnimator.class.getSimpleName();
    private final View view;
    private final TimeAnimator animator = new TimeAnimator();
    private float focusLevel = 0f;
    private float focusLevelStart;
    private float focusLevelDelta;
    private final AnimateConfig animateConfig;

    public HoverAnimator(View view, AnimateConfig animateConfig) {
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
        float scale = 1 + level * (animateConfig.scaleFactor - 1);
        view.setScaleX(scale);
        view.setScaleY(scale);

        // translate
        view.setTranslationX(level * animateConfig.translateX);
        view.setTranslationY(level * animateConfig.translateY);

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

    public void animateHover(int x) {
        animator.end();
//        final float end = hasFocus ? 1 : 0;
//        if (focusLevel != end) {
//            focusLevelStart = focusLevel;
//            focusLevelDelta = end - focusLevelStart;
//            animator.start();
//        }
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
        // 缩放比例。如原比例为1，放大比例为1.1，则scaleFactor=1.1
        private float scaleFactor;
        // 平移X
        private float translateX;
        // 平移Y
        private float translateY;
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
            this.duration = 250;
            this.scaleFactor = 1.2f;
            this.interpolator = new AccelerateDecelerateInterpolator();
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        public void setScaleFactor(float scaleFactor) {
            this.scaleFactor = scaleFactor;
        }

        public void setTranslateX(float translateX) {
            this.translateX = translateX;
        }

        public void setTranslateY(float translateY) {
            this.translateY = translateY;
        }

        public void setInterpolator(Interpolator interpolator) {
            this.interpolator = interpolator;
        }

        public void setLeftPadding(int leftPadding) {
            this.leftPadding = leftPadding;
        }

        public void setTopPadding(int topPadding) {
            this.topPadding = topPadding;
        }

        public void setRightPadding(int rightPadding) {
            this.rightPadding = rightPadding;
        }

        public void setBottomPadding(int bottomPadding) {
            this.bottomPadding = bottomPadding;
        }

        public void setTimeListener(TimeListener timeListener) {
            this.timeListener = timeListener;
        }
    }

    public interface TimeListener {
        void onTimeUpdate(float level);
    }
}