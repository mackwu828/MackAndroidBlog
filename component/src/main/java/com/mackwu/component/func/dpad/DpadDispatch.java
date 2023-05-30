package com.mackwu.component.func.dpad;

import android.view.KeyEvent;

/**
 * @author MackWu
 * @since 2023/2/28 14:24
 */
public interface DpadDispatch {

    boolean dispatchKeyEvent(KeyEvent event);

    void setColumns(int columns);

    void setOnDpadToBottomListener(OnDpadToBottomListener onDpadToBottomListener);

    void setOnDpadToTopListener(OnDpadToTopListener onDpadToTopListener);

    void setInterceptLastRowDpadDown(boolean interceptLastRowDpadDown);

}
