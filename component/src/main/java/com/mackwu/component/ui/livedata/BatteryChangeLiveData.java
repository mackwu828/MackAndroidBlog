package com.mackwu.component.ui.livedata;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import androidx.lifecycle.MutableLiveData;

import com.mackwu.base.util.LogUtil;


/**
 * ===================================================
 * Created by MackWu on 2021/3/4 10:49
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class BatteryChangeLiveData extends MutableLiveData<BatteryChangeLiveData.BatteryInfo> {

    private static BatteryChangeLiveData instance;
    private final Context context;
    private final BatteryChangeReceiver batteryChangeReceiver;
    private final IntentFilter intentFilter;

    private BatteryChangeLiveData(Context context) {
        this.context = context.getApplicationContext();
        this.batteryChangeReceiver = new BatteryChangeReceiver();
        this.intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
    }

    public static BatteryChangeLiveData getInstance(Context context) {
        if (instance == null) {
            instance = new BatteryChangeLiveData(context);
        }
        return instance;
    }

    @Override
    protected void onActive() {
        context.registerReceiver(batteryChangeReceiver, intentFilter);
    }

    @Override
    protected void onInactive() {
        context.unregisterReceiver(batteryChangeReceiver);
    }

    private static class BatteryChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                // 当前电量
                int level = intent.getIntExtra("level", 0);
                // 总电量
                int total = intent.getIntExtra("scale", 100);
                // 充电状态
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                // 充电方式
                int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
                LogUtil.d("ACTION_BATTERY_CHANGED...  level: " + level + ", total: " + total + ", status: " + status + ", plugged: " + plugged);
                BatteryInfo batteryInfo = new BatteryInfo();
                batteryInfo.setLevel(level);
                batteryInfo.setTotal(total);
                batteryInfo.setStatus(status);
                getInstance(context).postValue(batteryInfo);
            }
//            if (Intent.ACTION_POWER_CONNECTED.equals(intent.getAction())) {
//                LogUtil.d("ACTION_POWER_CONNECTED");
//            }
//            if (Intent.ACTION_POWER_DISCONNECTED.equals(intent.getAction())) {
//                LogUtil.d("ACTION_POWER_DISCONNECTED");
//            }
        }
    }

    public static class BatteryInfo {
        private int level;
        private int total;
        private int status;

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

}

