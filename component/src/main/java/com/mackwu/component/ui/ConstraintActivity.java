package com.mackwu.component.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.databinding.WidgetActivityConstraintBinding;

/**
 * ===================================================
 * Created by MackWu on 2022/3/29 10:34
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ConstraintActivity extends BaseActivity<BaseViewModel, WidgetActivityConstraintBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    public void constraintGift() {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) binding.tvTest.getLayoutParams();
        layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.endToEnd = ConstraintLayout.LayoutParams.UNSET;
        layoutParams.leftMargin = getResources().getDimensionPixelOffset(R.dimen.dp_40);
        binding.tvTest.setLayoutParams(layoutParams);
    }
}
