package com.mackwu.java_kt.kt.generic.java;

/**
 * ===================================================
 * Created by MackWu on 2020/4/21 10:45
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class GenericInterface<T> implements IGenericInterface<T> {

    private T data;

    @Override
    public void setData(final T data) {
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }
}
