package com.mackwu.http.rxjava;

/**
 * ===================================================
 * Created by MackWu on 2020/6/29 19:25
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public interface IOnResult<T>{

    /**
     * 成功
     */
    void onSuccess(T t, String successMsg);

    /**
     * 失败
     */
    void onFail(String errorMsg);
}
