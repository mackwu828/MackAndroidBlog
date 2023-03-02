package com.mackwu.component.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mackwu.component.R;

/**
 * @author MackWu
 * @since 2023/1/3 15:01
 */
public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tvTest;
    ImageView ivTest;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTest = itemView.findViewById(R.id.tv_test);
        ivTest = itemView.findViewById(R.id.iv_test);
    }
}
