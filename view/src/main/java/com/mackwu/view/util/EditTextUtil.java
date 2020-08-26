package com.mackwu.view.util;

import android.content.Context;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public final class EditTextUtil {

    /**
     * edit text action 内容监听。
     */
    public interface OnEditorActionResultListener {
        void onEditActionResult(String text);
    }

    /**
     * Enter按键是Search时。
     */
    public static void setOnEditorSearchActionListener(EditText editText, OnEditorActionResultListener listener) {
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                listener.onEditActionResult(v.getText().toString().trim());
            }
            return false;
        });
    }

    /**
     * Enter按键是Done时。
     */
    public static void setOnEditorDoneActionListener(EditText editText, OnEditorActionResultListener listener) {
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                listener.onEditActionResult(v.getText().toString().trim());
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

}
