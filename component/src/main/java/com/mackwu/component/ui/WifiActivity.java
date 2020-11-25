package com.mackwu.component.ui;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.component.R;
import com.mackwu.xmvc.BaseActivity;
import com.mackwu.xmvc.util.LogUtil;

import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2020/9/13 18:28
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class WifiActivity extends BaseActivity {

    private WifiManager wifiManager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        // isWifiEnabled
        boolean isWifiEnabled = wifiManager.isWifiEnabled();
        LogUtil.d("isWifiEnabled: " + isWifiEnabled);

        // scanResults
        List<ScanResult> scanResults = wifiManager.getScanResults();
        for (ScanResult scanResult : scanResults) {
            LogUtil.d(scanResult.toString());
        }

        // 已连接wifi
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
    }

}
