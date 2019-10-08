package com.mackwu.service.accessibility

import android.accessibilityservice.AccessibilityService
import android.provider.Settings
import android.util.Log
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent

/**
 * ================================================
 * Created by MackWu on 2019/9/10 18:19
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 * 辅助功能服务AccessibilityService
 * AccessibilityService是系统提供的一种服务，继承自Service，提供了增强的用户界面，旨在帮助残障人士或者可能暂时无法与设备充分交互的用户。
 * 在设置 -> 系统 -> 无障碍 -> 服务 可以看到辅助功能服务页面
 *
 * <h2>跳转到辅助功能页面</h2>
 *
 * <h2>手动开启辅助功能</h2>
 * 到设置 -> 系统 -> 无障碍 -> 服务，开启辅助功能服务 -> 点击返回
 * 测试：
 * 09-11 10:36:58.899 2665-2665/com.mackwu.service D/TAG: onCreate...
 * 09-11 10:36:58.900 2665-2665/com.mackwu.service D/TAG: onServiceConnected...
 * 09-11 10:37:08.664 2665-2665/com.mackwu.service D/TAG: onAccessibilityEvent... packageName: com.android.settings, eventType: 1
 * 09-11 10:37:08.673 2665-2665/com.mackwu.service D/TAG: onAccessibilityEvent... packageName: com.android.settings, eventType: 2048
 * 09-11 10:37:08.673 2665-2665/com.mackwu.service D/TAG: onAccessibilityEvent... packageName: com.android.settings, eventType: 2048
 * 09-11 10:37:08.722 2665-2665/com.mackwu.service D/TAG: onAccessibilityEvent... packageName: com.android.settings, eventType: 32
 * 09-11 10:37:08.722 2665-2665/com.mackwu.service D/TAG: onAccessibilityEvent... packageName: com.android.settings, eventType: 2048
 *
 * <h2>自动开启辅助功能</h2>
 * Settings.Secure.putString(contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES, "packageName/serviceName")
 * Settings.Secure.putString(contentResolver, Settings.Secure.ACCESSIBILITY_ENABLED, "1")
 * 如果手动开启辅助功能，还要用户手动操作，体验极差。自动开启辅助功能需要用Settings添加系统属性，使用Settings添加系统属性需要系统权限（即添加SharedUserId）和系统签名
 *
 * <h2>在Service中获取keyCode</h2>
 * 如果没开启辅助功能，在Service中不会走onKeyEvent回调，即获取不到keyCode。开启后会走onKeyEvent回调
 * 测试：
 *
 *
 * <h2>模拟点击事件？？</h2>
 */
class AccessService : AccessibilityService() {

    override fun onCreate() {
        super.onCreate()
        Log.d("TAG", "onCreate...")

//        Settings.Secure.putString(contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES, "packageName/serviceName")
//        Settings.Secure.putString(contentResolver, Settings.Secure.ACCESSIBILITY_ENABLED, "1")
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.d("TAG", "onServiceConnected...")
    }

    override fun onInterrupt() {
        Log.d("TAG", "onInterrupt...")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        val packageName = event.packageName.toString()
        val eventType = event.eventType
        Log.d("TAG", "onAccessibilityEvent... packageName: $packageName, eventType: $eventType")
    }

    override fun onKeyEvent(event: KeyEvent): Boolean {
        Log.d("TAG", "onKeyEvent... keyCode: ${event.keyCode}")
        return super.onKeyEvent(event)
    }
}