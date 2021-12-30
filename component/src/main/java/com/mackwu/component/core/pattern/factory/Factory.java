package com.mackwu.component.core.pattern.factory;

/**
 * ===================================================
 * Created by MackWu on 2021/4/6 11:34
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public abstract class Factory {

    public abstract <T extends Product> T createProduct(Class<T> c);

}
