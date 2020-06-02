package com.mackwu.kt.generic.java;

/**
 * ===================================================
 * Created by MackWu on 2020/4/21 10:48
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class GenericMethod {

    public <T> void show(T t) {
        System.out.println(t.toString());
    }

}
