package com.mackwu.component.ui.livedata;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import androidx.lifecycle.MutableLiveData;

import com.mackwu.base.util.Logger;
import com.mackwu.component.util.NetworkUtil;

/**
 * ===================================================
 * Created by MackWu on 2020/7/9 10:34
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class NetworkLiveData extends MutableLiveData<Boolean> {

    private static NetworkLiveData networkLiveData;
    private final Context context;
    private final NetworkReceiver networkReceiver;
    private final IntentFilter intentFilter;

    private NetworkLiveData(final Context context) {
        this.context = context.getApplicationContext();
        this.networkReceiver = new NetworkReceiver();
        this.intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    }

    public static NetworkLiveData getInstance(final Context context) {
        if (networkLiveData == null) {
            networkLiveData = new NetworkLiveData(context);
        }
        return networkLiveData;
    }

    @Override
    protected void onActive() {
        Logger.d("onActive...");
        context.registerReceiver(networkReceiver, intentFilter);
    }

    @Override
    protected void onInactive() {
        Logger.d("onInactive...");
        context.unregisterReceiver(networkReceiver);
    }

    private static class NetworkReceiver extends BroadcastReceiver {

        // 是否时首次收到
        private boolean isFirstFlag = true;
        // 上次网络状态
        private boolean lastNetworkAvailable = false;

        @Override
        public void onReceive(final Context context, final Intent intent) {
            synchronized (NetworkLiveData.class) {
                if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                    if (isFirstFlag) {
                        // 首次收到广播，直接发送通知。
                        isFirstFlag = false;
                        lastNetworkAvailable = NetworkUtil.isNetworkConnected(context);
                        Logger.d("first lastNetworkAvailable: " + lastNetworkAvailable);
                        getInstance(context).setValue(lastNetworkAvailable);
                    } else {
                        // 非首次收到广播，判断如果上一次网络状态是已连接且当前网络状态是已连接，则不发送通知。
                        boolean networkAvailable = NetworkUtil.isNetworkConnected(context);
                        if (!(lastNetworkAvailable && networkAvailable)) {
                            Logger.d("lastNetworkAvailable: " + lastNetworkAvailable + ", networkAvailable: " + networkAvailable);
                            getInstance(context).setValue(networkAvailable);
                        }
                        lastNetworkAvailable = networkAvailable;
                    }
                }
            }
        }
    }
}
