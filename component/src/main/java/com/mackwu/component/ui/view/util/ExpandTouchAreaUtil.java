package com.mackwu.component.ui.view.util;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author MackWu
 * @since 2022/12/16 15:25
 */
public class ExpandTouchAreaUtil {

    /**
     * 多个View扩大点击区域
     *
     * @param size   扩大距离
     * @param parent 父控件。父控件需要有足够大的区域，如果写死了宽高，则只有该宽高的点击区域。
     * @param views  views
     */
    public static void expandTouchArea(int size, ViewGroup parent, View... views) {
        expandTouchArea(new Rect(size, size, size, size), parent, views);
    }

    /**
     * 多个View扩大点击区域。会出现多个选中态，待定位
     *
     * @param rect   扩大区域
     * @param parent 父控件。父控件需要有足够大的区域，如果写死了宽高，则只有该宽高的点击区域。
     * @param views  views
     */
    public static void expandTouchArea(Rect rect, ViewGroup parent, View... views) {
        parent.post(() -> {
            TouchDelegateComposite touchDelegateComposite = new TouchDelegateComposite(parent);
            for (View view : views) {
                Rect bound = new Rect();
                view.getHitRect(bound);
                bound.left -= rect.left;
                bound.top -= rect.top;
                bound.right += rect.right;
                bound.bottom += rect.bottom;
                TouchDelegate touchDelegate = new TouchDelegate(bound, view);
                touchDelegateComposite.addDelegate(touchDelegate);
            }
            parent.setTouchDelegate(touchDelegateComposite);
        });
    }

    /**
     * 单个View扩大点击区域
     *
     * @param rect   扩大区域
     * @param parent 父view
     * @param view   view
     */
    public static void expandTouchArea(Rect rect, ViewGroup parent, View view) {
        parent.post(() -> {
            Rect bounds = new Rect();
            view.getHitRect(bounds);
            bounds.left -= rect.left;
            bounds.top -= rect.top;
            bounds.right += rect.right;
            bounds.bottom += rect.bottom;
            TouchDelegate touchDelegate = new TouchDelegate(bounds, view);
            parent.setTouchDelegate(touchDelegate);
        });
    }

}
