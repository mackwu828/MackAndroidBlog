package com.mackwu.component.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.mackwu.base.BaseActivity;
import com.mackwu.component.R;
import com.mackwu.component.bean.RecycleItem;
import com.mackwu.component.bean.RecycleSectionItem;
import com.mackwu.component.databinding.ActivityStickyHeaderBinding;
import com.mackwu.component.ui.view.QuickViewHolder;
import com.mackwu.component.ui.view.sticky.StickyItemDecoration;
import com.mackwu.component.ui.viewmodel.RecyclerViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2022/11/3 15:03
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class StickyHeaderActivity extends BaseActivity<RecyclerViewModel, ActivityStickyHeaderBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        //
        List<RecycleSectionItem> recycleSectionItems = new ArrayList<>();
        recycleSectionItems.add(new RecycleSectionItem(true, new RecycleItem("2022-1-1", R.drawable.home_bg_1)));
        recycleSectionItems.add(new RecycleSectionItem(false, new RecycleItem("2022-1-2", R.drawable.home_bg_2)));
        recycleSectionItems.add(new RecycleSectionItem(false, new RecycleItem("2022-1-7", R.drawable.home_bg_1)));
        recycleSectionItems.add(new RecycleSectionItem(false, new RecycleItem("2022-1-22", R.drawable.home_bg_1)));
        recycleSectionItems.add(new RecycleSectionItem(true, new RecycleItem("2022-2-1", R.drawable.home_bg_3)));
        recycleSectionItems.add(new RecycleSectionItem(false, new RecycleItem("2022-2-1", R.drawable.home_bg_1)));
        recycleSectionItems.add(new RecycleSectionItem(false, new RecycleItem("2022-5-1", R.drawable.home_bg_2)));
        recycleSectionItems.add(new RecycleSectionItem(false, new RecycleItem("2022-5-1", R.drawable.home_bg_1)));
        recycleSectionItems.add(new RecycleSectionItem(true, new RecycleItem("2022-7-1", R.drawable.home_bg_3)));
        recycleSectionItems.add(new RecycleSectionItem(false, new RecycleItem("2022-7-9", R.drawable.home_bg_1)));
        recycleSectionItems.add(new RecycleSectionItem(false, new RecycleItem("2022-7-2", R.drawable.home_bg_1)));
        recycleSectionItems.add(new RecycleSectionItem(false, new RecycleItem("2022-7-12", R.drawable.home_bg_1)));
        recycleSectionItems.add(new RecycleSectionItem(false, new RecycleItem("2022-7-13", R.drawable.home_bg_1)));
        recycleSectionItems.add(new RecycleSectionItem(true, new RecycleItem("2022-9-1", R.drawable.home_bg_1)));
        recycleSectionItems.add(new RecycleSectionItem(false, new RecycleItem("2022-9-22", R.drawable.home_bg_1)));


        // adapter
        MyAdapter2 adapter = new MyAdapter2();
        adapter.setList(recycleSectionItems);

        // recyclerView
        StickyItemDecoration stickyItemDecoration = new StickyItemDecoration(binding.stickyHeaderLayout);
        stickyItemDecoration.setOnStickyChangeListener((view, isHeader) -> {
            if (isHeader) {
                View childView = view.findViewById(R.id.ll_sticky_header);
                if (view.getTop() < 0) {
                    childView.setVisibility(View.GONE);
                } else {
                    childView.setVisibility(View.VISIBLE);
                }
            }
        });
        binding.recyclerView.addItemDecoration(stickyItemDecoration);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        // stickyHeaderLayout
        binding.stickyHeaderLayout.setDataCallback(position -> {
            RecycleSectionItem item = adapter.getItem(position);
            String date = item.getRecycleItem().getDate();
            binding.layoutItemHeader.tvTest.setText(date);
        });

        //
//        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                int firstVisibleItemPosition = RecyclerViewPositionHelper.createHelper(recyclerView).findFirstVisibleItemPosition();
//                RecycleSectionItem item = adapter.getItem(firstVisibleItemPosition);
//                if (item.isHeader()) {
//                    String date = item.getRecycleItem().getDate();
//                    binding.layoutItemHeader.tvTest.setText(date);
//                }
//            }
//        });
    }


    private static class MyAdapter extends BaseSectionQuickAdapter<RecycleSectionItem, QuickViewHolder> {

        public MyAdapter() {
            super(R.layout.layout_item_sticky_header);
            setNormalLayout(R.layout.layout_item_sticky);
        }

        @Override
        protected void convert(@NonNull QuickViewHolder viewHolder, RecycleSectionItem recycleSectionItem) {
            RecycleItem recycleItem = recycleSectionItem.getRecycleItem();
            viewHolder.setImageResource(R.id.iv_test, recycleItem.getResId());
            viewHolder.setText(R.id.tv_test, recycleItem.getDate());
        }

        @Override
        protected void convertHeader(@NonNull QuickViewHolder viewHolder, @NonNull RecycleSectionItem recycleSectionItem) {
            RecycleItem recycleItem = recycleSectionItem.getRecycleItem();
            viewHolder.setText(R.id.tv_test, recycleItem.getDate());
        }
    }

    private static class MyAdapter2 extends BaseQuickAdapter<RecycleSectionItem, QuickViewHolder> {

        public MyAdapter2() {
            super(R.layout.layout_item_sticky);
        }

        @Override
        protected void convert(@NonNull QuickViewHolder viewHolder, RecycleSectionItem recycleSectionItem) {
            RecycleItem recycleItem = recycleSectionItem.getRecycleItem();
            viewHolder.setImageResource(R.id.iv_test, recycleItem.getResId());
            viewHolder.setText(R.id.tv_test, recycleItem.getDate());
            viewHolder.setVisible(R.id.ll_sticky_header, recycleSectionItem.isHeader());
        }
    }

}
