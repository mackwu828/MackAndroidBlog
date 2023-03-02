package com.mackwu.component.ui.share_element;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityShareElementBinding;

/**
 * @author MackWu
 * @since 2022/12/5 14:35
 */
public class ShareElementActivity extends BaseActivity<BaseViewModel, ActivityShareElementBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        binding.btnTest.setOnClickListener(v -> {
            Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this, binding.ivTest, "shareElement").toBundle();
            Intent intent = new Intent(this, ShareElement2Activity.class);
            startActivity(intent, bundle);
        });
    }

}
