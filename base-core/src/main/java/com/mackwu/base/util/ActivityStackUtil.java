package com.mackwu.base.util;

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

    private static final Stack<Activity> activityStack = new Stack<>();

    /**
     * add activity
     */
    public static void addActivity(Activity activity) {
        if (!activityStack.contains(activity)) {
            activityStack.add(activity);
        }
    }

    /**
     * remove activity
     */
    public static void removeActivity(Activity activity) {
        activityStack.remove(activity);
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
            if (activity != null && activity.getClass().equals(cls)) {
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
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * finish all activity except cls
     */
    public static void finishAllActivityExcept(Class<?> cls) {
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (activity != null && !activity.getClass().equals(cls)) {
                activity.finish();
                activityStack.remove(activity);
            }
        }
//        for (int i = 0; i < activityStack.size(); i++) {
//            Activity activity = activityStack.get(i);
//            if (activity != null && !activity.getClass().equals(cls)) {
//                activityStack.remove(activity);
//            }
//        }
    }

    /**
     * finish all activity except clss
     */
    public static void finishAllActivityExcept(Class<?>... clss) {
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            for (Class<?> cls : clss) {
                if (activity != null && !activity.getClass().equals(cls)) {
                    activity.finish();
                    activityStack.remove(activity);
                }
            }
        }
    }

    /**
     * get activity
     */
    public static Activity getActivity(Class<?> cls) {
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (activity != null && activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * get activity from stack top
     */
    public static Activity getTopActivity() {
        if (activityStack.size() > 0) {
            return activityStack.get(activityStack.size() - 1);
        }
        return null;
    }

    /**
     * is activity exist
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
