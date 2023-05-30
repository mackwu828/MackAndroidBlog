package com.mackwu.base.wifi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;

/**
 * @author MackWu
 * @since 2023/5/15 18:44
 */
public class WifiController {

    @SuppressLint("StaticFieldLeak")
    private static WifiController instance;
    private Context context;
    private WifiManager wifiManager;
    private ConnectivityManager connectivityManager;

    private WifiController() {
    }

    public static WifiController getInstance() {
        if (instance == null) {
            instance = new WifiController();
        }
        return instance;
    }

    public void init(Context context) {
        this.context = context;
        wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public void enableWifi() {
    }

    public void disableWifi() {

    }
}
