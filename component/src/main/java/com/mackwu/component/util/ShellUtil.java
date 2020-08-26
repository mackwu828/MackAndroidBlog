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
//        Process process = null;
//        BufferedReader bufferedReader = null;
//        InputStreamReader inputStreamReader = null;
//        try {
//            process = Runtime.getRuntime().exec(command);
//            bufferedReader = new BufferedReader();
//            BufferedReader mReader = new BufferedReader());
//            StringBuilder stringBuffer = new StringBuilder();
//            char[] buff = new char[1024];
//            int ch;
//            while ((ch = mReader.read(buff)) != -1) {
//                stringBuffer.append(buff, 0, ch);
//            }
//            mReader.close();
//            Log.d("TAG", "execCommand...  " + stringBuffer.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
////            IOUtil.close(dos);
//        }
    }

    public static void execCommand() {
//        String[] commands = new String[]{
//                "settings put secure enabled_accessibility_services com.zeasn.tvappalexa/com.zeasn.tvappalexa.AvsService",
//                "settings put secure accessibility_enabled 1"
//        };
        execCommand("settings put secure enabled_accessibility_services com.zeasn.tvappalexa/com.zeasn.tvappalexa.AvsService");
    }
}
