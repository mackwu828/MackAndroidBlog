package com.mackwu.component.util;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.provider.Settings;

import com.mackwu.base.util.Logger;

import java.util.Objects;


/**
 * ===================================================
 * Created by MackWu on 2020/10/28 13:57
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ServiceUtil {

    /**
     * 开启辅助功能。
     * settings put secure enabled_accessibility_services 包名/包名.类名
     * settings put secure accessibility_enabled 1
     * <p>
     * settings get secure enabled_accessibility_services
     * settings get secure accessibility_enabled
     */
    public static void setAccessibilityEnabled(Context context, String packageName, Class<? extends AccessibilityService> serviceCls) {
        // 需要开启的辅助功能服务。名称格式：包名/包名.类名
        String servicePackageName = Objects.requireNonNull(serviceCls.getPackage()).getName();
        String accessibilityServiceName = packageName + "/" + servicePackageName + "." + serviceCls.getSimpleName();
        // 判断辅助功能是否开启
        String enableAccessibilityService = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
        String accessibilityEnabled = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED);
        Logger.d("accessibilityServiceName: " + accessibilityServiceName + ", enableAccessibilityService: " + enableAccessibilityService + ", accessibilityEnabled: " + accessibilityEnabled);
        if (enableAccessibilityService.equals(accessibilityServiceName) && accessibilityEnabled.equals("1")) {
            return;
        }
        // putString
        Settings.Secure.putString(context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES, accessibilityServiceName);
        Settings.Secure.putString(context.getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED, "1");
    }

}
