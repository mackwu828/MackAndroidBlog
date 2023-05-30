package com.mackwu.component.func;

import java.util.concurrent.CountDownLatch;
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
    // CPU数量
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    // 核心线程数量。和CPU数量有关。
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    // 最大线程数量。任务数量超过核心线程数，就会将任务放到队列中，队列满了，就会启动非核心线程执行任务，线程数超过这个限制就会走拒绝策略。
    private static final int MAXIMUM_POOL_SIZE = CORE_POOL_SIZE;
    // 空闲线程存活时间
    public static final long KEEP_ALIVE_TIME = 3 * 1000;
    // 线程池
    private final ThreadPoolExecutor executor;
    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    private ThreadPoolManager() {
        executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        executor.allowCoreThreadTimeOut(true);
    }

    public static ThreadPoolManager getInstance() {
        if (instance == null) {
            instance = new ThreadPoolManager();
        }
        return instance;
    }

    public void submit(Runnable runnable) {
        executor.submit(runnable);
    }

    public void submitAwait(Runnable runnable) {
        executor.submit(() -> {
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
