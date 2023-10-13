package com.mackwu.component.func;

import android.content.Context;
import android.view.MotionEvent;

import com.mackwu.base.util.Logger;
import com.mackwu.component.func.brightness.BrightnessControl;
import com.mackwu.component.func.volume.VolumeControl;

/**
 * @author MackWu
 * @since 2023/10/12 11:08
 */

public class UpDownTouchDispatcher {

    private final int screenHeight;
    private final int screenWidth;
    private float startY;
    private float startX;
    private static final int MIN_DISTANCE = 80;
    private boolean isMultiTouch;

    public UpDownTouchDispatcher(Context context) {
        screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
    }

    public void processTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                startY = event.getY();
                startX = event.getX();
                isMultiTouch = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isMultiTouch) {
                    float endY = event.getY();
                    float endX = event.getX();
                    float distanceY = startY - endY;
                    float distanceX = Math.abs(endX - startX);
                    Logger.d(String.format("processTouchEvent...  %s, %s, %s, %s", startY, endY, distanceY, distanceX));
                    if (Math.abs(distanceY) > MIN_DISTANCE && Math.abs(distanceY) > distanceX) {
                        if (startX < screenWidth / 2f) {
                            adjustBrightness(distanceY);
                        } else {
                            adjustVolume(distanceY);
                        }
                        startY = endY;
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                isMultiTouch = true;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (event.getPointerCount() == 1) {
                    isMultiTouch = false;
                }
                break;
        }
    }

    private void adjustVolume(float distanceY) {
        int max = VolumeControl.getInstance().getMaxVolume();
        int current = VolumeControl.getInstance().getVolume();
        int step = screenHeight / max;
        int delta = (int) (distanceY / step);
        int adjust = current + delta;
        adjust = validate(adjust, max);
//        Logger.d(String.format("adjustVolume...  %s, %s, %s, %s, %s, %s", max, current, step, distanceY, delta, adjust));
        VolumeControl.getInstance().setVolume(adjust);
//        VolumeWindow.getInstance().show(adjust);
    }

    private int validate(int adjust, int max) {
        if (adjust < 0) {
            adjust = 0;
        } else if (adjust > max) {
            adjust = max;
        }
        return adjust;
    }

    private void adjustBrightness(float distanceY) {

    }

}
