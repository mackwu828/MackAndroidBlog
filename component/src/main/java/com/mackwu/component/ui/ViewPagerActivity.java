package com.mackwu.component.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.databinding.WidgetActivtiyViewpagerBinding;
import com.mackwu.component.ui.fragment.RedFragment;
import com.mackwu.component.ui.fragment.TestFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2021/2/19 18:51
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ViewPagerActivity extends BaseActivity<BaseViewModel, WidgetActivtiyViewpagerBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        //
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new TestFragment());
        fragments.add(new RedFragment());

        // adapter
        PagerAdapter adapter = new PagerAdapter(this);
        adapter.setFragments(fragments);

        // viewPager
        binding.viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.viewPager.setAdapter(adapter);
    }

    private static class PagerAdapter extends FragmentStateAdapter {

        private List<Fragment> fragments;

        public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        public PagerAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        public PagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }

        public void setFragments(List<Fragment> fragments) {
            this.fragments = fragments;
        }
    }

}
