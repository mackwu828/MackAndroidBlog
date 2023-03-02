package com.mackwu.component.ui.recycler;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.Logger;
import com.mackwu.component.R;
import com.mackwu.component.bean.RecycleItem;
import com.mackwu.component.databinding.ActivityBrvBinding;
import com.mackwu.component.ui.view.recycler.QuickViewHolder;
import com.mackwu.component.ui.view.util.RecyclerViewPositionHelper;
import com.mackwu.component.ui.viewmodel.RecyclerViewModel;

/**
 * @author MackWu
 * @since 2022/12/5 15:30
 */
public class BrvActivity extends BaseActivity<RecyclerViewModel, ActivityBrvBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        // adapter
        MyAdapter adapter = new MyAdapter();
        adapter.setOnItemClickListener((a, view, position) -> {

        });
        adapter.setList(viewModel.getData());

        // recyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
//        binding.recyclerView.scrollToPosition(6);


        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                if (dy <= 0) {
//                    int firstCompletelyVisibleItemPosition = RecyclerViewPositionHelper.createHelper(recyclerView).findFirstCompletelyVisibleItemPosition();
//                    LogUtil.d("onScrollStateChanged...   firstCompletelyVisibleItemPosition=" + firstCompletelyVisibleItemPosition);
//                    if (firstCompletelyVisibleItemPosition == 0) {
//                        LogUtil.d("onScrollStateChanged...   scroll to top");
//                        recyclerView.post(() -> {
//                            adapter.addData(0, new RecycleItem("2023-5-1", R.drawable.ic_launcher_background));
//                        });
//                    }
//                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE || newState == RecyclerView.SCROLL_STATE_SETTLING) {

                }
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager == null || layoutManager.getItemCount() == 0) {
                    return;
                }
                int firstCompletelyVisibleItemPosition = RecyclerViewPositionHelper.createHelper(recyclerView).findFirstCompletelyVisibleItemPosition();
                Logger.d("onScrollStateChanged...   firstCompletelyVisibleItemPosition=" + firstCompletelyVisibleItemPosition);
                if (firstCompletelyVisibleItemPosition == 0) {
                    Logger.d("onScrollStateChanged...   scroll to top");
                    adapter.addData(0, new RecycleItem("2023-5-1", R.drawable.ic_launcher_background));
                }
            }
        });
    }

    private static class MyAdapter extends BaseQuickAdapter<RecycleItem, QuickViewHolder> {
        public MyAdapter() {
            super(R.layout.layout_item_linear);
        }

        @Override
        protected void convert(@NonNull QuickViewHolder viewHolder, RecycleItem recycleItem) {
            viewHolder.setImageResource(R.id.iv_test, recycleItem.getResId());
            viewHolder.setText(R.id.tv_test, recycleItem.getDate());
        }
    }

}
