package com.mackwu.component;


import com.google.gson.Gson;
import com.mackwu.component.util.IOUtil;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * ===================================================
 * Created by MackWu on 2020/11/12 14:37
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class JavaTest {

    public static void main(String[] args) {
        AtomicBoolean refreshing = new AtomicBoolean(false);
        System.out.println(refreshing.compareAndSet(false, true));
    }

    public static void test() {

    }

}
