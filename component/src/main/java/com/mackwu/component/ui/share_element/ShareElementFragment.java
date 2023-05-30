package com.mackwu.component.ui.share_element;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mackwu.base.fragment.BaseFragment;
import com.mackwu.component.R;
import com.mackwu.component.bean.RecycleItem;
import com.mackwu.component.databinding.FragmentShareElementBinding;
import com.mackwu.component.ui.view.QuickViewHolder;
import com.mackwu.component.ui.viewmodel.RecyclerViewModel;

/**
 * @author MackWu
 * @since 2022/12/5 17:38
 */
public class ShareElementFragment extends BaseFragment<RecyclerViewModel, FragmentShareElementBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        GridAdapter gridAdapter = new GridAdapter();
        gridAdapter.setOnItemClickListener((a, view, position) -> {
            ShareElement2Fragment.position = position;
            view.setTransitionName("shareElement");
            getParentFragmentManager()
                    .beginTransaction()
                    .addSharedElement(view, "shareElement")
                    .replace(R.id.fl_container, ShareElement2Fragment.newInstance())
                    .commit();
        });
        gridAdapter.setList(viewModel.getData());
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.recyclerView.setAdapter(gridAdapter);
    }

    public static ShareElementFragment newInstance() {
        Bundle args = new Bundle();
        ShareElementFragment fragment = new ShareElementFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private static class GridAdapter extends BaseQuickAdapter<RecycleItem, QuickViewHolder> {
        public GridAdapter() {
            super(R.layout.layout_item_grid);
        }

        @Override
        protected void convert(@NonNull QuickViewHolder viewHolder, RecycleItem recycleItem) {
            viewHolder.setImageResource(R.id.iv_test, recycleItem.getResId());
        }
    }
}
