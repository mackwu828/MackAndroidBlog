package com.mackwu.component.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.BaseTransactionActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.databinding.ActivityFragmentBinding;
import com.mackwu.component.ui.fragment.GreenFragment;
import com.mackwu.component.ui.fragment.RedFragment;

/**
 * @author MackWu
 * @since 2022/12/6 18:49
 */
public class FragmentActivity extends BaseTransactionActivity<BaseViewModel, ActivityFragmentBinding> {

    RedFragment redFragment;
    GreenFragment greenFragment;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        binding.btnTest.setOnClickListener(v -> {
            replaceRedFragment();
        });
        binding.btnTest2.setOnClickListener(v -> {
            replaceGreenFragment();
        });
        binding.btnTest3.setOnClickListener(v -> {
        });
    }

    public void showRedFragment(){
        showFragment(R.id.fl_container, RedFragment.class);
    }

    public void showGreenFragment(){
        showFragment(R.id.fl_container, RedFragment.class);
    }

    public void replaceRedFragment() {
        if (redFragment == null) {
            redFragment = new RedFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, redFragment)
                .commit();
    }

    public void replaceGreenFragment() {
        if (greenFragment == null) {
            greenFragment = new GreenFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, greenFragment)
                .commit();
    }

}
