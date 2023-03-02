package com.mackwu.base.livedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

/**
 * @author MackWu
 * @since 2020/9/22 19:46
 */
public interface ILiveData<T> {

    /**
     * 进程内发送消息
     *
     * @param value 发送的消息
     */
    void post(T value);

    /**
     * App内发送消息，跨进程使用
     *
     * @param value 发送的消息
     */
    void postAcrossProcess(T value);

    /**
     * App之间发送消息
     *
     * @param value 发送的消息
     */
    void postAcrossApp(T value);

    /**
     * 进程内发送消息，延迟发送
     *
     * @param value 发送的消息
     * @param delay 延迟毫秒数
     */
    void postDelay(T value, long delay);

    /**
     * 进程内发送消息，延迟发送，带生命周期
     * 如果延时发送消息的时候sender处于非激活状态，消息取消发送
     *
     * @param sender 消息发送者
     * @param value  发送的消息
     * @param delay  延迟毫秒数
     */
    void postDelay(LifecycleOwner sender, T value, long delay);

    /**
     * 进程内发送消息
     * 强制接收到消息的顺序和发送顺序一致
     *
     * @param value 发送的消息
     */
    void postOrderly(T value);

    /**
     * 注册一个Observer，生命周期感知，自动取消订阅
     *
     * @param owner    LifecycleOwner
     * @param observer 观察者
     */
    void observe(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer);

    /**
     * 注册一个Observer，生命周期感知，自动取消订阅
     * 如果之前有消息发送，可以在注册时收到消息（消息同步）
     *
     * @param owner    LifecycleOwner
     * @param observer 观察者
     */
    void observeSticky(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer);

    /**
     * 注册一个Observer，需手动解除绑定
     *
     * @param observer 观察者
     */
    void observeForever(@NonNull Observer<T> observer);

    /**
     * 注册一个Observer，需手动解除绑定
     * 如果之前有消息发送，可以在注册时收到消息（消息同步）
     *
     * @param observer 观察者
     */
    void observeStickyForever(@NonNull Observer<T> observer);

    /**
     * 通过observeForever或observeStickyForever注册的，需要调用该方法取消订阅
     *
     * @param observer 观察者
     */
    void removeObserver(@NonNull Observer<T> observer);

    /**
     * clear livedata when no observer observe it false: not clear livedata unless app was killed
     */
    void autoClear();

}
