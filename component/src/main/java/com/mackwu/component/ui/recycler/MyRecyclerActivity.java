package com.mackwu.component.ui.recycler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.Logger;
import com.mackwu.component.databinding.WidgetActivityRecyclerBinding;
import com.mackwu.component.ui.adapter.MyAdapter;
import com.mackwu.component.ui.viewmodel.RecyclerViewModel;


/**
 * ===================================================
 * Created by MackWu on 2021/11/16 16:00
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class MyRecyclerActivity extends BaseActivity<RecyclerViewModel, WidgetActivityRecyclerBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        // adapter
        MyAdapter adapter = new MyAdapter(viewModel.getData());

        // recyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }

    /**
     * 滚动
     * addOnScrollListener: 滚动监听
     * canScrollVertically：是否可以垂直滚动
     * canScrollHorizontally：是否可以水平滚动
     */
    private void testOnScrollListener() {
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            /**
             * 滚动状态变化时回调
             * @param recyclerView recyclerView
             * @param newState public static final int SCROLL_STATE_IDLE = 0 静止没有滚动
             *                 public static final int SCROLL_STATE_DRAGGING = 1 正在被外部拖拽,一般为用户正在用手指滚动
             *                 public static final int SCROLL_STATE_SETTLING = 2 自动滚动
             */
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                Logger.d("onScrollStateChanged...  newState=" + newState);
            }

            /**
             * 滚动时回调
             * @param recyclerView recyclerView
             * @param dx 水平滚动距离
             *        dx > 0 时为手指向左滚动,列表滚动显示右面的内容
             *        dx < 0 时为手指向右滚动,列表滚动显示左面的内容
             * @param dy 垂直滚动距离
             *        dy > 0 时为手指向上滚动,列表滚动显示下面的内容
             *        dy < 0 时为手指向下滚动,列表滚动显示上面的内容
             */
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                Logger.d("onScrolled...  dx=" + dx + ", dy=" + dy);
            }
        });
        // 是否可以垂直滚动。1表示手指向上滚动, -1表示手指向下滚动。1时返回false表示已经滚到底部
        binding.recyclerView.canScrollVertically(1);
        // 是否可以水平滚动。
        binding.recyclerView.canScrollHorizontally(1);


        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                // 是否滚动到底部
                if (!recyclerView.canScrollVertically(1)) {
                    Logger.d("scrolled to the bottom!!!");
                }
            }
        });
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                // 是否滚动到底部
                if (dy > 0) {
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    if (layoutManager instanceof LinearLayoutManager) {
                        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                        // 当前可见的item数量
                        int visibleItemCount = linearLayoutManager.getChildCount();
                        // item总数
                        int totalItemCount = linearLayoutManager.getItemCount();
                        // 可见的第一个item的位置
                        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                        Logger.d("onScrolled...  visibleItemCount=" + visibleItemCount + ", totalItemCount=" + totalItemCount + ", firstVisibleItemPosition=" + firstVisibleItemPosition);
                        // 当前可见的item数量+可见的第一个item的位置>item总数
                        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount) {
                            Logger.d("scrolled to the bottom!!!");
                        }
                    }
                }
            }
        });
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                // 正在被外部拖拽或者自动滚动时，停止加载图片
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    if (!Glide.with(MyRecyclerActivity.this).isPaused()) {
                        Glide.with(MyRecyclerActivity.this).pauseRequests();
                    }
                }
                // 静止没有滚动时，开始加载图片
                else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (Glide.with(MyRecyclerActivity.this).isPaused()) {
                        Glide.with(MyRecyclerActivity.this).resumeRequests();
                    }
                }
            }
        });
    }


}
