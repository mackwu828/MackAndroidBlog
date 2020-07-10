package com.mackwu.view.basic.recycler;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;

import androidx.recyclerview.widget.RecyclerView;

/**
 * ===================================================
 * Created by MackWu on 2020/6/18 16:43
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class MyRecyclerView extends RecyclerView {
    public MyRecyclerView(Context context) {
        this(context, null);
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    public boolean dispatchKeyEvent(final KeyEvent event) {
        Log.d("TAG", "MyRecyclerView dispatchKeyEvent...");
        return super.dispatchKeyEvent(event);
    }
}
