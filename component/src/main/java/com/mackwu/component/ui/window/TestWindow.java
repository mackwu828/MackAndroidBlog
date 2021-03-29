package com.mackwu.component.ui.window;

import android.content.Context;

import com.mackwu.component.R;

import java.util.Random;

/**
 * ===================================================
 * Created by MackWu on 2021/1/4 11:00
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class TestWindow extends BaseWindow {

    private static TestWindow instance;

    public TestWindow(Context context) {
        super(context);
    }

    public static TestWindow getInstance(Context context) {
        if (instance == null) {
            instance = new TestWindow(context);
        }
        return instance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.window_layout_test;
    }

    @Override
    public void show() {
        showAsSystem(300, 300, false);
    }

    public void showRandom() {
        Random random = new Random();
        showAsApp(random.nextInt(400), random.nextInt(400), false);
    }
}
