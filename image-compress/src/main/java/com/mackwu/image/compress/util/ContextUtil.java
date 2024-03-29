package com.mackwu.image.compress.util;

import android.annotation.SuppressLint;
import android.app.Application;

import java.lang.reflect.Method;

/**
 * @author MackWu
 * @since 2023/3/10 17:13
 */
public class ContextUtil {

    private static Application sApplication;

    public static Application get() {
        return sApplication != null ? sApplication : getApplication();
    }

    @SuppressLint({"DiscouragedPrivateApi", "PrivateApi"})
    private static Application getApplication() {
        Application application = null;
        Method method;
        try {
            method = Class.forName("android.app.AppGlobals").getDeclaredMethod("getInitialApplication");
            method.setAccessible(true);
            application = (Application) method.invoke(null);
        } catch (Exception e) {
            try {
                method = Class.forName("android.app.ActivityThread").getDeclaredMethod("currentApplication");
                method.setAccessible(true);
                application = (Application) method.invoke(null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return application;
    }

}
