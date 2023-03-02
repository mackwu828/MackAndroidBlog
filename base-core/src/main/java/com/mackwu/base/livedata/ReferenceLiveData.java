package com.mackwu.base.livedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.jeremyliao.liveeventbus.core.LiveEvent;

/**
 * @author MackWu
 * @since 2020/9/22 19:46
 */
public class ReferenceLiveData<T extends LiveEvent> implements ILiveData<T> {

    private final Class<T> tClass;

    public ReferenceLiveData(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public void post(T value) {
        LiveEventBus.get(tClass).post(value);
    }

    @Override
    public void postAcrossProcess(T value) {
        LiveEventBus.get(tClass).postAcrossProcess(value);
    }

    @Override
    public void postAcrossApp(T value) {
        LiveEventBus.get(tClass).postAcrossApp(value);
    }

    @Override
    public void postDelay(T value, long delay) {
        LiveEventBus.get(tClass).postDelay(value, delay);
    }

    @Override
    public void postDelay(LifecycleOwner sender, T value, long delay) {
        LiveEventBus.get(tClass).postDelay(sender, value, delay);
    }

    @Override
    public void postOrderly(T value) {
        LiveEventBus.get(tClass).postOrderly(value);
    }

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
        LiveEventBus.get(tClass).observe(owner, observer);
    }

    @Override
    public void observeSticky(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
        LiveEventBus.get(tClass).observeSticky(owner, observer);
    }

    @Override
    public void observeForever(@NonNull Observer<T> observer) {
        LiveEventBus.get(tClass).observeForever(observer);
    }

    @Override
    public void observeStickyForever(@NonNull Observer<T> observer) {
        LiveEventBus.get(tClass).observeStickyForever(observer);
    }

    @Override
    public void removeObserver(@NonNull Observer<T> observer) {
        LiveEventBus.get(tClass).removeObserver(observer);
    }

    @Override
    public void autoClear() {
        LiveEventBus.config(tClass.getName()).autoClear(true);
    }

}
