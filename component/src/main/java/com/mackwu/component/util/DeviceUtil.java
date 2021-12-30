package com.mackwu.component.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * ===================================================
 * Created by MackWu on 2021/11/4 16:39
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * https://www.jianshu.com/p/df3f549ddd35
 * https://www.cnblogs.com/chorm590/p/11003222.html
 */
public final class DeviceUtil {

    /**
     * 获取imei
     * 注：Android6.0以上需要动态获取READ_PHONE_STATE权限
     *
     * @param context 上下文
     * @return imei
     */
    @SuppressLint("HardwareIds")
    public static String getImei(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            deviceId = telephonyManager.getImei();
        } else {
            deviceId = telephonyManager.getDeviceId();
        }
        return deviceId;
    }

    /**
     * 获取设备序列号
     *
     * @return sn
     */
    @SuppressLint("HardwareIds")
    public static String getSn() {
        String sn;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sn = Build.getSerial();
        } else {
            sn = Build.SERIAL;
        }
        if (TextUtils.isEmpty(sn)) {
            sn = SystemPropUtil.get("ro.serialno", "");
        }
        if (TextUtils.isEmpty(sn)) {
            sn = SystemPropUtil.get("ro.boot.serialno", "");
        }
        return sn;
    }

    /**
     * 获取android id
     * @param context 上下文
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

}
