package com.mackwu.component.util;

import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author MackWu
 * @since 2023/6/26 15:10
 */
public class EditTextUtil {

    /**
     * 显示软键盘。
     */
    public static void showSoftInput(TextView v) {
        InputMethodManager inputMethodManager = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            inputMethodManager.showSoftInput(v, 0);
        }
    }

    /**
     * 隐藏软键盘。
     */
    public static void hideSoftInput(TextView v) {
        InputMethodManager inputMethodManager = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    /**
     * 显示密码
     */
    public static void showPassword(TextView v) {
        v.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    }

    /**
     * 隐藏密码
     */
    public static void hidePassword(TextView v) {
        v.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

}
