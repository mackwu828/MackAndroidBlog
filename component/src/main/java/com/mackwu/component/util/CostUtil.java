package com.mackwu.component.util;

import com.mackwu.base.util.LogUtil;

/**
 * ===================================================
 * Created by MackWu on 2022/2/9 16:15
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class CostUtil {

    public static long startTime = 0;

    public static void cost() {
        if (startTime == 0) {
            startTime = System.currentTimeMillis();
        } else {
            LogUtil.d("cost==" + (System.currentTimeMillis() - startTime));
            startTime = 0;
        }
    }

}
