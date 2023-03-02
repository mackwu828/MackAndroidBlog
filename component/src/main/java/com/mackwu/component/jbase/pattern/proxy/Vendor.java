package com.mackwu.component.jbase.pattern.proxy;

/**
 * @author MackWu
 * @since 2022/7/5 11:03
 */
public class Vendor implements Sell{

    private static Vendor instance;
    private boolean isInit;

    private Vendor() {
    }

    public static Vendor getInstance() {
        if (instance == null) {
            instance = new Vendor();
        }
        return instance;
    }

    public void init() {
        isInit = true;
    }

    public boolean isInit() {
        return isInit;
    }

    @Override
    public void sell() {
        System.out.println("In sell method");
    }

    @Override
    public void ad() {
        System.out.println("In ad method");
    }

}
