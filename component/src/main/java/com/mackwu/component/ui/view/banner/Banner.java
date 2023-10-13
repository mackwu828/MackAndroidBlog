package com.mackwu.component.ui.view.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import java.util.List;

/**
 * @author MackWu
 * @since 2023/10/13 19:15
 */
public class Banner extends ViewPager {

    private BannerAdapter adapter;
    // 自动轮播线程
    private Runnable loopRunnable;
    // 轮播间隔
    private final long loopDuration = 3000;
    // 当前位置
    private int currentPosition;

    public Banner(Context context) {
        this(context, null);
    }

    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        // adapter
        adapter = new BannerAdapter();
        setAdapter(adapter);
    }

    private void startLoop() {
        if (loopRunnable == null) {
            loopRunnable = () -> {
                currentPosition = getCurrentItem();
                currentPosition++;
                setCurrentItem(currentPosition);
                postDelayed(loopRunnable, loopDuration);
            };
        }
        postDelayed(loopRunnable, loopDuration);
    }

    /**
     * 设置数据
     *
     * @param views views
     */
    public void setData(List<View> views) {
        adapter.setViews(views);
    }

}
