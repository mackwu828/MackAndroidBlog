package com.mackwu.component.ui.widget;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.WidgetActivityDialogBinding;
import com.mackwu.component.ui.dialog.LoadingDialog;

/**
 * ===================================================
 * Created by MackWu on 2021/4/22 15:15
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class DialogActivity extends BaseActivity<BaseViewModel, WidgetActivityDialogBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        LoadingDialog loadingDialog = new LoadingDialog();
        binding.btnShow.setOnClickListener(v -> loadingDialog.show(this));
        binding.btnHide.setOnClickListener(v -> loadingDialog.dismissAllowingStateLoss());
    }

}
