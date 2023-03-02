package com.mackwu.component.jbase.pattern.singleton;

/**
 * @author MackWu
 * @since 2022/6/28 16:23
 */
public class Singleton3 {

    private static Singleton3 instance;

    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }

}
