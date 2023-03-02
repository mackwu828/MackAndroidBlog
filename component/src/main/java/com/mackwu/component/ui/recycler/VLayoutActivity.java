package com.mackwu.component.ui.recycler;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.mackwu.base.BaseActivity;
import com.mackwu.component.R;
import com.mackwu.component.bean.RecycleItem;
import com.mackwu.component.databinding.WidgetActivityRecyclerBinding;
import com.mackwu.component.ui.viewmodel.RecyclerViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MackWu
 * @since 2022/10/28 15:26
 */
public class VLayoutActivity extends BaseActivity<RecyclerViewModel, WidgetActivityRecyclerBinding> {

    @SuppressWarnings("rawtypes")
    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        List<RecycleItem> recycleItems = viewModel.getData();

        //
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        binding.recyclerView.setLayoutManager(virtualLayoutManager);
        // delegateAdapter
        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        List<DelegateAdapter.Adapter> adapters = new ArrayList<>();


        // linearLayoutHelper
//        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
//        // 设置分割线高度
//        linearLayoutHelper.setDividerHeight(getResources().getDimensionPixelOffset(R.dimen.dp_20));
//        // 设置宽高比。如3：高是宽的3倍。
//        linearLayoutHelper.setAspectRatio(3);
//        galleryDayAdapter = new GalleryDayAdapter(this, galleryItems, linearLayoutHelper);
//        delegateAdapter.addAdapter(galleryDayAdapter);


        // gridLayoutHelper
//        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
//        gridLayoutHelper.setAspectRatio(2);
//        // 设置垂直间距
//        gridLayoutHelper.setVGap(getResources().getDimensionPixelOffset(R.dimen.dp_20));
//        // 设置水平间距
//        gridLayoutHelper.setHGap(getResources().getDimensionPixelOffset(R.dimen.dp_20));
//        galleryDayAdapter = new GalleryDayAdapter(this, galleryItems, gridLayoutHelper);
//        delegateAdapter.addAdapter(galleryDayAdapter);


        // columnLayoutHelper
//        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
//        columnLayoutHelper.setAspectRatio(2);
//        columnLayoutHelper.setWeights(new float[]{30, 30, 10, 30});


        // onePlusNLayoutHelper
        OnePlusNLayoutHelper onePlusNLayoutHelper = new OnePlusNLayoutHelper();
        // 设置行比重
        onePlusNLayoutHelper.setColWeights(new float[]{58, 21, 21, 42});
        // 设置列比重
        onePlusNLayoutHelper.setRowWeight(81);
        MyAdapter oneAdapter = new MyAdapter(this, onePlusNLayoutHelper, recycleItems.subList(0, 4));
//        delegateAdapter.addAdapter(oneAdapter);
        adapters.add(oneAdapter);


        // setAdapter
        delegateAdapter.setAdapters(adapters);
        binding.recyclerView.setAdapter(delegateAdapter);
    }


    private static class MyAdapter extends DelegateAdapter.Adapter<MyAdapter.MyViewHolder> {

        Context context;
        LayoutHelper layoutHelper;
        List<RecycleItem> data;

        public MyAdapter(Context context, LayoutHelper layoutHelper, List<RecycleItem> data) {
            this.context = context;
            this.layoutHelper = layoutHelper;
            this.data = data;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return layoutHelper;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_linear, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            RecycleItem recycleItem = data.get(position);
            holder.tvTest.setText(recycleItem.getDate());
            holder.ivTest.setImageResource(recycleItem.getResId());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tvTest;
            ImageView ivTest;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTest = itemView.findViewById(R.id.tv_test);
                ivTest = itemView.findViewById(R.id.iv_test);
            }
        }
    }
}
