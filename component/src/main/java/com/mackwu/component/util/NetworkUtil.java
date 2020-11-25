package com.mackwu.component.util;

import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * ===================================================
 * Created by MackWu on 2020/7/9 11:00
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class NetworkUtil {

    private static ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    private static NetworkInfo getActiveNetworkInfo(Context context) {
        return getConnectivityManager(context).getActiveNetworkInfo();
    }

    /**
     * 网络是否可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = getConnectivityManager(context);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
        if (networkInfos != null) {
            for (NetworkInfo networkInfo : networkInfos) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 是否是wifi
     */
    public static boolean isWifi(Context context) {
        return getActiveNetworkInfo(context).getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 是否是移动网络
     */
    public static boolean isMobile(Context context) {
        return getActiveNetworkInfo(context).getType() == ConnectivityManager.TYPE_MOBILE;
    }

    /**
     * 获取ip地址
     */
    public static String getIpAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        return (ipAddress & 0xFF) + "." +
                ((ipAddress >> 8) & 0xFF) + "." +
                ((ipAddress >> 16) & 0xFF) + "." +
                ((ipAddress >> 24) & 0xFF);
    }

}
