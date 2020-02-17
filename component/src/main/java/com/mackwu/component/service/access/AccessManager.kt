package com.mackwu.component.service.access

import android.accessibilityservice.AccessibilityService
import android.annotation.TargetApi
import android.os.Build
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

/**
 * ================================================
 * Created by MackWu on 2019/9/11 10:52
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 */
@TargetApi(16)
object AccessManager {

    private var rootNodeInfo: AccessibilityNodeInfo? = null

    fun updateRootNodeInfo(service: AccessibilityService, event: AccessibilityEvent) {
        rootNodeInfo = if (Build.VERSION.SDK_INT >= 16) service.rootInActiveWindow else event.source
    }

    private fun findNodeInfosByText(text: String): List<AccessibilityNodeInfo>? {
        return rootNodeInfo?.findAccessibilityNodeInfosByText(text)
    }

    private fun findNodeInfosByViewId(viewId: String): List<AccessibilityNodeInfo>? {
        return if (Build.VERSION.SDK_INT >= 18) rootNodeInfo?.findAccessibilityNodeInfosByViewId(viewId) else null
    }

    fun performClickByText(text: String) {
        performClick(findNodeInfosByText(text))
    }

    fun performClickByViewId(viewId: String) {
        performClick(findNodeInfosByViewId(viewId))
    }

    /**
     * 模拟点击事件
     */
    private fun performClick(nodeInfos: List<AccessibilityNodeInfo>?): Boolean {
        nodeInfos?.run {
            for (nodeInfo in this) {
                nodeInfo.run { if (isEnabled) return performAction(AccessibilityNodeInfo.ACTION_CLICK) }
            }
        }
        return false
    }

    private fun performGloableAction(accessibilityService: AccessibilityService, action: Int): Boolean {
        return accessibilityService.performGlobalAction(action)
    }

}