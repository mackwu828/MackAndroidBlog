package com.mackwu.component.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mackwu.component.R;

/**
 * ===================================================
 * Created by MackWu on 2022/2/28 15:58
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class FastScroller extends FrameLayout implements View.OnTouchListener {

    private RecyclerView.OnScrollListener onScrollListener;

    public FastScroller(Context context) {
        this(context, null);
    }

    public FastScroller(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FastScroller(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_fast_scroll, this, true);

        //
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    public RecyclerView.OnScrollListener getOnScrollListener(){
        if (onScrollListener == null) {
            onScrollListener = new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                }
            };
        }
        return onScrollListener;
    }
}
