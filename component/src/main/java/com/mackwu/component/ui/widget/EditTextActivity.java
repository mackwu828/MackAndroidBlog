package com.mackwu.component.ui.widget;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
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
//        binding.etTest.requestFocus();

        binding.etTest.setTransformationMethod(new MyPasswordTransformationMethod());
        binding.etTest.setLetterSpacing(0.5f);

        binding.btnShowSoftInput.setOnClickListener(v -> EditTextUtil.showSoftInput(this, binding.etTest));
        binding.btnHideSoftInput.setOnClickListener(v -> EditTextUtil.hideSoftInput(this, binding.etTest));
    }

}
