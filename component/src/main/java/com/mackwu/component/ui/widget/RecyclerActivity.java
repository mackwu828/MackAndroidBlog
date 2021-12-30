package com.mackwu.component.ui.widget;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.LogUtil;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.databinding.WidgetActivityRecyclerBinding;
import com.mackwu.component.util.DoubleClickUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2021/11/16 16:00
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class RecyclerActivity extends BaseActivity<BaseViewModel, WidgetActivityRecyclerBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        List<String> data = new ArrayList<>();
        data.add("111");
        data.add("222");
        data.add("333");

        // adapter
        MyAdapter adapter = new MyAdapter();
        adapter.setList(data);
        adapter.setOnItemClickListener((a, view, position) -> {
            LogUtil.d("position: " + position);
        });

        // recyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }

    private static class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public MyAdapter() {
            super(R.layout.layout_item);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, String s) {
            helper.setText(R.id.tv_test, s);
        }
    }

}
