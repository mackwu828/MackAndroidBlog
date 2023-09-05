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
                    if (bytes == null) {
                        return "";
                    }
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
        } catch (Exception e) {
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

    private String getMacAddress() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) {
                    continue;
                }
                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }
                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }
                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static String getMacAddress(Context context) {
//        WifiManager mWifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        final String[] macAddresses = (String[]) ReflectionUtils.callMethod(mWifiManager, "getFactoryMacAddresses", new Object[]{}); //mWifiManager.getFactoryMacAddresses();
//        String macAddress = "";
//        if (macAddresses != null && macAddresses.length > 0) {
//            macAddress = macAddresses[0];
//        }
//        return macAddress;
//    }

}
