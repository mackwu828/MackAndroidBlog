package com.mackwu.component.func.task;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

/**
 * @author MackWu
 * @since 2023/4/10 17:28
 */
public class SafeHandler extends Handler {

    public static final String TAG = SafeHandler.class.getSimpleName();

    public SafeHandler() {
        super();
    }

    public SafeHandler(@NonNull Looper looper) {
        super(looper);
    }

    @Override
    public void dispatchMessage(Message msg) {
        try {
            super.dispatchMessage(msg);
        } catch (Exception e) {
            Log.d(TAG, "dispatchMessage Exception " + msg + " , " + e);
        } catch (Throwable t) {
            Log.d(TAG, "dispatchMessage Throwable " + msg + " , " + t);
        }
    }
}
