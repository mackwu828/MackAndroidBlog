package com.mackwu.component.func.volume;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.mackwu.base.util.Logger;

/**
 * @author MackWu
 * @since 2022/8/5 15:09
 * 每次改变音量，音量变化广播会收到4次。
 * https://stackoverflow.com/questions/42394325/why-does-volume-changed-action-get-fired-multiple-times-for-same-state-for-one-c
 */
public class VolumeReceiver extends BroadcastReceiver implements LifecycleObserver {
    private static final String ACTION_VOLUME_CHANGED = "android.media.VOLUME_CHANGED_ACTION";
    private static final String ACTION_MUTE_CHANGED = "android.media.STREAM_MUTE_CHANGED_ACTION";
    private static final String EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";
    private static final String EXTRA_PREV_VOLUME_STREAM_VALUE = "android.media.EXTRA_PREV_VOLUME_STREAM_VALUE";
    private static final String EXTRA_VOLUME_STREAM_VALUE = "android.media.EXTRA_VOLUME_STREAM_VALUE";

    final Context context;
    final IntentFilter filter = new IntentFilter();
    OnVolumeChangedListener volumeChangedListener;
    final Handler handler = new Handler(Looper.getMainLooper());

    /**
     * 音量变化监听
     */
    public interface OnVolumeChangedListener {

        /**
         * 音量变化监听
         *
         * @param currentVolume 当前音量
         */
        void onVolumeChanged(int currentVolume);
    }

    public VolumeReceiver(Context context) {
        this.context = context.getApplicationContext();
    }

    public VolumeReceiver(Context context, OnVolumeChangedListener volumeChangedListener) {
        this.context = context.getApplicationContext();
        this.volumeChangedListener = volumeChangedListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.d("onReceive...  intent=" + intent.getAction());
        // 静音变化。静音到非静音，会同时触发音量变化。
        if (ACTION_MUTE_CHANGED.equals(intent.getAction())) {
            handler.removeCallbacksAndMessages(null);
            handler.postDelayed(this::onMuteChanged, 100);
            return;
        }
        // 音量变化
        if (ACTION_VOLUME_CHANGED.equals(intent.getAction())) {
            handler.removeCallbacksAndMessages(null);
            handler.postDelayed(() -> onVolumeChanged(intent), 100);
        }
    }

    private void onMuteChanged() {
        Logger.d("ACTION_MUTE_CHANGED...");
        volumeChangedListener.onVolumeChanged(VolumeControl.getInstance().getVolume());
    }

    private void onVolumeChanged(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            int stream = extras.getInt(EXTRA_VOLUME_STREAM_TYPE);
//            int preVolume = extras.getInt(EXTRA_PREV_VOLUME_STREAM_VALUE, -1);
            int currentVolume = extras.getInt(EXTRA_VOLUME_STREAM_VALUE, -1);
            Logger.d("ACTION_VOLUME_CHANGED...  currentVolume=" + currentVolume);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        filter.addAction(ACTION_VOLUME_CHANGED);
        filter.addAction(ACTION_MUTE_CHANGED);
        context.registerReceiver(this, filter);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        try {
            context.unregisterReceiver(this);
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public void setVolumeChangedListener(OnVolumeChangedListener volumeChangedListener) {
        this.volumeChangedListener = volumeChangedListener;
    }

}
