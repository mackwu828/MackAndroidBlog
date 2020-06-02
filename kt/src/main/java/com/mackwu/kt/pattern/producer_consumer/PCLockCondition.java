package com.mackwu.kt.pattern.producer_consumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ===================================================
 * Created by MackWu on 2020/4/23 17:12
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class PCLockCondition implements IProducerConsumer {

    private static final int MAX_LEN = 10;
    private static final Queue<Integer> queue = new LinkedList<>();
    private static boolean flag = true;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    @Override
    public void produce() {
        while (flag) {
            lock.lock();
            try {
                while (queue.size() == MAX_LEN) {
                    System.out.println("生产者生产任务... 当前队列已满");
                    condition.await();
                }
                queue.add(1);
                condition.signal();
                System.out.println("生产者生产一个任务，当前队列长度为: " + queue.size());
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    @Override
    public void consume() {
        while (flag) {
            lock.lock();
            try {
                while (queue.size() == 0) {
                    System.out.println("消费者消费任务... 当前队列为空");
                    condition.await();
                }
                queue.poll();
                condition.signal();
                System.out.println("消费者消费一个任务，当前队列长度为: " + queue.size());
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    @Override
    public void stop() {
        flag = false;
    }

    public static void main(String[] args) {
        PCLockCondition pcLockCondition = new PCLockCondition();
        new Thread(pcLockCondition::produce).start();
        new Thread(pcLockCondition::consume).start();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pcLockCondition.stop();
    }
}
