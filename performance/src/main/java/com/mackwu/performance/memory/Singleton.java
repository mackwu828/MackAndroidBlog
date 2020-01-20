package com.mackwu.performance.memory;

import android.content.Context;

/**
 * ===================================================
 * Created by MackWu on 2020/1/15 14:12
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class Singleton {

    private static Singleton singleton;
    private Context context;
    private Singleton(Context context) {
        this.context = context;
    }
    public Singleton() {
    }

    public static Singleton getInstance(Context context){
        if (null == singleton) {
            synchronized (Singleton.class) {
                if (null == singleton) {
                    singleton = new Singleton(context);
                }
            }
        }
        return singleton;
    }
}
