package com.mackwu.component.util;

import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public final class SoftInputUtil {

    /**
     * 显示软键盘。
     */
    public static void showSoftInput(Context context, EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            inputMethodManager.showSoftInput(editText, 0);
        }
    }

    /**
     * 隐藏软键盘。
     */
    public static void hideSoftInput(Context context, EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

}
