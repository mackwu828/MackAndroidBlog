package com.mackwu.component;

import com.mackwu.component.util.SwMaker;

import org.junit.Test;

/**
 * @author MackWu
 * @since 2023/6/28 16:19
 */
public class SwMakeTest {

    @Test
    public void makeAllDimens() {
        SwMaker swMaker = new SwMaker.Builder()
                .designWidth(1080)
                .build();
        swMaker.makeAllDimens();
    }
}
