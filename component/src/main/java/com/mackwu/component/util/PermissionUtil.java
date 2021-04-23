package com.mackwu.component.util;

import androidx.fragment.app.FragmentActivity;

import com.tbruyelle.rxpermissions3.RxPermissions;

import java.util.function.Consumer;

/**
 * ===================================================
 * Created by MackWu on 2020/9/11 20:24
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public final class PermissionUtil {

    /**
     * 申请权限。
     *
     * @param activity   Activity
     * @param permission 权限
     * @param consumer   consumer
     */
    public static void requestPermission(FragmentActivity activity, String permission, Consumer<Boolean> consumer) {
//        RxPermissions rxPermissions = new RxPermissions(activity);
//        rxPermissions.request(permission)
//                .subscribe(consumer);
    }

    /**
     * 申请所有权限。
     * .subscribe(granted -> {
     * if (granted) {
     * // All requested permissions are granted
     * } else {
     * // At least one permission is denied
     * }
     * });
     */
    public static void requestPermissions(FragmentActivity activity, Consumer<Boolean> consumer, String... permissions) {
//        RxPermissions rxPermissions = new RxPermissions(activity);
//        rxPermissions.request(permissions)
//                .subscribe(consumer);
    }

}
