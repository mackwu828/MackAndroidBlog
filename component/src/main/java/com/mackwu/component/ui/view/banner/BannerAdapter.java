package com.mackwu.component.ui.view.banner;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.mackwu.base.util.Logger;

import java.util.List;

/**
 * @author MackWu
 * @since 2022/2/21 15:33
 */
public class BannerAdapter extends PagerAdapter {

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
        Logger.d("instantiateItem...  position=" + position + ", p=" + p);
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
        Logger.d("destroyItem...  position=" + position + ", p=" + p);
        container.removeView(views.get(p));
    }

    public void setViews(List<View> views) {
        this.views = views;
    }

}
