package com.mackwu.component.util;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2020/6/29 20:15
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class MacUtil {

    /**
     * 获取mac
     *
     * @param type eth0有线、wlan0无线。
     */
    private static String getMac(String type) {
        try {
            List<NetworkInterface> networkInterfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (final NetworkInterface networkInterface : networkInterfaces) {
                if (networkInterface.getName().toLowerCase().equals(type)) {
                    byte[] bytes = networkInterface.getHardwareAddress();
                    StringBuilder stringBuilder = new StringBuilder();
                    for (final byte b : bytes) {
                        stringBuilder.append(String.format("%02X:", b));
                    }
                    if (stringBuilder.length() > 0) {
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    }
                    return stringBuilder.toString();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 获取mac
     */
    public static String getMac() {
        String ethMac = getMac("eth0");
        return ethMac.isEmpty() ? getMac("wlan0") : ethMac;
    }

}
