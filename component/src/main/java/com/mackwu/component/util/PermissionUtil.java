package com.mackwu.component.util;

import android.app.Activity;
import android.content.Context;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * ===================================================
 * Created by MackWu on 2020/7/27 14:59
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class PermissionUtil {

    /**
     * check self permission
     */
    public static void checkSelfPermission(Context context, String permission) {
        ContextCompat.checkSelfPermission(context, permission);
    }

    /**
     * request permissions
     */
    public static void requestPermissions(Activity activity, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

}
