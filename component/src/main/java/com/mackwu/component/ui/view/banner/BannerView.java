package com.mackwu.component.ui.view.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2022/2/21 16:17
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class BannerView extends FrameLayout {

    // banner
    private BannerViewPager banner;
    // adapter
    private BannerPagerAdapter adapter;
    // 自动轮播线程
    private Runnable loopRunnable;
    // 轮播间隔
    private final long loopDuration = 3000;
    // 当前位置
    private int currentPosition;

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        // adapter
        adapter = new BannerPagerAdapter();

        // banner
        banner = new BannerViewPager(getContext());
        addView(banner);
        banner.setAdapter(adapter);

        // startLoop
        startLoop();
    }

    private void startLoop() {
        if (loopRunnable == null) {
            loopRunnable = () -> {
                currentPosition = banner.getCurrentItem();
                currentPosition++;
                banner.setCurrentItem(currentPosition);
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
