package com.mackwu.java_kt.kt.pattern.singleton.java;

/**
 * ===================================================
 * Created by MackWu on 2020/2/26 15:29
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 双重检查锁定
 */
public class JavaDoubleCheckSingleton {

    private static volatile JavaDoubleCheckSingleton instance;

    private JavaDoubleCheckSingleton(){

    }

    public static JavaDoubleCheckSingleton getInstance() {
        if (null == instance) {
            synchronized (JavaDoubleCheckSingleton.class) {
                if (null == instance) {
                    instance = new JavaDoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
