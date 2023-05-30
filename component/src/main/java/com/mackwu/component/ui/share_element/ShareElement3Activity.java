package com.mackwu.component.ui.share_element;

import android.os.Bundle;
import android.transition.Scene;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mackwu.base.BaseActivity;
import com.mackwu.component.R;
import com.mackwu.component.bean.RecycleItem;
import com.mackwu.component.databinding.ActivityShareElement3Binding;
import com.mackwu.component.ui.view.QuickViewHolder;
import com.mackwu.component.ui.viewmodel.RecyclerViewModel;

/**
 * @author MackWu
 * @since 2022/12/5 14:35
 */
public class ShareElement3Activity extends BaseActivity<RecyclerViewModel, ActivityShareElement3Binding> {

//    Scene scene1;
//    LayoutShareElementBinding shareElementBinding;
//
//    Scene scene2;
//    LayoutShareElement2Binding shareElement2Binding;
//    MyAdapter adapter;
//
//    int position;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        Scene scene1 = Scene.getSceneForLayout(binding.llContainer, R.layout.layout_share_element, this);
//        Scene scene2 = Scene.getSceneForLayout(binding.llContainer, R.layout.layout_share_element_2, this);


//        shareElementBinding = LayoutShareElementBinding.inflate(LayoutInflater.from(this));
//        scene1 = new Scene(binding.llContainer, shareElementBinding.getRoot());
        GridAdapter gridAdapter = new GridAdapter();
        gridAdapter.setOnItemClickListener((a, view, position) -> {


        });
        gridAdapter.setList(viewModel.getData());
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        binding.recyclerView.setAdapter(gridAdapter);

//        shareElement2Binding = LayoutShareElement2Binding.inflate(LayoutInflater.from(this));
//        scene2 = new Scene(binding.llContainer, shareElement2Binding.getRoot());
//        adapter = new MyAdapter();
//        adapter.setOnItemClickListener((a, view, position) -> {
//            view.setTransitionName("shareElement" + position);
//            TransitionManager.go(scene1, getWindow().getSharedElementEnterTransition());
//        });
//        adapter.setList(viewModel.getData());

//        TransitionManager.go(scene1, getWindow().getSharedElementEnterTransition());


        binding.btnTest.setOnClickListener(v -> {
            MyAdapter adapter = new MyAdapter();
            adapter.setList(viewModel.getData());
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
            binding.recyclerView.setAdapter(adapter);
        });
        binding.btnTest2.setOnClickListener(v -> {
        });
    }

    private void a() {
//            Transition transition = new ChangeBounds();
//            transition.setDuration(1000);
//            TransitionManager.beginDelayedTransition(binding.llContainer, transition);
//            ViewGroup.LayoutParams layoutParams = binding.ivTest.getLayoutParams();
//            layoutParams.width = getResources().getDimensionPixelSize(R.dimen.dp_400);
//            layoutParams.height = getResources().getDimensionPixelSize(R.dimen.dp_400);
//            binding.ivTest.setLayoutParams(layoutParams);
    }


    private static class MyAdapter extends BaseQuickAdapter<RecycleItem, QuickViewHolder> {
        public MyAdapter() {
            super(R.layout.item_test_1);
        }

        @Override
        protected void convert(@NonNull QuickViewHolder viewHolder, RecycleItem recycleItem) {
            viewHolder.setImageResource(R.id.iv_test, recycleItem.getResId());
        }
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
