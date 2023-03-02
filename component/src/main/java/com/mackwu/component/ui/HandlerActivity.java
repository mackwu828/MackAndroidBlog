package com.mackwu.component.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.Logger;
import com.mackwu.component.databinding.ActivityTestBinding;
import com.mackwu.component.ui.viewmodel.HandlerViewModel;

/**
 * ===================================================
 * Created by MackWu on 2021/1/27 16:32
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class HandlerActivity extends BaseActivity<HandlerViewModel, ActivityTestBinding> {

    private Handler handler;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        HandlerThread handlerThread = new HandlerThread("xxx");
        handlerThread.start();
        Logger.d("currentThread=" + Thread.currentThread().getName());

        Handler handler = new Handler(handlerThread.getLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                // 在子线程中处理消息
            }
        };


        binding.btnTest.setOnClickListener(v -> {
            Message message = new Message();
            message.what = 1;
            message.obj = "XXX";
            handler.sendMessage(message);
        });
    }

    private void test() {
        Handler handler = new Handler();
//        Handler handler = new Handler(Looper.myLooper());
        // sendMessage
        Message message = new Message();
        message.what = 1;
        handler.sendMessage(message);

        // post
//        handler.post(() -> {
//
//        });
    }

    private void testHandlerThread(){
        HandlerThread handlerThread = new HandlerThread("xxx");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
    }


    private class LooperThread extends Thread {

        private Handler handler;

        @Override
        public void run() {
            Looper.prepare();
            handler = new Handler(Looper.myLooper()) {
                @Override
                public void handleMessage(@NonNull Message msg) {

                }
            };
            Looper.loop();
        }
    }


    private class MyThread extends Thread{
        Looper mLooper;

        @Override
        public void run() {
            Logger.d("run begin");
            Looper.prepare();
            synchronized (this) {
                mLooper = Looper.myLooper();
                notifyAll();
            }
            Looper.loop();
            Logger.d("run end");
        }

        private Looper getLooper(){
            synchronized (this) {
                while (mLooper == null) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return mLooper;
        }
    }

}
