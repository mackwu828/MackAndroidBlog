package com.mackwu.component.util;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import androidx.fragment.app.FragmentActivity;

import com.mackwu.xmvc.util.LogUtil;

/**
 * ===================================================
 * Created by MackWu on 2020/7/27 14:59
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class PermissionUtil {

    public static final int SYSTEM_ALERT_WINDOW_REQUEST_CODE = 0x01;

    public interface OnPermissionGrantedListener {
        void OnPermissionGranted();
    }

    /**
     * 申请悬浮窗权限。
     * Android6.0(包括6.0)以上需要申请悬浮窗权限
     */
    public static void requestSystemAlertWindow(FragmentActivity activity, OnPermissionGrantedListener listener) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (canDrawOverlays(activity)) {
                if (listener != null) listener.OnPermissionGranted();
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.setData(Uri.parse("package:" + activity.getPackageName()));
                activity.startActivityForResult(intent, SYSTEM_ALERT_WINDOW_REQUEST_CODE);
            }
        }
    }

    public static boolean canDrawOverlays(FragmentActivity activity) {
        boolean canDrawOverlays = true;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            canDrawOverlays = Settings.canDrawOverlays(activity);
        }
        LogUtil.d("canDrawOverlays: " + canDrawOverlays);
        return canDrawOverlays;
    }

}
