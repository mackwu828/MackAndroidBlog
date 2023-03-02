package com.mackwu.component.jbase.pattern.factory;

import com.mackwu.component.jbase.pattern.singleton.Singleton1;

import java.lang.reflect.Constructor;

/**
 * @author MackWu
 * @since 2022/6/28 16:15
 */
public class SingletonFactory {

    private static Singleton1 instance;

    private SingletonFactory() {
    }

    static {
        try {
            Class<?> cl = Class.forName(Singleton1.class.getName());
            Constructor<?> constructor = cl.getDeclaredConstructor();
            constructor.setAccessible(true);
            instance = (Singleton1) constructor.newInstance();
        } catch (Exception e) {
        }
    }

    public static Singleton1 getInstance() {
        return instance;
    }

}
