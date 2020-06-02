package com.mackwu.component.util

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager

/**
 * ===================================================
 * Created by MackWu on 2020/4/23 11:18
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
/**
 * usb permission action
 */
const val ACTION_USB_PERMISSION = "com.mackwu.action.usbpermission"

/**
 * usb manager
 */
val Context.usbManager
    get() = getSystemService(Context.USB_SERVICE) as UsbManager

/**
 * 获取usb权限
 */
fun Context.requestUsbPermission(usbDevice: UsbDevice) {
    if (!usbManager.hasPermission(usbDevice)) {
        // 没有usb权限
        val pendingIntent = PendingIntent.getBroadcast(this, 0x01, Intent(ACTION_USB_PERMISSION), 0)
        usbManager.requestPermission(usbDevice, pendingIntent)
    } else {

    }
}