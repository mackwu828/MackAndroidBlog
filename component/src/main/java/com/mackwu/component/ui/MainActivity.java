package com.mackwu.component.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.ActivityStartUtil;
import com.mackwu.base.util.Logger;
import com.mackwu.component.R;
import com.mackwu.component.databinding.ActivityMainBinding;
import com.mackwu.component.func.task.TaskDispatcher;
import com.mackwu.component.ui.recycler.RecyclerViewActivity;
import com.mackwu.component.ui.viewmodel.MainViewModel;

/**
 * @author MackWu
 * @since 2020/6/19 23:34
 */
public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        ActivityStartUtil.startActivity(this, HttpActivity.class);
//        ActivityStartUtil.startActivity(this, StickyHeaderActivity.class);
//        ActivityStartUtil.startActivity(this, VerticalGridViewActivity.class);
//        ActivityStartUtil.startActivity(this, TimelineGalleryActivity.class);

        binding.btnTest.setOnClickListener(v -> {
        });
        binding.btnTest2.setOnClickListener(v -> {
        });
    }

}
