package com.mackwu.component;


import com.mackwu.component.bean.AuthState;

import java.lang.reflect.Field;

/**
 * ===================================================
 * Created by MackWu on 2020/11/12 14:37
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class JavaTest {


    public static void main(String[] args) {
        AuthState authState = new AuthState(AuthState.State.IDLE);
        System.out.println(authState.getState());
        System.out.println(authState.getStateName());
    }

}
