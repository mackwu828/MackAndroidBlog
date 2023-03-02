package com.mackwu.component.bean;

/**
 * @author MackWu
 * @since 2022/7/28 17:39
 */
public class State {

    /**
     * 1=00000001
     * 1 << 1 00000010
     * 1 << 2 00000100
     * 1 << 3 00001000
     */
    public static final int a = 1 << 1;
    public static final int b = 1 << 2;
    public static final int c = 1 << 3;

    /**
     * 两个值相与，判断是否相等。
     * 如果结果不为0，则两个值相等(在自身不为0的情况)。
     * @param state
     */
    public static boolean isStateB(int state) {
        return (state & b) != 0;
    }

    public static void main(String[] args) {
        // &与：都为1才为1
        // 00000000
        System.out.println(a & b);

        // |或：都为0才为0
        // 00000110
        System.out.println(a | b);

        System.out.println(isStateB(1 << 2));
    }

}
