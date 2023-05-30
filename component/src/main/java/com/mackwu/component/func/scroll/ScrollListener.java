package com.mackwu.component.func.scroll;

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

    RecyclerView recyclerView;
    private OnScrollToLastPositionListener onScrollToLastPositionListener;
    private OnScrolledListener onScrolledListener;
    private OnScrollToBottomListener onScrollToBottomListener;
    private OnScrollToTopListener onScrollToTopListener;

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        if (((Activity) recyclerView.getContext()).isDestroyed()) {
            return;
        }
        this.recyclerView = recyclerView;
        switch (newState) {
//            case RecyclerView.SCROLL_STATE_DRAGGING:
//                Logger.d("onScrollStateChanged...  SCROLL_STATE_DRAGGING");
//                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
//                Logger.d("onScrollStateChanged...  SCROLL_STATE_SETTLING");
                pauseGlide();
                scrollToBottom();
                scrollToTop();
                break;
            case RecyclerView.SCROLL_STATE_IDLE:
//                Logger.d("onScrollStateChanged...  SCROLL_STATE_IDLE");
                resumeGlide();
                scrollToLastPosition();
                break;
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        if (((Activity) recyclerView.getContext()).isDestroyed()) {
            return;
        }
        if (onScrolledListener != null) {
            onScrolledListener.onScrolled();
        }
    }

    private void pauseGlide() {
//        Logger.d("pauseGlide...");
        Glide.with(recyclerView).pauseRequests();
    }

    private void resumeGlide() {
//        Logger.d("resumeGlide...");
        Glide.with(recyclerView).resumeRequests();
    }

    private void scrollToLastPosition() {
        if (onScrollToLastPositionListener == null) {
            return;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null) {
            return;
        }
        View view = layoutManager.getChildAt(0);
        if (view == null) {
            return;
        }
        int position = layoutManager.getPosition(view);
        int top = view.getTop();
//                Logger.d("onScrollStateIdle...  position=" + position + ", top=" + top);
        onScrollToLastPositionListener.onScrollToLastPosition(position, top);
    }

    /**
     * 滚动到底部
     */
    private void scrollToBottom() {
        if (onScrollToBottomListener == null) {
            return;
        }
        if (ScrollUtil.isScrollToBottom(recyclerView)) {
            onScrollToBottomListener.onScrollToBottom();
        }
    }

    /**
     * 滚动到顶部
     */
    private void scrollToTop() {
        if (onScrollToTopListener == null) {
            return;
        }
        if (ScrollUtil.isScrollToTop(recyclerView)) {
            onScrollToTopListener.onScrollToTop();
        }
    }

    public void setOnScrollToLastPositionListener(OnScrollToLastPositionListener onScrollToLastPositionListener) {
        this.onScrollToLastPositionListener = onScrollToLastPositionListener;
    }

    public void setOnScrolledListener(OnScrolledListener onScrolledListener) {
        this.onScrolledListener = onScrolledListener;
    }

    public void setOnScrollToBottomListener(OnScrollToBottomListener onScrollToBottomListener) {
        this.onScrollToBottomListener = onScrollToBottomListener;
    }

    public void setOnScrollToTopListener(OnScrollToTopListener onScrollToTopListener) {
        this.onScrollToTopListener = onScrollToTopListener;
    }

    public interface OnScrollToLastPositionListener {
        void onScrollToLastPosition(int position, int top);
    }

    public interface OnScrolledListener {
        void onScrolled();
    }

    public interface OnScrollToBottomListener {
        void onScrollToBottom();
    }

    public interface OnScrollToTopListener {
        void onScrollToTop();
    }

    public void removeAllListener() {
        onScrollToLastPositionListener = null;
        onScrolledListener = null;
        onScrollToBottomListener = null;
        onScrollToTopListener = null;
    }
}
