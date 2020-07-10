package com.mackwu.component.util;

import android.annotation.SuppressLint;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ===================================================
 * Created by MackWu on 2020/6/29 19:59
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class SystemPropUtil {

    /**
     * 获取系统属性
     *
     * @param key          属性key
     * @param defaultValue 属性默认值
     */
    @SuppressWarnings("unchecked")
    @SuppressLint("PrivateApi")
    public static String get(String key, String defaultValue) {
        try {
            Class systemProperties = Class.forName("android.os.SystemProperties");
            Method getProp = systemProperties.getDeclaredMethod("get", String.class, String.class);
            return (String) getProp.invoke(systemProperties, key, defaultValue);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 设置系统属性
     *
     * @param key   属性key
     * @param value 属性值
     * @return
     */
    @SuppressWarnings("unchecked")
    @SuppressLint("PrivateApi, DiscouragedPrivateApi")
    public static void set(String key, String value) {
        try {
            Class systemProperties = Class.forName("android.os.SystemProperties");
            Method setProp = systemProperties.getDeclaredMethod("set", String.class, String.class);
            setProp.invoke(systemProperties, key, value);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
