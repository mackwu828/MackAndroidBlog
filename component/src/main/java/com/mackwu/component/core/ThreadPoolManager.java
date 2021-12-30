package com.mackwu.component.core;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ===================================================
 * Created by MackWu on 2020/12/2 18:47
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ThreadPoolManager {

    private static ThreadPoolManager instance;
    // the maximum number of processors available to the virtual machine
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    public static final long KEEP_ALIVE_TIME = 60 * 1000L;
    private final ExecutorService service = new ThreadPoolExecutor(CORE_POOL_SIZE, CORE_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    private ThreadPoolManager() {
//        LogUtil.d("CPU_COUNT: " + CPU_COUNT + ", CORE_POOL_SIZE: " + CORE_POOL_SIZE);
    }

    public static ThreadPoolManager getInstance() {
        if (instance == null) {
            instance = new ThreadPoolManager();
        }
        return instance;
    }

    public void submit(Runnable runnable) {
        service.submit(runnable);
    }

    public void submitAwait(Runnable runnable) {
        service.submit(() -> {
            runnable.run();
            countDownLatch.countDown();
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
