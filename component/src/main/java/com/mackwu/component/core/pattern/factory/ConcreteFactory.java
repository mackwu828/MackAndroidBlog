package com.mackwu.component.core.pattern.factory;

import java.lang.reflect.Constructor;

/**
 * ===================================================
 * Created by MackWu on 2021/4/6 11:38
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ConcreteFactory extends Factory {

    @Override
    public <T extends Product> T createProduct(Class<T> c) {
        T product = null;
        try {
            Constructor<T> constructor = c.getConstructor();
            product = constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }
}
