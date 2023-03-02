package com.mackwu.base.livedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.jeremyliao.liveeventbus.LiveEventBus;

/**
 * @author MackWu
 * @since 2020/9/22 19:57
 */
public class BaseLiveData<T> implements ILiveData<T> {

    private final String key;
    private final Class<T> tClass;

    public BaseLiveData(String key, Class<T> tClass) {
        this.key = key;
        this.tClass = tClass;
    }

    @Override
    public void post(T value) {
        LiveEventBus.get(key, tClass).post(value);
    }

    @Override
    public void postAcrossProcess(T value) {
        LiveEventBus.get(key, tClass).postAcrossProcess(value);
    }

    @Override
    public void postAcrossApp(T value) {
        LiveEventBus.get(key, tClass).postAcrossApp(value);
    }

    @Override
    public void postDelay(T value, long delay) {
        LiveEventBus.get(key, tClass).postDelay(value, delay);
    }

    @Override
    public void postDelay(LifecycleOwner sender, T value, long delay) {
        LiveEventBus.get(key, tClass).postDelay(sender, value, delay);
    }

    @Override
    public void postOrderly(T value) {
        LiveEventBus.get(key, tClass).postOrderly(value);
    }

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
        LiveEventBus.get(key, tClass).observe(owner, observer);
    }

    @Override
    public void observeSticky(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
        LiveEventBus.get(key, tClass).observeSticky(owner, observer);
    }

    @Override
    public void observeForever(@NonNull Observer<T> observer) {
        LiveEventBus.get(key, tClass).observeForever(observer);
    }

    @Override
    public void observeStickyForever(@NonNull Observer<T> observer) {
        LiveEventBus.get(key, tClass).observeStickyForever(observer);
    }

    @Override
    public void removeObserver(@NonNull Observer<T> observer) {
        LiveEventBus.get(key, tClass).removeObserver(observer);
    }

    @Override
    public void autoClear() {
        LiveEventBus.config(key).autoClear(true);
    }

}
