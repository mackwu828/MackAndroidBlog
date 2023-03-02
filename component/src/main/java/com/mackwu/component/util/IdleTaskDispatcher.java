package com.mackwu.component.util;

import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author MackWu
 * @since 2023/2/23 14:41
 */
public class IdleTaskDispatcher {

    MessageQueue.IdleHandler idleHandler;
    Queue<Runnable> tasks = new LinkedList<>();
    Handler handler = new Handler(Looper.getMainLooper());

    public IdleTaskDispatcher() {
        idleHandler = () -> {
            Runnable runnable = tasks.poll();
            if (runnable != null) {
                runnable.run();
            }
            return false;
//            return !tasks.isEmpty();
        };
    }

    public IdleTaskDispatcher addTask(long timeout, Runnable runnable) {
        tasks.add(runnable);
        handler.postDelayed(() -> {
            if (tasks.contains(runnable)) {
                tasks.remove(runnable);
                runnable.run();
            }
        }, timeout);
        return this;
    }

    public void removeTask(Runnable runnable) {
        tasks.remove(runnable);
    }

    public void start() {
        Looper.myQueue().addIdleHandler(idleHandler);
    }

    public void stop() {
        Looper.myQueue().removeIdleHandler(idleHandler);
    }

}
