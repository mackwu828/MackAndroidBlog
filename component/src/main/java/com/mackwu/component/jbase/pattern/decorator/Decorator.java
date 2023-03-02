package com.mackwu.component.jbase.pattern.decorator;

/**
 * ===================================================
 * Created by MackWu on 2021/2/24 15:21
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class Decorator implements Component {

    private final Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operate() {
        component.operate();
    }
}
