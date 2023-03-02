package com.mackwu.component.util;

import android.annotation.SuppressLint;
import android.app.Application;

import java.lang.reflect.Method;

/**
 * @author MackWu
 * @since 2022/8/23 16:30
 */
public class ContextUtil {

    private static Application application;

    public static Application get() {
        return application != null ? application : getApplication();
    }

    @SuppressLint({"PrivateApi", "DiscouragedPrivateApi"})
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
