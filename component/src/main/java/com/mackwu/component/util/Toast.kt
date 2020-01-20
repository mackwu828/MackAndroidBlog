package com.mackwu.component.util

import android.app.Activity
import android.app.Service
import android.content.Context
import androidx.fragment.app.Fragment
import android.widget.Toast

/**
 * ===================================================
 * Created by MackWu on 2019-05-21 10:26
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
fun Context.toast(text: String, duration: Int = Toast.LENGTH_SHORT): Toast {
   return Toast.makeText(this, text, duration).apply { show() }
}

fun Activity.toast(text: String, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(this, text, duration).apply { show() }
}

fun androidx.fragment.app.Fragment.toast(text: String, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(activity, text, duration).apply { show() }
}

fun Service.toast(text: String, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(this, text, duration).apply { show() }
}

