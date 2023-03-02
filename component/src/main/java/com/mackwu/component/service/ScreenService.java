package com.mackwu.component.service;

import android.service.dreams.DreamService;

import com.mackwu.component.R;
import com.mackwu.base.util.Logger;

/**
 * ===================================================
 * Created by MackWu on 2020/10/9 11:37
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 2021-12-21 04:08:22.524 2233-2233/com.mackwu.component D/mack_wu: onCreate...
 * 2021-12-21 04:08:22.560 2233-2233/com.mackwu.component D/mack_wu: onAttachedToWindow...
 * 2021-12-21 04:08:22.713 2233-2233/com.mackwu.component D/mack_wu: onDreamingStarted...
 * 2021-12-21 04:08:38.671 2233-2233/com.mackwu.component D/mack_wu: onDreamingStopped...
 * 2021-12-21 04:08:38.672 2233-2233/com.mackwu.component D/mack_wu: onDetachedFromWindow...
 * 2021-12-21 04:08:38.674 2233-2233/com.mackwu.component D/mack_wu: onDestroy...
 */
public class ScreenService extends DreamService {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.d("onCreate...");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Logger.d("onAttachedToWindow...");
        // 是否与用户交互。默认false，用户触摸时Daydream会退出。如果你希望与用户交互，则设置为true。
        setInteractive(false);
        // 是否全屏
        setFullscreen(true);
        // 设置布局
        setContentView(R.layout.layout_screensaver);
    }

    @Override
    public void onDreamingStarted() {
        super.onDreamingStarted();
        Logger.d("onDreamingStarted...");
    }

    @Override
    public void onDreamingStopped() {
        super.onDreamingStopped();
        Logger.d("onDreamingStopped...");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Logger.d("onDetachedFromWindow...");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.d("onDestroy...");
    }

}
