package com.mackwu.component.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityTestBinding;

/**
 * ===================================================
 * Created by MackWu on 2021/1/27 16:32
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class HandlerActivity extends BaseActivity<BaseViewModel, ActivityTestBinding> {

    private Handler handler;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        new Thread(() -> {
//            Looper.prepare();
            handler = new Handler() {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                }
            };
//            Looper.loop();
        }).start();
    }

}
