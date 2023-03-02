package com.mackwu.component.func.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * @author MackWu
 * @since 2023/1/17 11:06
 */
public class GradeService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new GradeStub();
    }
}
