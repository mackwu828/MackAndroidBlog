package com.mackwu.component.func.dpad;

import android.animation.Keyframe;
import android.animation.PropertyValuesHolder;
import android.view.View;

/**
 * @author MackWu
 * @since 2022/12/16 14:33
 */
public class ShakeUtil {

    public static PropertyValuesHolder getShakeVertical() {
        return PropertyValuesHolder.ofKeyframe(View.TRANSLATION_Y,
                Keyframe.ofFloat(0f, 0),
                Keyframe.ofFloat(0.4f, 29),
                Keyframe.ofFloat(0.7f, -18),
                Keyframe.ofFloat(0.9f, 21),
                Keyframe.ofFloat(1f, 0));
    }

    public static PropertyValuesHolder getShakeHorizontalToStart() {
        return PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X,
                Keyframe.ofFloat(0f, 0),
                Keyframe.ofFloat(0.4f, -29),
                Keyframe.ofFloat(0.7f, 25),
                Keyframe.ofFloat(0.9f, -21),
                Keyframe.ofFloat(1f, 0));
    }

    public static PropertyValuesHolder getShakeHorizontalToEnd() {
        return PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X,
                Keyframe.ofFloat(0f, 0),
                Keyframe.ofFloat(0.4f, 29),
                Keyframe.ofFloat(0.7f, -25),
                Keyframe.ofFloat(0.9f, 21),
                Keyframe.ofFloat(1f, 0));
    }

}
