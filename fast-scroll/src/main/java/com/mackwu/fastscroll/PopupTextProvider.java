package com.mackwu.fastscroll;

import androidx.annotation.NonNull;

/**
 * @author MackWu
 * @since 2023/2/28 17:59
 */
public interface PopupTextProvider {

    @NonNull
    String getPopupText(int position);
}
