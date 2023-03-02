package com.mackwu.component.util;

import android.app.ActivityManager;
import android.content.Context;

import com.mackwu.base.util.Logger;

/**
 * ===================================================
 * Created by MackWu on 2021/11/10 18:22
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class MemoryUtil {


    public static int getMemory(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        int memoryClass = activityManager.getMemoryClass();
        int largeMemoryClass = activityManager.getLargeMemoryClass();
        Logger.d("memoryClass: " + memoryClass + ", largeMemoryClass: " + largeMemoryClass);
        return memoryClass;
    }

}
