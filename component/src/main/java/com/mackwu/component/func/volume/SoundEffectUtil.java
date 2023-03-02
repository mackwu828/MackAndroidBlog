package com.mackwu.component.func.volume;

import android.content.Context;
import android.provider.Settings;

/**
 * @author MackWu
 * @since 2022/6/28 14:33
 * 触屏音效
 */
public class SoundEffectUtil {

    /**
     * 触屏音量是否打开
     * settings get system sound_effects_enabled
     * settings put system sound_effects_enabled 1 开启
     * settings put system sound_effects_enabled 0 关闭
     *
     * @param context context
     */
    public static boolean isSoundEffectEnabled(Context context) {
        return Settings.System.getInt(context.getContentResolver(), Settings.System.SOUND_EFFECTS_ENABLED, 1) != 0;
    }

    /**
     * 开启触屏音量
     *
     * @param context context
     */
    public static void setSoundEffectEnabled(Context context) {
        Settings.System.putInt(context.getContentResolver(), Settings.System.SOUND_EFFECTS_ENABLED, 1);
    }

    /**
     * 关闭触屏音量
     *
     * @param context context
     */
    public static void setSoundEffectDisabled(Context context) {
        Settings.System.putInt(context.getContentResolver(), Settings.System.SOUND_EFFECTS_ENABLED, 0);
    }

    /**
     * 设置触屏音量开关
     *
     * @param context  context
     * @param isEnable 是否打开
     */
    public static void setSoundEffect(Context context, boolean isEnable) {
        Settings.System.putInt(context.getContentResolver(), Settings.System.SOUND_EFFECTS_ENABLED, isEnable ? 1 : 0);
    }

}
