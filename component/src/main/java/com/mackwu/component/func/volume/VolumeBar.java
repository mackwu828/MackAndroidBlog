package com.mackwu.component.func.volume;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

import androidx.appcompat.widget.AppCompatSeekBar;

import com.mackwu.base.util.Logger;

/**
 * ===================================================
 * Created by MackWu on 2022/1/7 11:34
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class VolumeBar extends AppCompatSeekBar implements SeekBar.OnSeekBarChangeListener {

    private final Context context;
    private boolean isTrackingTouch;
    private VolumeReceiver volumeReceiver;
    private VolumeReceiver.OnVolumeChangedListener onVolumeChangeListener;
    private OnSeekBarChangeListener onSeekBarChangeListener;

    public VolumeBar(Context context) {
        this(context, null);
    }

    public VolumeBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VolumeBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initView();
    }

    private void initView() {
        setMax(VolumeControl.getInstance().getMaxVolume());
        setProgress(VolumeControl.getInstance().getVolume());
        setOnSeekBarChangeListener(this);

        // volumeReceiver
        volumeReceiver = new VolumeReceiver(context);
//        volumeReceiver.setVolumeChangedListener(new VolumeReceiver.OnVolumeChangedListener() {
//            @Override
//            public void onVolumeChanged(int preVolume, int currentVolume) {
//                setProgress(currentVolume >= 0 ? currentVolume : VolumeControl.getInstance().getVolume());
//                if (onVolumeChangeListener != null) onVolumeChangeListener.onVolumeChanged(preVolume, currentVolume);
//            }
//
//            @Override
//            public void onMuteChanged(boolean isMute, int currentVolume) {
//                setProgress(isMute ? 0 : currentVolume);
//            }
//        });
        volumeReceiver.onCreate();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (!isTrackingTouch) {
            return;
        }
        Logger.d("volume onProgressChanged...  progress: " + progress);
        if (VolumeControl.getInstance().isMute()) {
            VolumeControl.getInstance().unMute();
        }
        VolumeControl.getInstance().setVolume(progress);
        if (onSeekBarChangeListener != null) onSeekBarChangeListener.onProgressChanged(seekBar, progress, fromUser);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        Logger.d("onStartTrackingTouch...");
        isTrackingTouch = true;
//        Toolbar.getInstance().cancelHideTimer();
        if (onSeekBarChangeListener != null) onSeekBarChangeListener.onStartTrackingTouch(seekBar);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Logger.d("onStopTrackingTouch...");
        isTrackingTouch = false;
//        Toolbar.getInstance().startHideTimer();
        if (onSeekBarChangeListener != null) onSeekBarChangeListener.onStopTrackingTouch(seekBar);
    }

    public void release() {
        if (volumeReceiver != null) volumeReceiver.onDestroy();
    }

    public void setOnVolumeChangeListener(VolumeReceiver.OnVolumeChangedListener onVolumeChangeListener) {
        this.onVolumeChangeListener = onVolumeChangeListener;
    }

    public void setOnCustomSeekBarChangeListener(OnSeekBarChangeListener onSeekBarChangeListener) {
        this.onSeekBarChangeListener = onSeekBarChangeListener;
    }
}
