package com.mackwu.component.jbase.pattern.singleton;

/**
 * ===================================================
 * Created by MackWu on 2020/12/10 14:30
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class Singleton1 {

    private static final Singleton1 instance = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        return instance;
    }

}
