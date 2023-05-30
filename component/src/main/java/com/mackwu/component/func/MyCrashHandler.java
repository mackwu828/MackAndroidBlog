package com.mackwu.component.func;

import android.content.Context;

import androidx.annotation.NonNull;

/**
 * @author MackWu
 * @since 2023/5/12 17:37
 */
public class MyCrashHandler implements Thread.UncaughtExceptionHandler {

    private Context context;

    public void init(Context context) {
        this.context = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        // 处理异常
        handleException(e);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        // 杀掉进程
        killApp();
    }


    private void handleException(@NonNull Throwable e) {

    }

    private void killApp() {

    }

}
