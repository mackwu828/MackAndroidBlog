package com.mackwu.service.access

import android.accessibilityservice.AccessibilityService
import android.annotation.TargetApi
import android.os.Build
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

/**
 * ================================================
 * Created by MackWu on 2019/9/10 18:19
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * 辅助功能服务AccessibilityService
 * AccessibilityService是系统提供的一种服务，继承自Service，提供了增强的用户界面，旨在帮助残障人士或者可能暂时无法与设备充分交互的用户。
 * 在设置 -> 系统 -> 无障碍 -> 服务 可以看到辅助功能服务页面
 *
 * 查找界面元素？模拟点击？
 *
 *
 * <h2>跳转到辅助功能服务页面</h2>
 *
 * <h2>手动开启</h2>
 * 到设置 -> 系统 -> 无障碍 -> 服务，开启辅助功能服务
 * 09-11 10:36:58.899 2665-2665/com.mackwu.service D/TAG: onCreate...
 * 09-11 10:36:58.900 2665-2665/com.mackwu.service D/TAG: onServiceConnected...
 *
 * 点击返回键
 * 09-11 10:37:08.664 2665-2665/com.mackwu.service D/TAG: onAccessibilityEvent... packageName: com.android.settings, eventType: 1
 * 09-11 10:37:08.673 2665-2665/com.mackwu.service D/TAG: onAccessibilityEvent... packageName: com.android.settings, eventType: 2048
 * 09-11 10:37:08.673 2665-2665/com.mackwu.service D/TAG: onAccessibilityEvent... packageName: com.android.settings, eventType: 2048
 * 09-11 10:37:08.722 2665-2665/com.mackwu.service D/TAG: onAccessibilityEvent... packageName: com.android.settings, eventType: 32
 * 09-11 10:37:08.722 2665-2665/com.mackwu.service D/TAG: onAccessibilityEvent... packageName: com.android.settings, eventType: 2048
 *
 * <h2>自动开启</h2>
 * @see AccessActivity
 * 需要系统权限和系统签名
 */
class AccessService : AccessibilityService() {

    override fun onCreate() {
        super.onCreate()
        Log.d("TAG", "onCreate...")
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


}