package com.mackwu.component.util;

import android.app.Activity;

import java.util.Stack;

/**
 * ===================================================
 * Created by MackWu on 2020/7/28 16:59
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class ActivityStackUtil {

    private static Stack<Activity> activityStack = new Stack<>();

    /**
     * add activity
     */
    public static void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    /**
     * finish activity
     */
    public static void finishActivity(Activity activity) {
        if (activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * finish activity
     */
    public static void finishActivity(Class<?> cls) {
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (activity.getClass().equals(cls)) {
                activityStack.remove(activity);
                activity.finish();
                break;
            }
        }
    }

    /**
     * finish all activity
     */
    public static void finishAllActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (activity != null) {
                activityStack.remove(activity);
                activity.finish();
            }
        }
    }

    /**
     * finish all activity
     *
     * @param cls 除了该activity
     */
    public static void finishAllActivityExcept(Class<?> cls) {
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (activity != null && !activity.getClass().equals(cls)) {
                activityStack.remove(activity);
                activity.finish();
            }
        }
    }

    /**
     * get activity
     */
    public static Activity getActivity(Class<?> cls) {
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * activity是否存在
     */
    public static boolean isActivityExist(Class<?> cls) {
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

}
