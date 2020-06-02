package com.mackwu.component.other.usb

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.opengl.EGLSurface
import android.util.Log
import android.widget.Toast
import com.mackwu.component.util.ACTION_USB_PERMISSION
import com.mackwu.component.util.toast

/**
 * ===================================================
 * Created by MackWu on 2020/4/23 10:53
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class UsbReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "onReceive...  action: " + intent.action)
        context.toast("onReceive...  action: " + intent.action)

        when (intent.action) {
            ACTION_USB_PERMISSION -> {

            }
            UsbManager.ACTION_USB_ACCESSORY_ATTACHED -> {
                val usbDevice = intent.getParcelableExtra<UsbDevice>(UsbManager.EXTRA_DEVICE)
                Log.d(TAG, "usbDevice: $usbDevice")
                val granted = intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)
                if (granted) {

                }
            }
            UsbManager.ACTION_USB_ACCESSORY_DETACHED -> {

            }
        }

    }

    companion object{
        private val TAG = UsbReceiver::class.java.simpleName
    }
}