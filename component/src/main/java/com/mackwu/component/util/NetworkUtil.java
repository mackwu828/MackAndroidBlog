package com.mackwu.component.util;

import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.net.ConnectivityManager;

/**
 * ===================================================
 * Created by MackWu on 2020/7/9 11:00
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class NetworkUtil {

    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo().isAvailable();
    }
}
