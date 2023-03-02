package com.mackwu.component.ui.view.util;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

/**
 * @author MackWu
 * @since 2022/11/10 12:02
 */
public class ScrollListener extends RecyclerView.OnScrollListener {

    private OnScrollStateIdleListener onScrollStateIdleListener;
    private OnScrollToBottomListener onScrollToBottomListener;
    private OnScrolledListener onScrolledListener;

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        if (((Activity) recyclerView.getContext()).isDestroyed()) {
            return;
        }
        onHandleGlide(recyclerView, newState);
        onScrollStateIdle(recyclerView);
        onScrollToBottom(recyclerView, newState);
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        if (((Activity) recyclerView.getContext()).isDestroyed()) {
            return;
        }
        onScrolled(recyclerView);
    }

    /**
     * 滑动时不加载图片，滑动停止时再加载图片
     *
     * @param recyclerView recyclerView
     * @param newState     newState
     */
    private void onHandleGlide(RecyclerView recyclerView, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_SETTLING || newState == RecyclerView.SCROLL_STATE_DRAGGING) {
            if (!Glide.with(recyclerView.getContext()).isPaused()) {
                Glide.with(recyclerView.getContext()).pauseRequests();
            }
        } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (Glide.with(recyclerView.getContext()).isPaused()) {
                Glide.with(recyclerView.getContext()).resumeRequests();
            }
        }
    }

    private void onScrollStateIdle(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager != null) {
            View view = layoutManager.getChildAt(0);
            if (view == null) {
                return;
            }
            int position = layoutManager.getPosition(view);
            int top = view.getTop();
//            Logger.d("onScrollStateIdle...  position=" + position + ", top=" + top);
            if (onScrollStateIdleListener != null) {
                onScrollStateIdleListener.onScrollStateIdle(position, top);
            }
        }
    }

    /**
     * 是否滑动到底部
     *
     * @param recyclerView recyclerView
     * @param newState     newState
     */
    private void onScrollToBottom(RecyclerView recyclerView, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE || newState == RecyclerView.SCROLL_STATE_SETTLING) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager == null || layoutManager.getItemCount() == 0) {
                return;
            }
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = RecyclerViewPositionHelper.createHelper(recyclerView).findFirstVisibleItemPosition();
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount) {
                if (onScrollToBottomListener != null) {
                    onScrollToBottomListener.onScrollToBottom();
                }
            }
        }
    }

    private void onScrolled(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager != null) {
            View view = layoutManager.getChildAt(0);
            if (view == null) {
                return;
            }
            int position = layoutManager.getPosition(view);
            int top = view.getTop();
//            Logger.d("onScrolled...  position=" + position + ", top=" + top);
            if (onScrolledListener != null) {
                onScrolledListener.onScrolled(view, position, top);
            }
        }
    }

    public void setOnScrollStateIdleListener(OnScrollStateIdleListener onScrollStateIdleListener) {
        this.onScrollStateIdleListener = onScrollStateIdleListener;
    }

    public void setOnScrolledListener(OnScrolledListener onScrolledListener) {
        this.onScrolledListener = onScrolledListener;
    }

    public interface OnScrollStateIdleListener {
        void onScrollStateIdle(int position, int top);
    }

    public interface OnScrolledListener {
        void onScrolled(View view, int position, int top);
    }

    public interface OnScrollToBottomListener {
        void onScrollToBottom();
    }
}
