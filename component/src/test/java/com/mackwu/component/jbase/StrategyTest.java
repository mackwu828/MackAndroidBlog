package com.mackwu.component.jbase;

import com.mackwu.component.jbase.pattern.strategy.AStrategy;
import com.mackwu.component.jbase.pattern.strategy.BStrategy;
import com.mackwu.component.jbase.pattern.strategy.IStrategy;
import com.mackwu.component.jbase.pattern.strategy.StrategyContext;

/**
 * @author MackWu
 * @since 2023/6/21 10:41
 */
public class StrategyTest {

    public static void main(String[] args) {

        IStrategy strategy = new AStrategy();
        StrategyContext strategyContext = new StrategyContext(strategy);
        strategyContext.handle();

        strategy = new BStrategy();
        strategyContext = new StrategyContext(strategy);
        strategyContext.handle();
    }
}
