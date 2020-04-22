package com.mackwu.kt.generic.java;

/**
 * ===================================================
 * Created by MackWu on 2020/4/21 10:42
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class GenericClass<T> {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(final T data) {
        this.data = data;
    }
}
