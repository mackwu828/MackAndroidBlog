package com.mackwu.component.ui;

import android.os.Bundle;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.mackwu.base.BaseActivity;
import com.mackwu.component.R;
import com.mackwu.component.bean.RecycleItem;
import com.mackwu.component.databinding.ActivityDockBinding;
import com.mackwu.component.func.hover.BaseHoverQuickAdapter;
import com.mackwu.component.func.decoration.SpaceItemDecoration;
import com.mackwu.component.ui.view.QuickViewHolder;
import com.mackwu.component.ui.viewmodel.RecyclerViewModel;

/**
 * @author MackWu
 * @since 2023/4/6 16:22
 */
public class DockActivity extends BaseActivity<RecyclerViewModel, ActivityDockBinding> {

    float lastX;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {


        // adapter
        MyAdapter adapter = new MyAdapter();
        adapter.setOnItemHoverChangeListener((v, event, position) -> {
//            float x = event.getX();
//            float y = event.getY();
//            Logger.d("ACTION_HOVER_MOVE  x=" + x + ", y=" + y + ", position=" + position);


            // ACTION_HOVER_ENTER
            if (event.getAction() == MotionEvent.ACTION_HOVER_ENTER) {
            }
            // ACTION_HOVER_MOVE
            else if (event.getAction() == MotionEvent.ACTION_HOVER_MOVE) {

            } else if (event.getAction() == MotionEvent.ACTION_HOVER_EXIT) {
            }
        });

        // set data
        adapter.setList(viewModel.getData());

        // recyclerView
        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration();
        spaceItemDecoration.setRect(0, 0, 20, 0);
//        binding.recyclerView.addItemDecoration(spaceItemDecoration);
//        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
//        binding.recyclerView.setAdapter(adapter);
    }

//    private View findViewByPosition(int position) {
//        RecyclerView.LayoutManager layoutManager = binding.recyclerView.getLayoutManager();
//        if (layoutManager == null) {
//            return null;
//        }
//        if (position < 0 || position > layoutManager.getChildCount()) {
//            return null;
//        }
//        return layoutManager.findViewByPosition(position);
//    }

    private static class MyAdapter extends BaseHoverQuickAdapter<RecycleItem, QuickViewHolder> {
        public MyAdapter() {
            super(R.layout.item_dock);
        }

        @Override
        protected void convert(@NonNull QuickViewHolder viewHolder, RecycleItem recycleItem) {
            viewHolder.setImageResource(R.id.iv_test, recycleItem.getResId());
        }
    }
}
