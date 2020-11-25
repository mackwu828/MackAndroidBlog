package com.mackwu.component.util;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Process;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * ===================================================
 * Created by MackWu on 2020/9/30 11:04
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class WebViewUtil {

    /**
     * 用反射的方法，找到WebViewDelegate的构造，设置访问级别，然后newInstance就行。WebViewDelegate的构造方法是package级别的
     */
    @SuppressLint({"PrivateApi", "DiscouragedPrivateApi"})
    public static void hookWebView() {
        // 如果是非系统进程则按正常程序走
        if (Process.myUid() != Process.SYSTEM_UID) {
            return;
        }
        int sdkInt = Build.VERSION.SDK_INT;
        try {
            Class<?> factoryClass = Class.forName("android.webkit.WebViewFactory");
            Field field = factoryClass.getDeclaredField("sProviderInstance");
            field.setAccessible(true);
            Object sProviderInstance = field.get(null);
            if (sProviderInstance != null) {
                return;
            }
            Method getProviderClassMethod;
            if (sdkInt > 22) {
                getProviderClassMethod = factoryClass.getDeclaredMethod("getProviderClass");
            } else if (sdkInt == 22) {
                getProviderClassMethod = factoryClass.getDeclaredMethod("getFactoryClass");
            } else {
                return;
            }
            getProviderClassMethod.setAccessible(true);
            Class<?> factoryProviderClass = (Class<?>) getProviderClassMethod.invoke(factoryClass);
            Class<?> delegateClass = Class.forName("android.webkit.WebViewDelegate");
            Constructor<?> delegateConstructor = delegateClass.getDeclaredConstructor();
            delegateConstructor.setAccessible(true);
            if (sdkInt < 26) {
                Constructor<?> providerConstructor = factoryProviderClass.getConstructor(delegateClass);
                providerConstructor.setAccessible(true);
                sProviderInstance = providerConstructor.newInstance(delegateConstructor.newInstance());
            } else {
                Field chromiumMethodName = factoryClass.getDeclaredField("CHROMIUM_WEBVIEW_FACTORY_METHOD");
                chromiumMethodName.setAccessible(true);
                String chromiumMethodNameStr = (String) chromiumMethodName.get(null);
                if (chromiumMethodNameStr == null) {
                    chromiumMethodNameStr = "create";
                }
                Method staticFactory = factoryProviderClass.getMethod(chromiumMethodNameStr, delegateClass);
                sProviderInstance = staticFactory.invoke(null, delegateConstructor.newInstance());
            }
            if (sProviderInstance != null) {
                field.set("sProviderInstance", sProviderInstance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
