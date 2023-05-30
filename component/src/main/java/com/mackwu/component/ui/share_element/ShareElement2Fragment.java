package com.mackwu.component.ui.share_element;

import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mackwu.base.fragment.BaseFragment;
import com.mackwu.base.util.Logger;
import com.mackwu.component.R;
import com.mackwu.component.bean.RecycleItem;
import com.mackwu.component.databinding.FragmentShareElement2Binding;
import com.mackwu.component.ui.view.QuickViewHolder;
import com.mackwu.component.ui.viewmodel.RecyclerViewModel;

/**
 * @author MackWu
 * @since 2022/12/5 17:38
 */
public class ShareElement2Fragment extends BaseFragment<RecyclerViewModel, FragmentShareElement2Binding> {

    public static int position = 6;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setSharedElementEnterTransition(
                TransitionInflater.from(getContext())
                        .inflateTransition(android.R.transition.move));
        postponeEnterTransition();
        binding.recyclerView.getViewTreeObserver()
                .addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        binding.recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);

                        binding.recyclerView.scrollToPosition(position);
                        binding.recyclerView.post(() -> {
                            View view = binding.recyclerView.getLayoutManager().findViewByPosition(position);
                            Logger.d("viewviewview222=" + view);
                            if (view != null) {
                                view.setTransitionName("shareElement");
                            }
                            startPostponedEnterTransition();
                        });

                        return false;
                    }
                });


        MyAdapter adapter = new MyAdapter();
        adapter.setList(viewModel.getData());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
    }

    public static ShareElement2Fragment newInstance() {
        Bundle args = new Bundle();
        ShareElement2Fragment fragment = new ShareElement2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    private static class MyAdapter extends BaseQuickAdapter<RecycleItem, QuickViewHolder> {
        public MyAdapter() {
            super(R.layout.item_test_1);
        }

        @Override
        protected void convert(@NonNull QuickViewHolder viewHolder, RecycleItem recycleItem) {
            viewHolder.setImageResource(R.id.iv_test, recycleItem.getResId());
            viewHolder.setText(R.id.tv_test, viewHolder.getItemPosition() + "");
        }
    }
}
