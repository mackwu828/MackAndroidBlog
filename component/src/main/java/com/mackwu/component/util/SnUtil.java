package com.mackwu.component.util;

import android.text.TextUtils;

import com.mackwu.component.BuildConfig;

/**
 * ===================================================
 * Created by MackWu on 2020/9/23 15:42
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class SnUtil {

    /**
     * 获取序列号
     */
    public static String getSn(){
        String sn = SystemPropUtil.get("ro.serialno", "");
        if (TextUtils.isEmpty(sn)) {
            sn = SystemPropUtil.get("ro.boot.serialno", "");
        }
        return sn;
    }

}
