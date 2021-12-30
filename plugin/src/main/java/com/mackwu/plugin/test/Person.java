package com.mackwu.plugin.test;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * ===================================================
 * Created by MackWu on 2021/11/1 14:43
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class Person {

    public static void play() {
        try {
            System.out.println(1 / 0);
        } catch (Exception e) {
//            e.printStackTrace();
//            StringWriter sw = new StringWriter();
//            PrintWriter pw = new PrintWriter(sw);
//            e.printStackTrace(pw);
//            String stackTrace = sw.toString();
//            IOUtil.close(sw, pw);
//            String newStackTrace = stackTrace.replace("\n","<br/>");
//            System.out.println(newStackTrace);
        }
    }

}
