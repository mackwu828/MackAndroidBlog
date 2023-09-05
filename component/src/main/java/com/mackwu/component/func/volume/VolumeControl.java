package com.mackwu.component.func.volume;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;

import com.mackwu.component.ComponentApp;


/**
 * @author MackWu
 * @since 2023/7/11 17:13
 */
public class VolumeControl {

    private static VolumeControl instance;
    private final AudioManager audioManager;
    private final int streamType;
    private final int flags;
    private final int step;

    public VolumeControl() {
        Context context = ComponentApp.getInstance();
        audioManager = (AudioManager) context.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        streamType = AudioManager.STREAM_MUSIC;
        flags = AudioManager.FLAG_SHOW_UI;
        if (getMaxVolume() == 100) {
            step = 10;
        } else {
            step = 1;
        }
    }

    public static VolumeControl getInstance() {
        if (instance == null) {
            instance = new VolumeControl();
        }
        return instance;
    }

    /**
     * 获取音量大小
     */
    public int getVolume() {
        return audioManager != null ? audioManager.getStreamVolume(streamType) : 50;
    }

    /**
     * 获取最大音量
     */
    public int getMaxVolume() {
        return audioManager != null ? audioManager.getStreamMaxVolume(streamType) : 100;
    }

    /**
     * 设置音量大小
     *
     * @param volume 音量大小
     */
    public void setVolume(int volume) {
        if (audioManager != null) audioManager.setStreamVolume(streamType, volume, flags);
    }

    /**
     * 音量增大
     */
    public void volumeUp() {
//        if (audioManager != null) audioManager.adjustStreamVolume(streamType, AudioManager.ADJUST_RAISE, flags);
        setVolume(Math.min(getVolume() + step, getMaxVolume()));
    }

    /**
     * 音量减小
     */
    public void volumeDown() {
//        if (audioManager != null) audioManager.adjustStreamVolume(streamType, AudioManager.ADJUST_LOWER, flags);
        setVolume(Math.max(getVolume() - step, 0));
    }

    /**
     * 是否静音
     */
    public boolean isMute() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return audioManager != null && audioManager.isStreamMute(streamType);
        }
        return false;
    }

    /**
     * 设置静音
     */
    public void mute() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!isMute() && audioManager != null) {
                audioManager.adjustStreamVolume(streamType, AudioManager.ADJUST_MUTE, flags);
            }
        } else {
            if (audioManager != null) {
                audioManager.setStreamMute(streamType, true);
            }
        }
    }

    /**
     * 取消静音
     */
    public void unMute() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isMute() && audioManager != null) {
                audioManager.adjustStreamVolume(streamType, AudioManager.ADJUST_UNMUTE, flags);
            }
        } else {
            if (audioManager != null) {
                audioManager.setStreamMute(streamType, false);
            }
        }
    }

    /**
     * 是否正在播放音乐
     */
    public boolean isMusicActive() {
        if (audioManager != null) {
            return audioManager.isMusicActive();
        }
        return false;
    }

}
