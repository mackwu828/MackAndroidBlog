package com.mackwu.view.basic.recycler.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2020/6/4 17:39
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements View.OnClickListener {

    private int layoutResId;
    private List<T> data;
    private OnItemClickListener onItemClickListener = null;

    public BaseAdapter(int layoutResId, List<T> data) {
        this.layoutResId = layoutResId;
        this.data = data;
    }

    /**
     * implement this method and use the helper to adapt the view to the given item.
     */
    public abstract void convert(@NonNull VH holder, T item);

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public VH onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResId, null);
        VH holder = (VH) new BaseViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final VH holder, final int position) {
        convert(holder, data.get(position));
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(final View view) {
        if (null != onItemClickListener) onItemClickListener.onItemClick(view, (int) view.getTag());
    }

    public void setData(final List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}