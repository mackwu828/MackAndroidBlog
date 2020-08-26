package com.mackwu.xmvc.util;

import android.app.Service;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

/**
 * ===================================================
 * Created by MackWu on 2020/6/19 23:46
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class ActivityStartUtil {

    public static void startActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
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
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        service.startActivity(intent);
    }
}
