package com.mackwu.image.rotate;

/**
 * @author MackWu
 * @since 2023/4/19 18:35
 */
public interface RotateListener {

    void onRotateSuccess();

    void onRotateFailed(int error, String message);
}
