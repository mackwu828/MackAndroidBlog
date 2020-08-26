package com.mackwu.component.util;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.provider.Settings;

/**
 * ===================================================
 * Created by MackWu on 2020/8/18 15:35
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class SettingUtil {

    /**
     * 设置系统属性
     *
     * @param context 上下文
     * @param key     key
     * @param obj     value
     */
    public static void put(Context context, String key, Object obj) {
        if (obj instanceof Integer) {
            Settings.Secure.putInt(context.getContentResolver(), key, (int) obj);
        } else if (obj instanceof Long) {
            Settings.Secure.putLong(context.getContentResolver(), key, (long) obj);
        } else if (obj instanceof Float) {
            Settings.Secure.putFloat(context.getContentResolver(), key, (float) obj);
        } else {
            Settings.Secure.putString(context.getContentResolver(), key, (String) obj);
        }
    }

    /**
     * 获取系统属性
     *
     * @param context    上下文
     * @param key        key
     * @param defaultObj default value
     */
    public static Object get(Context context, String key, Object defaultObj) {
        if (defaultObj instanceof Integer) {
            return Settings.Secure.getInt(context.getContentResolver(), key, (int) defaultObj);
        } else if (defaultObj instanceof Long) {
            return Settings.Secure.getLong(context.getContentResolver(), key, (long) defaultObj);
        } else if (defaultObj instanceof Float) {
            return Settings.Secure.getFloat(context.getContentResolver(), key, (float) defaultObj);
        } else {
            return Settings.Secure.getString(context.getContentResolver(), key);
        }
    }

    /**
     * 开启辅助功能
     *
     * @param context     上下文
     * @param packageName 包名
     * @param serviceCls  服务类
     */
    public static void setAccessibilityEnabled(Context context, String packageName, Class<? extends AccessibilityService> serviceCls) {
        // 已设置过返回
        if (get(context, Settings.Secure.ACCESSIBILITY_ENABLED, "").equals("1")) {
            return;
        }
        String servicePackageName = "";
        Package aPackage = serviceCls.getPackage();
        if (aPackage != null) {
            servicePackageName = aPackage.getName();
        }
        put(context, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES, packageName + "/" + servicePackageName + "." + serviceCls.getSimpleName());
        put(context, Settings.Secure.ACCESSIBILITY_ENABLED, "1");
    }

}
