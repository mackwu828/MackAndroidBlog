package com.mackwu.base.window;

/**
 * @author MackWu
 * @since 2020/8/3 19:24
 */
public interface Window {

    /**
     * 显示弹窗。
     */
    void show();

    /**
     * 显示弹窗后自动隐藏。
     *
     * @param delayMillis 延时时间，时间到后隐藏。单位毫秒。
     */
    void showAutoHide(long delayMillis);

    /**
     * 隐藏弹窗。
     */
    void hide();

    /**
     * 移除弹窗。
     */
    void remove();

}
