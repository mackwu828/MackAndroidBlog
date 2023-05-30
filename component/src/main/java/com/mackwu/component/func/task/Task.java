package com.mackwu.component.func.task;

/**
 * @author MackWu
 * @since 2023/4/10 15:38
 */
public interface Task {

    void doInBackground();

    /**
     * {@link #doInBackground}后执行，在主线程执行。
     */
    default void onSuccess() {

    }

}
