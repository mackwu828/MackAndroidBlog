package com.mackwu.component.core.pattern.decorator;

/**
 * ===================================================
 * Created by MackWu on 2021/2/24 15:20
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 *
 */
public class ConcreteComponent implements Component {
    @Override
    public void operate() {
        System.out.println("do Something");
    }
}
