package com.mackwu.orr.rx;

/**
 * ===================================================
 * Created by MackWu on 2020/6/28 19:19
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class BaseResponse<T> {

    private T data;
    private String message;
    private int error;

    public T getData() {
        return data;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public int getError() {
        return error;
    }

    public void setError(final int error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", error=" + error +
                '}';
    }
}
