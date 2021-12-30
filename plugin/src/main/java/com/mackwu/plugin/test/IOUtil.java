package com.mackwu.plugin.test;

import java.io.Closeable;
import java.io.IOException;

/**
 * ===================================================
 * Created by MackWu on 2021/11/3 16:28
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class IOUtil {

    /**
     * 关闭IO流
     */
    public static void close(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (null != closeable) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
