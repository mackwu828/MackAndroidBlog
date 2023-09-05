package com.mackwu.component.ui;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.Logger;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.WidgetActivityEditTextBinding;
import com.mackwu.component.util.EditTextUtil;

/**
 * ===================================================
 * Created by MackWu on 2021/1/15 17:27
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class EditTextActivity extends BaseActivity<BaseViewModel, WidgetActivityEditTextBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        // setOnEditorActionListener
        binding.etTest.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String text = binding.etTest.getText().toString();
                Logger.d("text=" + text);
                return true;
            }
            return false;
        });

        binding.btnShowSoftInput.setOnClickListener(v -> EditTextUtil.showSoftInput(binding.etTest));
        binding.btnHideSoftInput.setOnClickListener(v -> EditTextUtil.hideSoftInput( binding.etTest));
    }

}
