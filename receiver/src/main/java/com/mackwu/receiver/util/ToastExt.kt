package com.mackwu.receiver.util

import android.app.Activity
import android.app.Service
import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * @author wmj
 * @date 2019-05-21 10:26
 * Toast
 */

fun Activity.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(text: String) {
    activity?.showToast(text)
}

fun Service.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}
