package com.mackwu.component.ui.window;

/**
 * ===================================================
 * Created by MackWu on 2021/1/4 10:54
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public interface IWindow {

    /**
     * 获取布局id
     */
    int getLayoutId();

    /**
     * 初始化view
     */
    void initView();

    /**
     * 显示弹窗。
     */
    void show();

    /**
     * 显示弹窗。
     *
     * @param width       宽度。
     * @param height      高度。
     * @param type        类型。
     * @param isFocusable 是否获取焦点。
     */
    void show(int width, int height, int type, boolean isFocusable);

    void showAsApp(int width, int height, boolean isFocusable);

    void showAsSystem(int width, int height, boolean isFocusable);

    /**
     * 隐藏弹窗。
     */
    void hide();
}
