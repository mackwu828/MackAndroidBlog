package com.mackwu.component.util;

import java.lang.reflect.Method;

/**
 * ===================================================
 * Created by MackWu on 2021/3/19 14:48
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class AnnotationUtil {

    private static AnnotationUtil instance;
    private Class<?> cls;

    private AnnotationUtil() {
    }

    public static AnnotationUtil getInstance() {
        if (instance == null) {
            instance = new AnnotationUtil();
        }
        return instance;
    }

    public void inject(Class<?> cls) {
        this.cls = cls;
    }

    public void onMessage(String message) {
        invokeMethod(message);
    }

    public void invokeMethod(String data) {
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
//            if (method.isAnnotationPresent(IotAction.class)) {
//                IotAction annotation = method.getAnnotation(IotAction.class);
////                try {
////                    if (data.equals(annotation.value())) {
////                        method.invoke(cls.newInstance(), "ni");
////                    }
////                } catch (Throwable e) {
////                    e.printStackTrace();
////                }
//                Class<?> subCls = cls.getSuperclass();
//                Method[] subclassMethods = subCls.getMethods();
//                for (Method subclassMethod : subclassMethods) {
//                    try {
//                        if (subclassMethod.getName().equals(method.getName())) {
//                            subclassMethod.invoke(subCls.newInstance(), "xxxxx");
//                        }
//                    } catch (Throwable e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
        }
    }

}
