package com.mackwu.kt.pattern.singleton.java;

/**
 * ===================================================
 * Created by MackWu on 2020/1/7 15:39
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class JavaLazilySingleton {

    private static JavaLazilySingleton instance;

    private JavaLazilySingleton() {
    }

    public static JavaLazilySingleton getInstance() {
        if (null == instance) {
            instance = new JavaLazilySingleton();
        }
        return instance;
    }

}
