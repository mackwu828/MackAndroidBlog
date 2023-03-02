package com.mackwu.component.ui.view.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import androidx.lifecycle.MutableLiveData;

import com.mackwu.base.util.Logger;


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
                Logger.d("ACTION_BATTERY_CHANGED...  level: " + level + ", total: " + total + ", status: " + status + ", plugged: " + plugged);
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

        /**
         * 电池状态：
         * {@link BatteryManager#BATTERY_STATUS_UNKNOWN} 1: 未知状态
         * {@link BatteryManager#BATTERY_STATUS_CHARGING} 2: 充电中
         * {@link BatteryManager#BATTERY_STATUS_DISCHARGING} 3: 取消充电
         * {@link BatteryManager#BATTERY_STATUS_NOT_CHARGING} 4: 未充电
         * {@link BatteryManager#BATTERY_STATUS_FULL} 5: 充满
         */
        private int status;

        /**
         * 充电方式:
         * 0: 未充电
         * {@link BatteryManager#BATTERY_PLUGGED_AC} 1: AC充电。
         * {@link BatteryManager#BATTERY_PLUGGED_USB} 2: USB充电
         * {@link BatteryManager#BATTERY_PLUGGED_WIRELESS} 4: 无线充电
         */
        private int plugged;

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

        public int getPlugged() {
            return plugged;
        }

        public void setPlugged(int plugged) {
            this.plugged = plugged;
        }
    }

}

