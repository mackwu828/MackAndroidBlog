package com.mackwu.component;


import java.util.Locale;

/**
 * ===================================================
 * Created by MackWu on 2020/11/12 14:37
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class JavaTest {

    public static void main(String[] args) {

        long space = 1398737;
        System.out.println(String.format(Locale.getDefault(), "%.2f", space / 1024f /1024 / 1024));
    }

    public static void test() {

    }

}
