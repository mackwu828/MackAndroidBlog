package com.mackwu.component.bean;

import android.annotation.TargetApi;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;

/**
 * ===================================================
 * Created by MackWu on 2021/9/24 15:53
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class AuthState {
    private @State int state;
    private int auth;

    public AuthState(int state) {
        this.state = state;
    }

    @IntDef({State.IDLE, State.AUTH})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
        int IDLE = 0;
        int AUTH = 1;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateName() {
        Field[] declaredFields = State.class.getDeclaredFields();
        return declaredFields[state].getName();
    }

}


