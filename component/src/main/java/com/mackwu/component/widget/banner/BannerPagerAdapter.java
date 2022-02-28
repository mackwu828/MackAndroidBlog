package com.mackwu.component.widget.banner;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.mackwu.base.util.LogUtil;

import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2022/2/21 15:33
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class BannerPagerAdapter extends PagerAdapter {

    private List<View> views;

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int p = position % views.size();
        LogUtil.d("instantiateItem...  position=" + position + ", p=" + p);
        View view = views.get(p);
        ViewParent parent = view.getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        int p = position % views.size();
        LogUtil.d("destroyItem...  position=" + position + ", p=" + p);
        container.removeView(views.get(p));
    }

    public void setViews(List<View> views) {
        this.views = views;
    }

}
