package com.mackwu.component.util

import android.app.Activity
import java.util.*

/**
 * ===================================================
 * Created by MackWu on 2019-05-16 11:17
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
private val activityStack by lazy { Stack<Activity>() }

/**
 * 添加activity
 */
fun addActivity(activity: Activity) {
    activityStack.add(activity)
}

/**
 * 结束activity
 */
fun finishActivity(activity: Activity) {
    activityStack.remove(activity)
    activity.finish()
}

fun finishActivity(cls: Class<*>) {
    val iterator = activityStack.iterator()
    while (iterator.hasNext()) {
        val activity = iterator.next() as Activity
        if (activity.javaClass == cls) {
            iterator.remove()
            activity.finish()
        }
    }
}

/**
 * 结束所有activity
 */
fun finishAllActivity() {
    for (activity in activityStack) {
        activity.finish()
    }
    activityStack.clear()
}

fun finishAllActivity(cls: Class<*>) {
    for (activity in activityStack) {
        if (activity.javaClass != cls) activity.finish()
    }
    activityStack.clear()
}

/**
 * 得到指定类名的Activity
 */
fun getActivity(cls: Class<*>): Activity? {
    for (activity in activityStack) {
        if (activity.javaClass == cls) {
            return activity
        }
    }
    return null
}