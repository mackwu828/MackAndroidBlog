package com.mackwu.component.jbase;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author MackWu
 * @since 2023/4/27 16:57
 */
public class ThreadTest {



    public void a() {
        // 创建线程池
        // corePoolSize: 线程池核心线程数。当任务提交时，如果当前线程池的线程数小于核心线程数，会创建新的线程来执行任务。
        // maximumPoolSize: 线程池最大线程数。当线程池线程数大于核心线程数，且任务队列已满，会创建新的线程来执行任务，但是线程数量要小于最大线程数。
        // keepAliveTime: 非核心线程空闲时间。非核心线程就是当workQueue已满时，再提交任务时创建的线程，因为不是核心线程，超过空闲时间会被回收。
        // unit: 非核心线程空闲时间单位。
        // workQueue: 任务队列。阻塞队列，用来存储来不及执行的任务。当任务提交时，线程池的线程数大于核心线程数时，任务会先被放在该队列中。
        // threadFactory: 创建线程的工程。统一处理线程的属性。
        // handler: 拒绝策略，当任务队列已满，且线程数量大于最大线程数量时，再提交任务时会执行对应的拒绝策略。
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 10, 60,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(20));
        // 执行任务
        executor.execute(() -> {

        });
        // 关闭线程池。阻止新任务提交，但是不影响已提交的任务。
        executor.shutdown();
        // 关闭线程池。阻止新任务提交，且会中断已提交的任务。
        executor.shutdownNow();

        // 创建单线程的线程池
        ExecutorService singleExecutor = Executors.newSingleThreadExecutor();
        // 创建固定线程数的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        // 创建可重用固定线程数的线程池
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    }

}
