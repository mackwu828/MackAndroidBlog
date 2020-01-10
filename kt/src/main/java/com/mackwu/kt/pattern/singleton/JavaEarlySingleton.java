package com.mackwu.kt.pattern.singleton;

/**
 * ===================================================
 * Created by MackWu on 2020/1/7 15:39
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class JavaEarlySingleton {

    private static JavaEarlySingleton instance = new JavaEarlySingleton();

    private JavaEarlySingleton() {
    }

    public static JavaEarlySingleton getInstance() {
        return instance;
    }

}
