package com.mackwu.component.core;

import android.service.dreams.DreamService;

import com.mackwu.component.R;
import com.mackwu.base.util.LogUtil;

/**
 * ===================================================
 * Created by MackWu on 2020/10/9 11:37
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class MyDreamService extends DreamService {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d("onCreate...");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        LogUtil.d("onAttachedToWindow...");
        setInteractive(false);
        setFullscreen(true);
        setContentView(R.layout.layout_test);
    }

    @Override
    public void onDreamingStarted() {
        super.onDreamingStarted();
        LogUtil.d("onDreamingStarted...");
    }

    @Override
    public void onDreamingStopped() {
        super.onDreamingStopped();
        LogUtil.d("onDreamingStopped...");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LogUtil.d("onDetachedFromWindow...");
    }

}
