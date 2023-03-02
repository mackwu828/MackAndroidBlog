package com.mackwu.component.func.volume;

import android.content.Context;
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
    private final AudioManager audioManager;
    private final int streamType;
    private final int flags;
    private final AudioFocus audioFocus;
    private final int step;

    public VolumeControl(Builder builder) {
        if (instance == null) {
            instance = this;
        }
        Context context = builder.context;
        this.audioManager = (AudioManager) context.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        this.streamType = builder.streamType;
        this.flags = builder.flags;
        this.audioFocus = builder.audioFocus;
        this.step = builder.step;
    }

    public static VolumeControl getInstance() {
        return instance;
    }

    public static class Builder {
        private final Context context;
        private int streamType;
        private int flags;
        private AudioFocus audioFocus;
        private int step;

        public Builder(Context context) {
            this.context = context;
            this.streamType = AudioManager.STREAM_MUSIC;
            this.flags = AudioManager.FLAG_SHOW_UI;
            this.audioFocus = new AudioFocus.AudioDuckFocus(context);
            this.step = 5;
        }

        public Builder streamType(int streamType) {
            this.streamType = streamType;
            return this;
        }

        public Builder flags(int flags) {
            this.flags = flags;
            return this;
        }

        /**
         * 语音焦点。
         */
        public Builder audioFocus(AudioFocus audioFocus) {
            this.audioFocus = audioFocus;
            return this;
        }

        /**
         * 每次增加或减小的音量值。
         */
        public Builder step(int step) {
            this.step = step;
            return this;
        }

        public VolumeControl build() {
            return new VolumeControl(this);
        }
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && audioManager != null) {
            return audioManager.isStreamMute(streamType);
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
        }
    }

    /**
     * 获取语音焦点
     */
    public void requestAudioFocus() {
        audioFocus.requestAudioFocus();
    }

    /**
     * 丢弃语音焦点
     */
    public void abandonAudioFocus() {
        audioFocus.abandonAudioFocus();
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
