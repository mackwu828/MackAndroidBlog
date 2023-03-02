package com.mackwu.component.ui.view.focus;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;

/**
 * @author MackWu
 * @since 2022/11/8 17:08
 */
public class FocusProxy {

    View view;
    FocusEffect focusEffect;

    public FocusProxy(@NonNull View view, @NonNull FocusEffect focusEffect) {
        this.view = view;
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        setFocusEffect(focusEffect);
    }

    private void setFocusEffect(FocusEffect focusEffect) {
        this.focusEffect = focusEffect;
        if (focusEffect.getBackgroundResource() != 0) {
            view.setBackgroundResource(focusEffect.getBackgroundResource());
        }
        if (focusEffect.getPadding() != 0) {
            int padding = focusEffect.getPadding();
            view.setPadding(padding, padding, padding, padding);
        }
    }

    public void onFocusChanged(View view, boolean gainFocus) {
        ViewCompat.setElevation(view, gainFocus ? 1 : 0);
        if (focusEffect != null) {
            focusEffect.run(view, gainFocus);
        }
    }

}
