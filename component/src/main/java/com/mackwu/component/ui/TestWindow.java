package com.mackwu.component.ui;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;

import com.mackwu.base.window.FloatWindow;
import com.mackwu.component.ComponentApp;
import com.mackwu.component.R;
import com.mackwu.component.databinding.WindowRedBinding;
import com.mackwu.component.databinding.WindowTestBinding;

/**
 * @author MackWu
 * @since 2023/9/25 15:29
 */
public class TestWindow {

    private static TestWindow instance;
    private static final String TAG = TestWindow.class.getCanonicalName();

    private TestWindow() {
        initWindow();
    }

    public static TestWindow getInstance() {
        if (instance == null) {
            instance = new TestWindow();
        }
        return instance;
    }

    private void initWindow() {
        Context context = ComponentApp.getInstance();
        WindowTestBinding binding = WindowTestBinding.inflate(LayoutInflater.from(context));
        FloatWindow.with(context)
                .tag(TAG)
                .view(binding.getRoot())
                .width(WindowManager.LayoutParams.MATCH_PARENT)
                .height(WindowManager.LayoutParams.MATCH_PARENT)
                .y(context.getResources().getDimensionPixelSize(R.dimen.dp_200))
                .build();
    }

    public void show() {
        FloatWindow.get(TAG).show();
    }

    public void hide() {
        FloatWindow.get(TAG).hide();
    }
}
