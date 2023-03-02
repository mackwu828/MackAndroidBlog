package com.mackwu.component.util;

import java.util.concurrent.CountDownLatch;

/**
 * @author MackWu
 * @since 2022/11/7 17:53
 * 异步任务转化为同步任务
 */
public class AsyncToSyncTask {

    CountDownLatch countDownLatch = new CountDownLatch(1);

    public void start(Runnable runnable) {
        new Thread(() -> {
            runnable.run();
            countDownLatch.countDown();
        }).start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
