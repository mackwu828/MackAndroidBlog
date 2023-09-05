package com.mackwu.component.jbase.pattern.decorator;

/**
 * @author MackWu
 * @since 2023/8/28 14:23
 */
public class Decorator implements Component {

    private final Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void funA() {
        component.funA();
    }

}
