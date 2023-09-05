package com.mackwu.component.ui.recycler;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mackwu.base.BaseActivity;
import com.mackwu.component.R;
import com.mackwu.component.bean.RecycleItem;
import com.mackwu.component.databinding.ActivityRecyclerViewBinding;
import com.mackwu.component.func.decoration.DividerItemDecoration;
import com.mackwu.component.ui.view.QuickViewHolder;
import com.mackwu.component.ui.viewmodel.RecyclerViewModel;

/**
 * @author MackWu
 * @since 2022/12/5 15:30
 */
public class RecyclerViewActivity extends BaseActivity<RecyclerViewModel, ActivityRecyclerViewBinding> {

    MyAdapter adapter;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        // adapter
        adapter = new MyAdapter();
        adapter.setOnItemClickListener((a, view, position) -> {

        });
        adapter.setList(viewModel.getData());

        // recyclerView
        DividerItemDecoration.Divider divider = new DividerItemDecoration.Divider();
        divider.setOrientation(RecyclerView.HORIZONTAL);
        divider.setWidth(getResources().getDimensionPixelSize(R.dimen.dp_4));
        divider.setColor(Color.parseColor("#14CAFF"));
        divider.setLastVisible(false);
        divider.setPaddingTop(getResources().getDimensionPixelSize(R.dimen.dp_20));
        divider.setPaddingBottom(getResources().getDimensionPixelSize(R.dimen.dp_14));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(divider);

        binding.recyclerView.addItemDecoration(dividerItemDecoration);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 7);
        binding.recyclerView.setLayoutManager(gridLayoutManager);
        binding.recyclerView.setAdapter(adapter);
    }

    private static class MyAdapter extends BaseQuickAdapter<RecycleItem, QuickViewHolder> {

        public MyAdapter() {
            super(R.layout.item_test_1);
        }

        @Override
        protected void convert(@NonNull QuickViewHolder viewHolder, RecycleItem recycleItem) {
        }
    }

}
