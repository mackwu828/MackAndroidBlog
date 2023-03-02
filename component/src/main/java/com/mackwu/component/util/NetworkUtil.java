package com.mackwu.component.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

/**
 * ===================================================
 * Created by MackWu on 2020/7/9 11:00
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class NetworkUtil {

    /**
     * 网络是否连接
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
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
     * 网络是否有访问能力
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static boolean isNetworkCapabilityValidated(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network activeNetwork = connectivityManager.getActiveNetwork();
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
    }

    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiConn = false;
        for (Network network : connMgr.getAllNetworks()) {
            NetworkInfo networkInfo = connMgr.getNetworkInfo(network);
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isWifiConn = networkInfo.isConnected();
            }
        }
        return isWifiConn;
    }

    public static boolean isPingSuccess() {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process exec = runtime.exec("ping -c 3 www.baidu.com");
            int result = exec.waitFor();
            return result == 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
