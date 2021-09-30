package com.mackwu.component.rx.token;

/**
 * ===================================================
 * Created by MackWu on 2021/9/27 16:12
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class TokenHolder {

    private static TokenHolder instance;
    private String token;

    private TokenHolder() {
    }

    public static TokenHolder getInstance() {
        if (instance == null) {
            instance = new TokenHolder();
        }
        return instance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
