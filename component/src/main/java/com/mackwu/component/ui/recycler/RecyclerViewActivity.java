package com.mackwu.component.ui.recycler;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.mackwu.base.BaseActivity;
import com.mackwu.component.R;
import com.mackwu.component.bean.RecycleItem;
import com.mackwu.component.databinding.ActivityRecyclerViewBinding;
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
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setAlignItems(AlignItems.STRETCH);

        binding.recyclerView.setLayoutManager(flexboxLayoutManager);
        binding.recyclerView.setAdapter(adapter);
        binding.btnTest.setOnClickListener(v -> {

        });
    }

    private static class MyAdapter extends BaseQuickAdapter<RecycleItem, QuickViewHolder> {

        public MyAdapter() {
            super(R.layout.item_test_1);
        }

        @Override
        protected void convert(@NonNull QuickViewHolder viewHolder, RecycleItem recycleItem) {
            int position = viewHolder.getItemPosition();
            ImageView ivTest = viewHolder.getView(R.id.iv_test);
            ivTest.setImageResource(recycleItem.getResId());
            if (position == 0) {
                ViewGroup.LayoutParams lp = ivTest.getLayoutParams();
                lp.width = getContext().getResources().getDimensionPixelSize(R.dimen.dp_638);
                lp.height = getContext().getResources().getDimensionPixelSize(R.dimen.dp_518);
                ivTest.setLayoutParams(lp);
            }

            View itemView = viewHolder.getItemView();
            ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
            if (layoutParams instanceof FlexboxLayoutManager.LayoutParams) {
                FlexboxLayoutManager.LayoutParams flexboxLayoutParams = (FlexboxLayoutManager.LayoutParams) layoutParams;
                flexboxLayoutParams.setFlexGrow(1f);
            }
        }
    }

}
