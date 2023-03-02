package com.mackwu.component.ui.share_element;

import android.os.Bundle;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mackwu.base.BaseActivity;
import com.mackwu.component.R;
import com.mackwu.component.bean.RecycleItem;
import com.mackwu.component.databinding.ActivityShareElement2Binding;
import com.mackwu.component.ui.view.recycler.QuickViewHolder;
import com.mackwu.component.ui.viewmodel.RecyclerViewModel;

/**
 * @author MackWu
 * @since 2022/12/5 14:35
 */
public class ShareElement2Activity extends BaseActivity<RecyclerViewModel, ActivityShareElement2Binding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        // 延迟执行过场动画。
        postponeEnterTransition();
        // 监听绘制完成后，再执行过场动画。
        binding.getRoot().getViewTreeObserver()
                .addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        binding.getRoot().getViewTreeObserver().removeOnPreDrawListener(this);
                        startPostponedEnterTransition();
                        return false;
                    }
                });

        // adapter
        MyAdapter adapter = new MyAdapter();
        adapter.setOnItemClickListener((a, view, position) -> {

        });
        adapter.setList(viewModel.getData());

        // recyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }


    private static class MyAdapter extends BaseQuickAdapter<RecycleItem, QuickViewHolder> {
        public MyAdapter() {
            super(R.layout.layout_item_linear);
        }

        @Override
        protected void convert(@NonNull QuickViewHolder viewHolder, RecycleItem recycleItem) {
            if (viewHolder.getItemPosition() == 0) {
                viewHolder.getView(R.id.iv_test).setTransitionName("shareElement");
            }
            viewHolder.setImageResource(R.id.iv_test, recycleItem.getResId());
        }
    }
}
