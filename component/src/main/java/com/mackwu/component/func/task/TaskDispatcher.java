package com.mackwu.component.func.task;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.mackwu.base.util.Logger;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author MackWu
 * @since 2023/4/10 15:37
 */
public class TaskDispatcher {

    private static volatile TaskDispatcher instance;
    private final LinkedBlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>();
    private HandlerThread handlerThread;
    // 子线程的Handler
    private Handler workHandler;
    // 主线程的Handler
    private final Handler mainHandler = new SafeHandler(Looper.getMainLooper());

    public TaskDispatcher() {
        initWorkHandler();
    }

    public static TaskDispatcher getInstance() {
        if (instance == null) {
            synchronized (TaskDispatcher.class) {
                if (instance == null) {
                    instance = new TaskDispatcher();
                }
            }
        }
        return instance;
    }

    private void initWorkHandler() {
        handlerThread = new HandlerThread("IoHandler");
        handlerThread.start();
        workHandler = new SafeHandler(handlerThread.getLooper()) {

            @Override
            public void handleMessage(@NonNull Message msg) {
                dispatchTask();
            }
        };
    }

    private void dispatchTask() {
        try {
            Task task = taskQueue.take();
            if (task != null) {
                task.doInBackground();
            }
            mainHandler.post(() -> {
                if (task != null) {
                    task.onSuccess();
                }
            });
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public TaskDispatcher addTask(Task task) {
        taskQueue.add(task);
        return this;
    }

    public void start() {
        if (handlerThread == null || workHandler == null) {
            initWorkHandler();
        }
        if (workHandler != null) {
            workHandler.sendEmptyMessage(1);
        }
    }

    public void stop() {
        taskQueue.clear();
        handlerThread.quit();
        handlerThread = null;
        workHandler = null;
    }

}
