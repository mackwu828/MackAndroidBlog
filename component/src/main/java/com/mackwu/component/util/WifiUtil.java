package com.mackwu.component.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2020/10/29 19:10
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class WifiUtil {

    /**
     * 获取wifiManager。
     */
    private static WifiManager getWifiManager(Context context) {
        return (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    /**
     * wifi是否开启。
     */
    public static boolean isWifiEnabled(Context context) {
        return getWifiManager(context).isWifiEnabled();
    }

    /**
     * wifi是否连接。
     */
    public static boolean isWifiConnected(Context context, String ssid) {
        WifiInfo wifiInfo = getWifiManager(context).getConnectionInfo();
//        LogUtil.d("isWifiConnected...  ssid: " + wifiInfo.getSSID());
        return wifiInfo != null && wifiInfo.getSSID() != null && wifiInfo.getSSID().replace("\"", "").equals(ssid);
    }


    public static void connectWifi(Context context, String ssid, String password) {
        WifiConfiguration conf = new WifiConfiguration();
        // 设置wifi名称
        conf.SSID = "\"" + ssid + "\"";
        // 根据密码加密方式设置wifi密码
        // WEP
        conf.wepKeys[0] = "\"" + password + "\"";
        conf.wepTxKeyIndex = 0;
        conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
//        // WPA
//        if (security == WifiSecurity.WPA || security == WifiSecurity.WPA2 || security == WifiSecurity.WPA_WPA2) {
//            conf.preSharedKey = "\"" + password + "\"";
//        }
//        // 没有密码
//        if (security == WifiSecurity.NONE) {
//            conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
//        }
        // 添加wifi配置
        WifiManager wifiManager = getWifiManager(context);
        int networkId = wifiManager.addNetwork(conf);
        // 连接wifi
        wifiManager.enableNetwork(networkId, true);
        wifiManager.reconnect();
    }

    /**
     * wifi是否保存。如果wifi配置列表中存在该wifi的配置，那么该wifi是已经输入过密码的。
     */
    @SuppressLint("MissingPermission")
    public static boolean isWifiSaved(Context context, ScanResult scanResult) {
        List<WifiConfiguration> wifiConfigurations = getWifiManager(context).getConfiguredNetworks();
        if (wifiConfigurations == null || wifiConfigurations.isEmpty()) {
            return false;
        }
        for (WifiConfiguration wifiConfiguration : wifiConfigurations) {
            // wifiConfiguration的wifi名称有引号
            if (wifiConfiguration.SSID.replace("\"", "").equals(scanResult.SSID)) {
                // 如果wifi配置列表中存在该wifi的配置，那么该wifi是已经输入过密码的
                return true;
            }
        }
        return false;
    }

    /**
     * 获取wifi密码加密方式。
     */
//    public static WifiSecurity getSecurity(ScanResult scanResult) {
//        String capabilities = scanResult.capabilities;
////        LogUtil.d("getWifiSecurity...  capabilities: " + capabilities);
//        WifiSecurity security = WifiSecurity.NONE;
//        // WEP
//        if (capabilities.toUpperCase(Locale.ENGLISH).contains("WEP")) {
//            security = WifiSecurity.WEP;
//        }
//        // WPA WPA2 WPA/WPA2
//        String[] splits = capabilities.split("]");
//        if (splits.length > 0) {
//            if (splits[0].toUpperCase(Locale.ENGLISH).contains("WPA")) {
//                security = WifiSecurity.WPA;
//            }
//            if (splits[0].toUpperCase(Locale.ENGLISH).contains("WPA2")) {
//                security = WifiSecurity.WPA2;
//            }
//        }
//        if (splits.length > 1) {
//            if (splits[0].toUpperCase(Locale.ENGLISH).contains("WPA") && splits[1].toUpperCase(Locale.ENGLISH).contains("WPA2")) {
//                security = WifiSecurity.WPA_WPA2;
//            }
//        }
//        return security;
//    }

    /**
     * 获取wifi信号强度
     *
     * @param scanResult scanResult
     * @param numLevels  数字等级
     * @return
     */
    public static int getLevel(ScanResult scanResult, int numLevels) {
        return WifiManager.calculateSignalLevel(scanResult.level, numLevels);
    }

    /**
     * 获取ip地址
     */
    public static String getIpAddress(Context context) {
        WifiInfo wifiInfo = getWifiManager(context).getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        return (ipAddress & 0xFF) + "." +
                ((ipAddress >> 8) & 0xFF) + "." +
                ((ipAddress >> 16) & 0xFF) + "." +
                ((ipAddress >> 24) & 0xFF);
    }

    /**
     * 获取连接速度。一直是0？
     */
    public static String getLinkSpeed(Context context) {
        WifiInfo wifiInfo = getWifiManager(context).getConnectionInfo();
        return wifiInfo.getLinkSpeed() + WifiInfo.LINK_SPEED_UNITS;
    }

    /**
     * 获取频率。5Ghz/2.4Ghz
     */
    public static String getFrequency(Context context) {
        WifiInfo wifiInfo = getWifiManager(context).getConnectionInfo();
        int freq = wifiInfo.getFrequency();
        if (freq > 2400 && freq < 2500) {
            return "2.4 Ghz";
        }
        if (freq > 4900 && freq < 5900) {
            return "5 Ghz";
        }
        return freq + " " + WifiInfo.FREQUENCY_UNITS;
    }

}
