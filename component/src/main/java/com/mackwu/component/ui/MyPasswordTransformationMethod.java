package com.mackwu.component.ui;

import android.text.method.PasswordTransformationMethod;
import android.view.View;

import org.jetbrains.annotations.NotNull;

/**
 * ===================================================
 * Created by MackWu on 2021/5/19 17:11
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class MyPasswordTransformationMethod extends PasswordTransformationMethod {

    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        return new PasswordCharSequence(source);
    }

    private static class PasswordCharSequence implements CharSequence {
        private final CharSequence source;

        public PasswordCharSequence(CharSequence source) {
            this.source = source;
        }

        public char charAt(int index) {
            return '●';
        }

        public int length() {
            return source.length();
        }

        @NotNull
        public CharSequence subSequence(int start, int end) {
            return source.subSequence(start, end);
        }
    }
};

