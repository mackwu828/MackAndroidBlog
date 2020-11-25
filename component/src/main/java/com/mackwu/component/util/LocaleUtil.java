package com.mackwu.component.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.LocaleList;
import android.util.DisplayMetrics;

import java.lang.reflect.Method;
import java.util.Locale;

/**
 * ===================================================
 * Created by MackWu on 2020/9/22 13:59
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class LocaleUtil {

    /**
     * 更新APP的语言。
     * 注：需要再调用 recreate() 刷新页面，不然语言不会更新。
     *
     * @param context 上下文
     * @param locale  语言
     */
    public static void updateAppLocale(Context context, Locale locale) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, displayMetrics);
    }

    /**
     * 更新系统的语言。
     * 注：需要有系统权限。
     *
     * @param locale 语言
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @SuppressLint({"PrivateApi", "DiscouragedPrivateApi"})
    public static void updateSystemLocale(Locale locale) {
        try {
            Class activityManagerNative = Class.forName("android.app.ActivityManagerNative");
            Method getDefault = activityManagerNative.getDeclaredMethod("getDefault");
            Object objIActivityManager = getDefault.invoke(activityManagerNative);
            Class iActivityManager = Class.forName("android.app.IActivityManager");
            Method getConfiguration = iActivityManager.getDeclaredMethod("getConfiguration");
            Configuration config = (Configuration) getConfiguration.invoke(objIActivityManager);
            if (config != null) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    LocaleList localeList = new LocaleList(locale);
                    config.setLocales(localeList);
                } else {
                    config.setLocale(locale);
                }
                Class[] clzParams = {Configuration.class};
                Method updateConfiguration = iActivityManager.getDeclaredMethod("updatePersistentConfiguration", clzParams);
                updateConfiguration.invoke(objIActivityManager, config);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
