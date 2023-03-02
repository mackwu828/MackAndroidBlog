package com.mackwu.component.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mackwu.base.util.Logger;
import com.mackwu.component.R;
import com.mackwu.component.bean.RecycleItem;

import java.util.List;

/**
 * @author MackWu
 * @since 2023/1/3 15:01
 * 2022-08-15 17:59:24.023 1627-1627/com.mackwu.component D/mack_wu: onCreateViewHolder...
 * 2022-08-15 17:59:24.026 1627-1627/com.mackwu.component D/mack_wu: onBindViewHolder...
 * 2022-08-15 17:59:24.027 1627-1627/com.mackwu.component D/mack_wu: onViewAttachedToWindow...
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    List<RecycleItem> data;

    public MyAdapter(List<RecycleItem> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Logger.d("onCreateViewHolder...");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_linear, parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Logger.d("onBindViewHolder...  position=" + position);
        RecycleItem item = data.get(position);
        holder.tvTest.setText(item.getDate());
        holder.ivTest.setImageResource(item.getResId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        Logger.d("onViewAttachedToWindow...");
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        Logger.d("onViewDetachedFromWindow...");
    }

    /**
     * 当item被回收的时候调用。如果调用了viewHolder.setIsRecyclable(false)，则item不会被回收。
     *
     * @param holder holder
     */
    @Override
    public void onViewRecycled(@NonNull MyViewHolder holder) {
        super.onViewRecycled(holder);
        Logger.d("onViewRecycled...");
    }

}

