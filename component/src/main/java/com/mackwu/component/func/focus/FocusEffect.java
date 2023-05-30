package com.mackwu.component.func.focus;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

import androidx.annotation.ColorInt;

/**
 * @author MackWu
 * @since 2022/10/31 14:56
 */
public interface FocusEffect {

    /**
     * 获取背景
     */
    Drawable getBackground();

    /**
     * 获取内间距
     */
    int getPadding();

    class Builder {
        StateListDrawable stateListDrawable;

        // default
        int defaultColor;
        float defaultRadius;
        int defaultStrokeWidth;
        int defaultStrokeColor;

        // focused
        float focusedRadius;
        int focusedStrokeWidth;
        int focusedStrokeColor;

        public Builder() {
            this.defaultColor = Color.TRANSPARENT;
        }

        public Builder defaultColor(@ColorInt int color) {
            this.defaultColor = color;
            return this;
        }

        public Builder defaultRadius(float radius) {
            this.defaultRadius = radius;
            return this;
        }

        public Builder defaultStroke(int width, @ColorInt int color) {
            this.defaultStrokeWidth = width;
            this.defaultStrokeColor = color;
            return this;
        }

        public Builder focusedRadius(float radius) {
            this.focusedRadius = radius;
            return this;
        }

        public Builder focusedStroke(int width, @ColorInt int color) {
            this.focusedStrokeWidth = width;
            this.focusedStrokeColor = color;
            return this;
        }

        public FocusEffect build() {
            stateListDrawable = new StateListDrawable();
            // pressedDrawable
//            GradientDrawable pressedDrawable = new GradientDrawable();
//            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);

            // focusedDrawable
            GradientDrawable focusedDrawable = new GradientDrawable();
            focusedDrawable.setCornerRadius(focusedRadius);
            focusedDrawable.setStroke(focusedStrokeWidth, focusedStrokeColor);
            stateListDrawable.addState(new int[]{android.R.attr.state_focused}, focusedDrawable);

            // defaultDrawable
            GradientDrawable defaultDrawable = new GradientDrawable();
            defaultDrawable.setCornerRadius(defaultRadius);
            defaultDrawable.setStroke(defaultStrokeWidth, defaultStrokeColor);
            defaultDrawable.setColor(defaultColor);
            stateListDrawable.addState(new int[]{}, defaultDrawable);
            return new FocusEffect() {

                @Override
                public Drawable getBackground() {
                    return stateListDrawable;
                }

                @Override
                public int getPadding() {
                    return focusedStrokeWidth;
                }
            };
        }
    }

}
