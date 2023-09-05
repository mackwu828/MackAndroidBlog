package com.mackwu.component.func.volume;

import android.content.Context;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;

/**
 * @author MackWu
 * @since 2023/7/11 17:04
 */
public class AudioFocusManager {

    public static class AudioFocus {

        private final AudioManager audioManager;
        private int audioFocusType;
        private int streamType;
        private AudioFocusRequest audioFocusRequest;
        private final AudioManager.OnAudioFocusChangeListener audioFocusChangeListener;

        public static class AudioDuckFocus extends AudioFocus {
            public AudioDuckFocus(Context context) {
                super(context);
                setAudioFocusType(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);
            }
        }

        public static class AudioGainFocus extends AudioFocus {
            public AudioGainFocus(Context context) {
                super(context);
                setAudioFocusType(AudioManager.AUDIOFOCUS_GAIN);
            }
        }

        public AudioFocus(Context context) {
            this.audioManager = (AudioManager) context.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
            this.audioFocusType = AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK;
            this.streamType = AudioManager.STREAM_MUSIC;
            this.audioFocusChangeListener = focusChange -> {
            };
        }

        /**
         * 设置语音焦点类型
         *
         * @param audioFocusType See {@link AudioManager#AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK} 声音减低
         *                       See {@link AudioManager#AUDIOFOCUS_GAIN} 声音暂停
         */
        public void setAudioFocusType(int audioFocusType) {
            this.audioFocusType = audioFocusType;
        }

        public void setStreamType(int streamType) {
            this.streamType = streamType;
        }

        /**
         * 获取语音焦点
         */
        public void requestAudioFocus() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (audioFocusRequest == null) {
                    audioFocusRequest = new AudioFocusRequest.Builder(audioFocusType)
                            .setOnAudioFocusChangeListener(audioFocusChangeListener)
                            .build();
                    audioFocusRequest.acceptsDelayedFocusGain();
                }
                if (audioManager != null) audioManager.requestAudioFocus(audioFocusRequest);
            } else {
                if (audioManager != null) audioManager.requestAudioFocus(audioFocusChangeListener, streamType, audioFocusType);
            }
        }

        /**
         * 丢弃语音焦点
         */
        public void abandonAudioFocus() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (audioFocusRequest != null && audioManager != null) audioManager.abandonAudioFocusRequest(audioFocusRequest);
            } else {
                if (audioFocusChangeListener != null && audioManager != null) audioManager.abandonAudioFocus(audioFocusChangeListener);
            }
        }
    }
}
