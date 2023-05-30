package com.mackwu.component.ui.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

/**
 * @author MackWu
 * @since 2022/11/9 14:32
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
        initView();
    }

    private void initView() {
        // 数据增删改查时防止布局重绘
        setHasFixedSize(true);
        // 设置缓存大小
        setItemViewCacheSize(100);
        setDrawingCacheEnabled(true);
        setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        // 滚动时停止加载图片
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (((Activity) getContext()).isDestroyed()) {
                    return;
                }
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    if (!Glide.with(getContext()).isPaused()) {
                        Glide.with(getContext()).pauseRequests();
                    }
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (Glide.with(getContext()).isPaused()) {
                        Glide.with(getContext()).resumeRequests();
                    }
                }
            }
        });
    }


}
