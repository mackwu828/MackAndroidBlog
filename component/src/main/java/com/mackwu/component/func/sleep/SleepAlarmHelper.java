package com.mackwu.component.func.sleep;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.mackwu.base.util.Logger;
import com.mackwu.component.ComponentApp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * ===================================================
 * Created by MackWu on 2021/12/31 15:25
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 闹钟功能
 * 设置闹钟时间 => AlarmManager#setExact => 时间到后系统会发出广播 => AlarmReceiver接收 => 触发指定操作
 */
public class SleepAlarmHelper {

    public static final String TAG = SleepAlarmHelper.class.getSimpleName();
    public static final String ACTION_SLEEP_MODE_START = "com.zeasn.action.SLEEP_MODE_START";
    public static final String ACTION_SLEEP_MODE_END = "com.zeasn.action.SLEEP_MODE_END";

    private static SleepAlarmHelper instance;
    private final Application context;
    private final AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private int currentHour;
    private int currentMinute;
    // 是否是休眠开始
    private boolean isStart;
    // 休眠结束时间是否是当天
    private boolean isCurrentDay;
    // 记录下设置休眠开始时间戳
    private long startSetTime;
    // 记录下设置休眠结束时间戳
    private long endSetTime;

    private SleepAlarmHelper() {
        this.context = ComponentApp.getInstance();
        this.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    public static SleepAlarmHelper getInstance() {
        if (instance == null) {
            instance = new SleepAlarmHelper();
        }
        return instance;
    }

    /**
     * 设置Alarm
     *
     * @param triggerAtMillis 触发时间
     */
    @SuppressLint("UnspecifiedImmutableFlag")
    private void setExact(long triggerAtMillis) {
        if (isStart) {
            startSetTime = System.currentTimeMillis();
        } else {
            endSetTime = System.currentTimeMillis();
        }
        Intent intent = new Intent(context, SleepAlarmReceiver.class);
        intent.setAction(isStart ? ACTION_SLEEP_MODE_START : ACTION_SLEEP_MODE_END);
        // pendingIntent
        // FLAG_UPDATE_CURRENT: 如果要创建的PendingIntent已经存在了，那么在保留原先PendingIntent的同时，将原先PendingIntent封装的Intent中的extra部分替换为现在新创建的PendingIntent的intent中extra的内容。
        // FLAG_CANCEL_CURRENT，如果要创建的PendingIntent已经存在了，那么在创建新的PendingIntent之前，会取消使用之前的PendingIntent。
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        // 设置闹钟
        // AlarmManager.ELAPSED_REALTIME_WAKEUP 表示闹钟在设备睡眠状态下不可用，该状态下闹钟使用相对时间。
        // AlarmManager.RTC_WAKEUP 表示闹钟在设备睡眠状态下会唤醒系统并执行提示功能，该状态下闹钟使用绝对时间即系统时间。
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            // Android4.4~Android6.0
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);
        } else {
            // Android6.0以上
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);
        }
    }

    /**
     * 设置休眠模式开始 => 触发Receiver
     */
    private void setSleepModeStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, startHour);
        calendar.set(Calendar.MINUTE, startMinute);
        calendar.set(Calendar.SECOND, 0);
        isStart = true;
        setExact(calendar.getTimeInMillis());
        Logger.d(TAG, "setSleepModeStart...  " + stampToDateStr(calendar.getTimeInMillis()));
    }

    /**
     * 设置休眠模式结束 => 触发Receiver
     */
    private void setSleepModeEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, endHour);
        calendar.set(Calendar.MINUTE, endMinute);
        calendar.set(Calendar.SECOND, 0);
        isStart = false;
        setExact(calendar.getTimeInMillis());
        Logger.d(TAG, "setSleepModeEnd...  " + stampToDateStr(calendar.getTimeInMillis()));
    }

    /**
     * 设置第二天休眠模式开始 => 触发Receiver
     */
    private void setNextDaySleepModeStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, startHour);
        calendar.set(Calendar.MINUTE, startMinute);
        calendar.set(Calendar.SECOND, 0);
        isStart = true;
        setExact(calendar.getTimeInMillis());
        Logger.d(TAG, "setNextDaySleepModeStart...  " + stampToDateStr(calendar.getTimeInMillis()));
    }

    /**
     * 设置第二天休眠模式结束 => 触发Receiver
     */
    private void setNextDaySleepModeEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, endHour);
        calendar.set(Calendar.MINUTE, endMinute);
        calendar.set(Calendar.SECOND, 0);
        isStart = false;
        setExact(calendar.getTimeInMillis());
        Logger.d(TAG, "setNextDaySleepModeEnd...  " + stampToDateStr(calendar.getTimeInMillis()));
    }

    /**
     * 设置睡眠模式。
     *
     * @param startTime 睡眠开始时间
     * @param endTime   睡眠结束时间
     */
    public void setSleepMode(long startTime, long endTime) {
        // startCalendar
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTimeInMillis(startTime);
        startHour = startCalendar.get(Calendar.HOUR_OF_DAY);
        startMinute = startCalendar.get(Calendar.MINUTE);
        // endCalendar
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTimeInMillis(endTime);
        endHour = endCalendar.get(Calendar.HOUR_OF_DAY);
        endMinute = endCalendar.get(Calendar.MINUTE);
        // currentCalendar
        Calendar currentCalendar = Calendar.getInstance();
        this.currentHour = currentCalendar.get(Calendar.HOUR_OF_DAY);
        this.currentMinute = currentCalendar.get(Calendar.MINUTE);
        Logger.d(TAG, "setSleepMode...  startTime==" + startHour + ":" + startMinute + ", endTime==" + endHour + ":" + endMinute + ", currentTime==" + currentHour + ":" + currentMinute);
        // startSleepMode
        startSleepMode();
    }

    /**
     * 设置睡眠模式。
     *
     * @param startHour   睡眠开始时
     * @param startMinute 睡眠开始分
     * @param endHour     睡眠结束时
     * @param endMinute   睡眠结束分
     */
    public void setSleepMode(int startHour, int startMinute, int endHour, int endMinute) {
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
        Calendar calendar = Calendar.getInstance();
        this.currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        this.currentMinute = calendar.get(Calendar.MINUTE);
        Logger.d(TAG, "setSleepMode...  startTime==" + startHour + ":" + startMinute + ", endTime==" + endHour + ":" + endMinute + ", currentTime==" + currentHour + ":" + currentMinute);
        startSleepMode();
    }

    /**
     * 启动睡眠模式。
     */
    private void startSleepMode() {
        cancelSleepMode("SleepAlarmHelper->startSleepMode");
        if (endHour > startHour || (endHour == startHour && endMinute > startMinute)) {
            // 结束睡眠时间是当天
            isCurrentDay = true;
            setCurrentDaySleepMode();
        } else {
            // 结束睡眠时间是第二天
            isCurrentDay = false;
            setNextDaySleepMode();
        }
    }

    /**
     * 设置当天的睡眠模式
     */
    private void setCurrentDaySleepMode() {
        Logger.d(TAG, "setCurrentDaySleepMode...");
        /*
         * 睡眠时间例子：19:33-21:45
         * 17:22 < 19:33 => setSleepModeStart
         *
         * 19:22 < 19:33 => setSleepModeStart
         * 19:33 = 19:33 => setSleepModeStart
         * 19:44 > 19:33 => setSleepModeEnd
         *
         * 19:44 < 21:45 => setSleepModeEnd
         * 21:45 = 21:45 => setSleepModeEnd
         * 21:55 > 21:45 => setNextDaySleepModeStart
         */
        if (endHour > startHour) {
            if (currentHour < startHour) {
                setSleepModeStart();
                return;
            }
            if (currentHour == startHour) {
                if (currentMinute <= startMinute) {
                    setSleepModeStart();
                } else {
                    setSleepModeEnd();
                }
                return;
            }
            if (currentHour > startHour) {
                if (currentHour <= endHour) {
                    setSleepModeEnd();
                } else {
                    setNextDaySleepModeStart();
                }
                return;
            }
            setNextDaySleepModeStart();
        }
        /*
         * 睡眠时间例子：19:33-19:44
         * 17:22 < 19:33 => setSleepModeStart
         *
         * 19:22 < 19:33 => setSleepModeStart
         * 19:33 = 19:33 => setSleepModeStart
         *
         * 19:35 < 19:44 => setSleepModeEnd
         * 19:44 = 19:44 => setSleepModeEnd
         * 19:50 > 19:44 => setNextDaySleepModeStart
         *
         * 20:22 > 19:44 => setNextDaySleepModeStart
         */
        if (endHour == startHour && startMinute < endMinute) {
            if (currentHour < startHour) {
                setSleepModeStart();
                return;
            }
            if (currentHour == startHour) {
                if (currentMinute <= startMinute) {
                    setSleepModeStart();
                } else {
                    if (currentMinute <= endMinute) {
                        setSleepModeEnd();
                    } else {
                        setNextDaySleepModeStart();
                    }
                }
                return;
            }
            setNextDaySleepModeStart();
        }
    }

    /**
     * 设置第二天的睡眠模式
     */
    private void setNextDaySleepMode() {
        /*
         * 睡眠时间例子：20:17-08:22
         * 07:11 < 08:22 => setSleepModeEnd
         *
         * 08:05 < 08:22 => setSleepModeStart
         * 08:22 = 08:22 => setSleepModeStart
         * 08:33 > 08:22 => setSleepModeStart
         *
         * 09:15 < 20:17 => setSleepModeStart
         *
         * 20:15 < 20:17 => setSleepModeStart
         * 20:17 = 20:17 => setSleepModeStart
         * 20:19 > 20:17 => setNextDaySleepModeEnd
         * 21:22 > 20:17 => setNextDaySleepModeEnd
         */
        /*
         * 睡眠时间例子：19:33-19:20
         */
        if (currentHour < endHour) {
            setSleepModeEnd();
            return;
        }
        if (currentHour == endHour) {
            if (currentMinute <= endMinute) {
                setSleepModeEnd();
            } else {
                setSleepModeStart();
            }
            return;
        }
        if (currentHour < startHour) {
            setSleepModeStart();
            return;
        }
        if (currentHour == startHour) {
            if (currentMinute <= startMinute) {
                setSleepModeStart();
            } else {
                setNextDaySleepModeEnd();
            }
            return;
        }
        setNextDaySleepModeEnd();
    }

    /**
     * 更新休眠模式结束时间
     */
    public void updateSleepModeEnd() {
        // 如果是当天，则设置休眠模式结束
        if (isCurrentDay) {
            setSleepModeEnd();
        } else {
            // 如果是第二天，则设置第二天休眠模式结束
            setNextDaySleepModeEnd();
        }
    }

    /**
     * 更新休眠模式开始时间
     */
    public void updateSleepModeStart() {
        // 如果是当天，则设置第二天的休眠模式开始
        if (isCurrentDay) {
            // 睡眠时间例子：14:02-14:47
            // 14:13 => setSleepModeEnd
            // 跨天设置系统时间时，天数已经是第二天的时间了 => setSleepModeStart
            Calendar endSetCalendar = Calendar.getInstance();
            endSetCalendar.setTimeInMillis(endSetTime);
            int endSetDay = endSetCalendar.get(Calendar.DAY_OF_MONTH);
            int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            Logger.d(TAG, "updateSleepModeStart...  endSetDay==" + endSetDay + ", currentDay==" + currentDay);
            if (currentDay > endSetDay) {
                setSleepModeStart();
            } else {
                setNextDaySleepModeStart();
            }
        } else {
            // 如果是第二天，则设置当天休眠模式开始
            setSleepModeStart();
        }
    }

    /**
     * 取消睡眠模式
     */
    public void cancelSleepMode(String from) {
        if (alarmManager != null && pendingIntent != null) {
            Logger.d(TAG, "cancelSleepMode...  " + from);
            alarmManager.cancel(pendingIntent);
        }
    }

    /**
     * 时间戳转字符串，用于打印。
     */
    public String stampToDateStr(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return simpleDateFormat.format(time);
    }

    /**
     * 触发时间是否是否超过一天。用于判断是否是网络连接成功之后系统时间被自动更新了
     */
    public boolean isTriggerMoreThanOneDay() {
        return System.currentTimeMillis() - startSetTime > 36 * 60 * 60 * 1000;
    }

    /**
     * 触发时间是否在开始休眠到结束休眠之间
     */
    public boolean isTriggerAtStartToEnd() {
        Calendar triggerCalendar = Calendar.getInstance();
        int triggerHour = triggerCalendar.get(Calendar.HOUR_OF_DAY);
        int triggerMinute = triggerCalendar.get(Calendar.MINUTE);
        if (isCurrentDay) {
            /*
             * 睡眠时间例子：18:17-20:22
             * 19:22 => true
             * 18:17 => true
             * 18:22 => true
             * 20:01 => true
             * 20:22 => true
             */
            if (endHour > startHour) {
                if (triggerHour > startHour && triggerHour < endHour) {
                    return true;
                }
                if (triggerHour == startHour) {
                    return triggerMinute >= startMinute;
                }
                if (triggerHour == endHour) {
                    return triggerMinute <= endMinute;
                }
            }
            /*
             * 睡眠时间例子：20:43-20:44
             * 20:43 => true
             * 20:44 => true
             */
            if (startHour == endHour) {
                return triggerMinute >= startMinute && triggerMinute <= endMinute;
            }
        } else {
            /*
             * 睡眠时间例子：19:01-10:15
             * 20:44 => true
             * 08:22 => true
             *
             * 19:01 => true
             * 19:05 => true
             * 10:11 => true
             * 10:15 => true
             */
            if (startHour > endHour) {
                if (triggerHour > startHour || triggerHour < endHour) {
                    return true;
                }
                if (triggerHour == startHour) {
                    return triggerMinute >= startMinute;
                }
                if (triggerHour == endHour) {
                    return triggerMinute <= endMinute;
                }
            }
            /*
             * 睡眠时间例子：20:26-20:22
             * 只有20:23、20:24、20:25 => false
             * 21:23 => true
             * 10:23 => true
             */
            if (startHour == endHour) {
                if (triggerHour == startHour) {
                    return triggerMinute >= startMinute || triggerMinute <= endMinute;
                } else {
                    return true;
                }
            }
        }
        return false;
    }


}
