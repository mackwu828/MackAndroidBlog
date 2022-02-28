package com.mackwu.component.ui.widget;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.recycler.GridSpaceItemDecoration;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.bean.RecycleItem;
import com.mackwu.component.databinding.WidgetActivityRecyclerBinding;
import com.mackwu.component.ui.adapter.MyMultiAdapter;

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

    private MyMultiAdapter adapter;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        // adapter
        adapter = new MyMultiAdapter();
        adapter.setOnItemClickListener((a, view, position) -> {
        });

        // recyclerView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        binding.recyclerView.setLayoutManager(gridLayoutManager);
        GridSpaceItemDecoration gridSpaceItemDecoration = new GridSpaceItemDecoration(getResources().getDimensionPixelOffset(R.dimen.dp_20));
        binding.recyclerView.addItemDecoration(gridSpaceItemDecoration);
        binding.recyclerView.setAdapter(adapter);

        //
        binding.recyclerView.addOnScrollListener(binding.fastScroller.getOnScrollListener());
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        List<RecycleItem> data = new ArrayList<>();
        //
        data.add(new RecycleItem(RecycleItem.ItemType.DATE, "2022-1-11"));
        data.add(new RecycleItem("2022-1-11", R.drawable.home_bg_1));
        data.add(new RecycleItem("2022-1-11", R.drawable.bg_picture_1));
        //
        data.add(new RecycleItem(RecycleItem.ItemType.DATE, "2022-1-12"));
        data.add(new RecycleItem("2022-1-12", R.drawable.home_bg_1));
        data.add(new RecycleItem("2022-1-12", R.drawable.home_bg_1));
        data.add(new RecycleItem("2022-1-12", R.drawable.home_bg_2));
//        data.add(new RecycleItem("2022-1-12", R.drawable.home_bg_3));
//        data.add(new RecycleItem("2022-1-12", R.drawable.bg_picture_1));
        //
        data.add(new RecycleItem(RecycleItem.ItemType.DATE, "2022-1-13"));
        data.add(new RecycleItem("2022-1-13", R.drawable.home_bg_1));
        //
        data.add(new RecycleItem(RecycleItem.ItemType.DATE, "2022-1-14"));
        data.add(new RecycleItem("2022-1-14", R.drawable.home_bg_1));
        // 2-21
        data.add(new RecycleItem(RecycleItem.ItemType.DATE, "2022-2-21"));
        data.add(new RecycleItem("2022-2-21", R.drawable.home_bg_3));
        data.add(new RecycleItem("2022-2-21", R.drawable.home_bg_2));
        // 2-22
        data.add(new RecycleItem(RecycleItem.ItemType.DATE, "2022-2-22"));
        data.add(new RecycleItem("2022-2-22", R.drawable.home_bg_1));
        data.add(new RecycleItem("2022-2-22", R.drawable.home_bg_2));
        data.add(new RecycleItem("2022-2-22", R.drawable.home_bg_3));
        data.add(new RecycleItem("2022-2-22", R.drawable.home_bg_2));
//        data.add(new RecycleItem("2022-2-22", R.drawable.bg_picture_1));
//        data.add(new RecycleItem("2022-2-22", R.drawable.home_bg_3));
        // 2-23
        data.add(new RecycleItem(RecycleItem.ItemType.DATE, "2022-2-23"));
        data.add(new RecycleItem("2022-2-23", R.drawable.home_bg_1));
        data.add(new RecycleItem("2022-2-23", R.drawable.home_bg_2));
        data.add(new RecycleItem("2022-2-23", R.drawable.home_bg_3));
        data.add(new RecycleItem("2022-2-23", R.drawable.home_bg_3));
//        data.add(new RecycleItem("2022-2-23", R.drawable.home_bg_3));
//        data.add(new RecycleItem("2022-2-23", R.drawable.home_bg_3));
        // 2-24
        data.add(new RecycleItem(RecycleItem.ItemType.DATE, "2022-2-24"));
        data.add(new RecycleItem("2022-2-24", R.drawable.home_bg_2));
        data.add(new RecycleItem("2022-2-24", R.drawable.home_bg_2));
        // 2-25
        data.add(new RecycleItem(RecycleItem.ItemType.DATE, "2022-2-25"));
        data.add(new RecycleItem("2022-2-25", R.drawable.bg_picture_1));
        data.add(new RecycleItem("2022-2-25", R.drawable.bg_picture_1));
        adapter.setList(data);
    }

}
