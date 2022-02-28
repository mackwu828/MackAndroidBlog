package com.mackwu.component.core.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mackwu.base.util.LogUtil;

/**
 * ===================================================
 * Created by MackWu on 2021/12/31 14:09
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.d("AlarmHelper", intent.getAction() + ", triggerAtMillis: " + AlarmHelper.getInstance().stampToDateStr(System.currentTimeMillis()));
        // ACTION_SLEEP_MODE_START
        if (AlarmHelper.ACTION_SLEEP_MODE_START.equals(intent.getAction())) {
            AlarmHelper.getInstance().updateSleepModeEnd();
        }
        // ACTION_SLEEP_MODE_END
        else if (AlarmHelper.ACTION_SLEEP_MODE_END.equals(intent.getAction())) {
            AlarmHelper.getInstance().updateSleepModeStart();
        }
    }

}
