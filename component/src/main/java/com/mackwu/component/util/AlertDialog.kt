package com.mackwu.component.util

import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog

/**
 * ===================================================
 * Created by MackWu on 2020/1/20 15:40
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

/**
 * 显示AlertDialog
 */
fun Context.showAlertDialog(@DrawableRes icon: Int = -1, title: CharSequence?, message: CharSequence?, confirm: CharSequence, cancel: CharSequence,
                            onPositive: (dialog: DialogInterface, which: Int) -> Unit, onNegative: (dialog: DialogInterface, which: Int) -> Unit) {
    val builder = AlertDialog.Builder(this)
            .apply { if (icon != -1) setIcon(icon) }
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(confirm, onPositive)
            .setNegativeButton(cancel, onNegative)
    val dialog = builder.create()
    dialog.show()
}

fun Context.showAlertDialog(title: CharSequence?, message: CharSequence?, confirm: CharSequence, cancel: CharSequence, onPositive: (dialog: DialogInterface, which: Int) -> Unit) = showAlertDialog(-1, title, message, confirm, cancel, onPositive) { dialog, _ -> dialog.dismiss() }

fun Context.showAlertDialog() = showAlertDialog("title", "i am a dialog", "confirm", "cancel") { _, _ -> Toast.makeText(this, "", Toast.LENGTH_SHORT).show() }
