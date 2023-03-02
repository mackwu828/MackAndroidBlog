package com.mackwu.component.jbase.pattern.singleton;

/**
 * @author MackWu
 * @since 2022/6/28 16:23
 */
public class Singleton2 {

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final Singleton2 INSTANCE = new Singleton2();
    }

}
