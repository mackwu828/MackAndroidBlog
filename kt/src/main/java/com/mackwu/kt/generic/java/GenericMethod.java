package com.mackwu.kt.generic.java;

/**
 * ===================================================
 * Created by MackWu on 2020/4/21 10:48
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class GenericMethod {

    public static <T extends Number> double add(T a, T b) {
        return a.doubleValue() + b.doubleValue();
    }

}
