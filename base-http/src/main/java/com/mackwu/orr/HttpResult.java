package com.mackwu.orr;

import com.mackwu.orr.error.ErrorType;

/**
 * @author MackWu
 * @since 2020/9/7 18:37
 */
public interface HttpResult<T>{

    /**
     * 成功
     */
    void onSuccess(T t, String successMsg);

    /**
     * 失败
     */
    void onFail(ErrorType errorType, String errorMsg);
}
