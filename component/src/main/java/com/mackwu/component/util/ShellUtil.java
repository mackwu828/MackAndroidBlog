package com.mackwu.component.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ===================================================
 * Created by MackWu on 2020/8/9 17:23
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class ShellUtil {

    public static void execCommand(String command) {

    }

    public static void execCommand() {
//        String[] commands = new String[]{
//                "settings put secure enabled_accessibility_services com.zeasn.tvappalexa/com.zeasn.tvappalexa.AvsService",
//                "settings put secure accessibility_enabled 1"
//        };
        execCommand("settings put secure enabled_accessibility_services com.zeasn.tvappalexa/com.zeasn.tvappalexa.AvsService");
    }
}
