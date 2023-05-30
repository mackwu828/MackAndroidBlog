package com.mackwu.component.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mackwu.base.fragment.BaseFragment;
import com.mackwu.component.R;
import com.mackwu.component.bean.RecycleItem;
import com.mackwu.component.databinding.FragmentTestBinding;
import com.mackwu.component.ui.view.QuickViewHolder;
import com.mackwu.component.ui.viewmodel.RecyclerViewModel;

/**
 * @author MackWu
 * @since 2023/3/21 11:09
 */
public class TestFragment extends BaseFragment<RecyclerViewModel, FragmentTestBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        // adapter
        MyAdapter adapter = new MyAdapter();
        adapter.setOnItemClickListener((a, view, position) -> {

        });
        adapter.setList(viewModel.getData());

        // recyclerView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        binding.recyclerView.setLayoutManager(gridLayoutManager);
        binding.recyclerView.setAdapter(adapter);
    }

    private static class MyAdapter extends BaseQuickAdapter<RecycleItem, QuickViewHolder> {
        public MyAdapter() {
            super(R.layout.item_test_1);
        }

        @Override
        protected void convert(@NonNull QuickViewHolder viewHolder, RecycleItem recycleItem) {
            viewHolder.setImageResource(R.id.iv_test, recycleItem.getResId());
            viewHolder.setText(R.id.tv_test, recycleItem.getDate());
        }
    }

}
