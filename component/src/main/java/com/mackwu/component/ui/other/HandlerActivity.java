package com.mackwu.component.ui.other;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.Nullable;

import com.mackwu.component.R;
import com.mackwu.xmvc.BaseActivity;

/**
 * ===================================================
 * Created by MackWu on 2020/6/28 10:55
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class HandlerActivity extends BaseActivity {

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(final Message msg) {
            int what = msg.what;
            Object obj = msg.obj;
            if (what == 1) {
                Log.d("TAG", "handle message 1");
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initView(@Nullable final Bundle savedInstanceState) {

    }

    @Override
    public void initData(@Nullable final Bundle savedInstanceState) {
        Message message = handler.obtainMessage();
        message.what = 1;
        message.obj = new Object();
        handler.sendMessage(message);
    }

}
