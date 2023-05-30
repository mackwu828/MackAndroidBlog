package com.mackwu.component.func.focus;

import android.content.Context;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.mackwu.component.R;

/**
 * @author MackWu
 * @since 2023/3/28 17:08
 */
public class FocusEffectFactory {

    public static void bindDefault(View view) {
        Context context = view.getContext();
        FocusEffect focusEffect = new FocusEffect.Builder()
                .focusedStroke(context.getResources().getDimensionPixelSize(R.dimen.dp_5), ContextCompat.getColor(context, R.color.blue))
                .defaultStroke(context.getResources().getDimensionPixelSize(R.dimen.dp_2), ContextCompat.getColor(context, R.color.grey))
                .build();
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.setBackground(focusEffect.getBackground());
        int padding = focusEffect.getPadding();
        view.setPadding(padding, padding, padding, padding);
    }

}
