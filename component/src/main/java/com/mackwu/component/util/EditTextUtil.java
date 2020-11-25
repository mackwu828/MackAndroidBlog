package com.mackwu.component.util;

import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public final class EditTextUtil {

    /**
     * Enter按键是Search时。
     */
    public static void setOnEditorSearchActionListener(EditText editText) {
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // v.getText().toString().trim()
            }
            return false;
        });
    }

    /**
     * 显示软键盘。
     */
    public static void showSoftInput(Context context, EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
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

    /**
     * 切换软键盘。如果软键盘没有显示，则显示；如果已经显示，则隐藏。
     */
    public static void toggleSoftInput(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 显示密码
     */
    public static void showPassword(EditText editText){
        editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    }

    /**
     * 隐藏密码
     */
    public static void hidePassword(EditText editText){
        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

}
