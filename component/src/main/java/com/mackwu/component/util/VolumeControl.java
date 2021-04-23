package com.mackwu.component.util;

import android.content.Context;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;

/**
 * ===================================================
 * Created by MackWu on 2020/11/6 16:11
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class VolumeControl {

    private static VolumeControl instance;
    private AudioManager audioManager;
    private int streamType;
    private int flags;
    private AudioFocusRequest audioFocusRequest;
    private int audioFocusType;
    private AudioManager.OnAudioFocusChangeListener listener;

    private VolumeControl() {
    }

    public static VolumeControl getInstance() {
        if (instance == null) {
            instance = new VolumeControl();
        }
        return instance;
    }

    public void init(Context context) {
        this.audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        this.streamType = AudioManager.STREAM_MUSIC;
        this.flags = AudioManager.FLAG_SHOW_UI;
        this.audioFocusType = AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK;
        this.listener = focusChange -> {

        };
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
        if (audioManager != null) audioManager.adjustStreamVolume(streamType, AudioManager.ADJUST_RAISE, flags);
    }

    public void volumeUp(int step) {
        setVolume(Math.min(getVolume() + step, 100));
    }

    /**
     * 音量减小
     */
    public void volumeDown() {
        if (audioManager != null) audioManager.adjustStreamVolume(streamType, AudioManager.ADJUST_LOWER, flags);
    }

    public void volumeDown(int step) {
        setVolume(Math.max(getVolume() - step, 0));
    }

    /**
     * 是否静音
     */
    public boolean isMute() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && audioManager != null) {
            return audioManager.isStreamMute(AudioManager.STREAM_MUSIC);
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
                audioManager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_MUTE, flags);
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
                audioManager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_UNMUTE, flags);
            }
        }
    }

    /**
     * 获取语音焦点
     */
    public void requestAudioFocus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (audioFocusRequest == null) {
                audioFocusRequest = new AudioFocusRequest.Builder(audioFocusType)
                        .setOnAudioFocusChangeListener(listener)
                        .build();
                audioFocusRequest.acceptsDelayedFocusGain();
            }
            if (audioManager != null) audioManager.requestAudioFocus(audioFocusRequest);
        } else {
            if (audioManager != null) audioManager.requestAudioFocus(listener, streamType, audioFocusType);
        }
    }

    /**
     * 丢弃语音焦点
     */
    public void abandonAudioFocus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (audioFocusRequest != null && audioManager != null) audioManager.abandonAudioFocusRequest(audioFocusRequest);
        } else {
            if (listener != null && audioManager != null) audioManager.abandonAudioFocus(listener);
        }
    }

    public boolean isMusicActive() {
        if (audioManager != null) {
            return audioManager.isMusicActive();
        }
        return false;
    }

}
