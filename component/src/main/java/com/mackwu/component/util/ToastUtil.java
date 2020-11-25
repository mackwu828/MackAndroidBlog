package com.mackwu.component.util;

import android.content.Context;
import android.widget.Toast;

/**
 * ===================================================
 * Created by MackWu on 2020/11/4 14:09
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ToastUtil {

    public static void show(Context context, CharSequence text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

}
