package com.mackwu.kt.pattern.singleton.java;

/**
 * ===================================================
 * Created by MackWu on 2020/1/7 15:39
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 饿汉式是由the singleton instance is early created at compile time中的early音译过来的。编译期实例化对象
 */
public class JavaEarlySingleton {

    private static JavaEarlySingleton instance = new JavaEarlySingleton();

    private JavaEarlySingleton() {
    }

    public static JavaEarlySingleton getInstance() {
        return instance;
    }

}
