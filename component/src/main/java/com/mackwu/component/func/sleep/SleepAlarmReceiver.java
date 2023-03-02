package com.mackwu.component.func.sleep;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mackwu.base.util.Logger;
import com.mackwu.component.func.screen.ScreenUtil;

/**
 * ===================================================
 * Created by MackWu on 2021/12/31 15:25
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class SleepAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.d(SleepAlarmHelper.TAG, intent.getAction() + ", triggerAtMillis: " + SleepAlarmHelper.getInstance().stampToDateStr(System.currentTimeMillis()));
        // ACTION_SLEEP_MODE_START
        if (SleepAlarmHelper.ACTION_SLEEP_MODE_START.equals(intent.getAction())) {
            SleepAlarmHelper.getInstance().updateSleepModeEnd();
            if (!SleepAlarmHelper.getInstance().isTriggerMoreThanOneDay()) {
                if (SleepAlarmHelper.getInstance().isTriggerAtStartToEnd()) {
//                    ScreenUtil.turnOffScreen(context);
                } else {
                    Logger.d(SleepAlarmHelper.TAG, "触发时间不在开始休眠到结束休眠之间");
                }
            } else {
                Logger.d(SleepAlarmHelper.TAG, "触发时间超过1天");
            }
        }
        // ACTION_SLEEP_MODE_END
        else if (SleepAlarmHelper.ACTION_SLEEP_MODE_END.equals(intent.getAction())) {
            SleepAlarmHelper.getInstance().updateSleepModeStart();
            ScreenUtil.turnOnScreen(context);
        }
    }

}