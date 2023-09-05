package com.mackwu.component.ui.livedata;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.MutableLiveData;

/**
 * @author MackWu
 * @since 2020/7/9 10:34
 */
public class NetworkFirstLiveData extends MutableLiveData<Boolean> {

    private static NetworkFirstLiveData networkFirstLiveData;
    private final Context context;
    private final NetworkReceiver networkReceiver;
    private final IntentFilter intentFilter;

    public NetworkFirstLiveData(final Context context) {
        this.context = context.getApplicationContext();
        this.networkReceiver = new NetworkReceiver();
        this.intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    }

    public static NetworkFirstLiveData getInstance(final Context context) {
        if (networkFirstLiveData == null) {
            networkFirstLiveData = new NetworkFirstLiveData(context);
        }
        return networkFirstLiveData;
    }

    @Override
    protected void onActive() {
        context.registerReceiver(networkReceiver, intentFilter);
    }

    @Override
    protected void onInactive() {
        context.unregisterReceiver(networkReceiver);
    }

    private static class NetworkReceiver extends BroadcastReceiver {

        // 是否时首次收到
        private static boolean isFirstFlag = true;

        @Override
        public  void onReceive(final Context context, final Intent intent) {
            synchronized (NetworkFirstLiveData.class) {
                if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                    // 只有首次收到才发送通知。
                    if (isNetworkAvailable(context) && isFirstFlag) {
                        isFirstFlag = false;
                        getInstance(context).setValue(true);
                    }
                }
            }
        }

        private boolean isNetworkAvailable(Context context) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
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
    }
}
