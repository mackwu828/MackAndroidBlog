package com.mackwu.component.ui.window;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.mackwu.component.ComponentApp;
import com.mackwu.component.R;
import com.zeasn.floatwindow.FloatWindow;

import java.util.Objects;

/**
 * ===================================================
 * Created by MackWu on 2021/9/2 16:51
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class RedWindow {

    private static final String TAG = RedWindow.class.getSimpleName();
    private static RedWindow instance;

    private RedWindow(Context context) {
        initWindow(context);
    }

    private RedWindow() {
        initWindow(ComponentApp.getInstance());
    }

    public static RedWindow getInstance(Context context) {
        if (instance == null) {
            instance = new RedWindow(context);
        }
        return instance;
    }

    public static RedWindow getInstance() {
        if (instance == null) {
            instance = new RedWindow();
        }
        return instance;
    }

    private void initWindow(Context context) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.window_red, null);
        FloatWindow.with(context)
                .tag(TAG)
                .view(view)
                .width(300)
                .height(300)
                .type(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
                .build();
    }

    public void show() {
        Objects.requireNonNull(FloatWindow.get(TAG)).show();
    }

    public void hide() {
        Objects.requireNonNull(FloatWindow.get(TAG)).hide();
    }

}
