package com.mackwu.base.util;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import androidx.fragment.app.Fragment;

/**
 * ===================================================
 * Created by MackWu on 2020/6/19 23:46
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class ActivityStartUtil {

    /**
     * 启动Activity。
     * 注：用非Activity的Context启动Activity需要添加FLAG_ACTIVITY_NEW_TASK。
     * Calling startActivity() from outside of an Activity context requires the FLAG_ACTIVITY_NEW_TASK flag. Is this really what you want?
     */
    public static void startActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    public static void startActivity(Fragment fragment, Class<?> cls) {
        Context context = fragment.getActivity();
        if (null == context) return;
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    public static void startActivity(Service service, Class<?> cls) {
        Intent intent = new Intent(service, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        service.startActivity(intent);
    }


    /*
     * * * * * * * * * * * * * * * * * * * * * * * * * * 跨进程启动 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */
    /**
     * 通过包名启动activity。
     */
    public static void startThirdPartyActivity(Context context, String packageName) {
        try {
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
            if (intent != null) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过包名和Activity名称启动Activity
     * 注：另一个activity必须要配置android:exported="true"
     */
    public static void startThirdPartyActivity(Context context, String packageName, String activityName) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(packageName, activityName));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过action启动Activity
     * 注：另一个activity必须要配置
     * 1. action
     * 2. android:exported="true"
     * 3. <category android:name="android.intent.category.DEFAULT" />
     */
    public static void startActionActivity(Context context, String action) {
        try {
            Intent intent = new Intent(action);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * * * * * * * * * * * * * * * * * * * * * * * * * * * 启动特殊页面 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */
    /**
     * 启动拨号页面
     */
    public static void startTelActivity(Context context, String tel) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 启动设置页面
     */
    public static void startSettingActivity(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        context.startActivity(intent);
    }

    /**
     * 启动GPS页面
     */
    public static void startGpsActivity(Context context) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(intent);
    }

    /**
     * 启动指定的uri
     */
    public static void startUriActivity(Context context, String uriString) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uriString));
        context.startActivity(intent);
    }

    public static void startUriActivity(Context context, String packageName, String activityName, String uriString) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uriString));
        intent.setClassName(packageName, activityName);
        context.startActivity(intent);
    }

    /**
     * 启动卸载页面
     */
    public static void startUninstallActivity(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse(packageName));
        context.startActivity(intent);
    }

}
