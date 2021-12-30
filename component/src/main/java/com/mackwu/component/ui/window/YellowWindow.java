package com.mackwu.component.ui.window;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.mackwu.base.util.LogUtil;
import com.mackwu.base.window.DisplayType;
import com.mackwu.base.window.FloatWindow;
import com.mackwu.component.ComponentApp;
import com.mackwu.component.R;
import com.mackwu.component.databinding.WindowYellowBinding;

import java.util.Objects;

/**
 * ===================================================
 * Created by MackWu on 2021/9/2 16:51
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class YellowWindow {

    private static final String TAG = YellowWindow.class.getSimpleName();
    private static YellowWindow instance;

    private YellowWindow(Context context) {
        initWindow(context);
    }

    private YellowWindow() {
        initWindow(ComponentApp.getInstance());
    }

    public static YellowWindow getInstance(Context context) {
        if (instance == null) {
            instance = new YellowWindow(context);
        }
        return instance;
    }

    public static YellowWindow getInstance() {
        if (instance == null) {
            instance = new YellowWindow();
        }
        return instance;
    }

    @SuppressLint("InflateParams")
    private void initWindow(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.window_yellow, null);
        FloatWindow.with(context)
                .tag(TAG)
                .view(view)
                .width(300)
                .height(300)
                .type(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
                .displayType(DisplayType.OUT_OF_SCREEN)
                .build();

        //
        WindowYellowBinding bind = WindowYellowBinding.bind(view);
        bind.llContainer.setOnClickListener(v -> Toast.makeText(context, "XXX", Toast.LENGTH_SHORT).show());
        bind.btn1.setOnClickListener(v -> Toast.makeText(context, "111", Toast.LENGTH_SHORT).show());
    }

    public void show() {
        Objects.requireNonNull(FloatWindow.get(TAG)).show();
    }

    public void hide() {
        Objects.requireNonNull(FloatWindow.get(TAG)).hide();
    }

}
