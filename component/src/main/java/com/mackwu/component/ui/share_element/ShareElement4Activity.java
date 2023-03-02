package com.mackwu.component.ui.share_element;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.BaseTransactionActivity;
import com.mackwu.component.R;
import com.mackwu.component.databinding.ActivityShareElement4Binding;
import com.mackwu.component.ui.viewmodel.RecyclerViewModel;

/**
 * @author MackWu
 * @since 2022/12/5 14:35
 */
public class ShareElement4Activity extends BaseTransactionActivity<RecyclerViewModel, ActivityShareElement4Binding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, ShareElementFragment.newInstance())
                .commit();

        binding.btnTest.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, ShareElementFragment.newInstance())
                    .commit();
        });
    }

}
