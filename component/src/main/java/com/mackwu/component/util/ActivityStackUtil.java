package com.mackwu.component.util;

import android.app.Activity;

import java.util.Iterator;
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
        activityStack.remove(activity);
        activity.finish();
    }

    /**
     * finish activity
     */
    public static void finishActivity(Class<?> cls) {
        Iterator<Activity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity.getClass().equals(cls)) {
                iterator.remove();
                activity.finish();
            }
        }
    }

    /**
     * finish all activity
     */
    public static void finishAllActivity() {
        for (Activity activity : activityStack) {
            activity.finish();
        }
        activityStack.clear();
    }

    /**
     * finish all activity
     * @param cls 除了该activity
     */
    public static void finishAllActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass() != cls) activity.finish();
        }
        activityStack.clear();
    }

    /**
     * get activity
     */
    public static Activity getActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass() == cls) {
                return activity;
            }
        }
        return null;
    }
}
