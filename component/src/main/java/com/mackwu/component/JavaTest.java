package com.mackwu.component;

import javax.xml.transform.Source;

/**
 * ===================================================
 * Created by MackWu on 2020/11/12 14:37
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class JavaTest {

    public static void main(String[] args) {
        String mac = "7C:25:DA:DD:FB:DF";
        mac = mac.replaceAll(":", "").replaceAll("%3A", "");
        StringBuilder stringBuilder = new StringBuilder(mac);
        stringBuilder.insert(6, "fffe");
        System.out.println(stringBuilder.toString());
    }
}
