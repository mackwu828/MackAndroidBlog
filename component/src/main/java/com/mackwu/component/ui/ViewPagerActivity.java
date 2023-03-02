package com.mackwu.component.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.databinding.WidgetActivtiyViewpagerBinding;

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

        // data
        List<View> views = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(this);
            if (i == 0) {
                imageView.setImageResource(R.drawable.home_bg_1);
            } else if (i == 1) {
                imageView.setImageResource(R.drawable.home_bg_2);
            } else {
                imageView.setImageResource(R.drawable.home_bg_3);
            }
            views.add(imageView);
        }

        //
        binding.bannerView.setData(views);
    }

}
