package com.mackwu.component.jbase.pattern.strategy;

/**
 * @author MackWu
 * @since 2023/6/21 10:40
 */
public class StrategyContext {

    IStrategy strategy;

    public StrategyContext(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void handle() {
        strategy.handle();
    }

}
