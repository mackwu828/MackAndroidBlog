package com.mackwu.component.ui.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.WidgetActivityHeartViewBinding;

import java.util.Random;

/**
 * ===================================================
 * Created by MackWu on 2022/2/22 17:02
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class HeartViewActivity extends BaseActivity<BaseViewModel, WidgetActivityHeartViewBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        binding.rlContainer.setOnClickListener(v -> {
            ImageView imageView = cloneImage();
            animateFlying(imageView);
            animateFading(imageView);
        });
    }

    private ImageView cloneImage() {
        ImageView clone = new ImageView(this);
        clone.setLayoutParams(binding.ivHeart.getLayoutParams());
        clone.setImageDrawable(binding.ivHeart.getDrawable());
        binding.flHeartContainer.addView(clone);
        return clone;
    }

    private void animateFlying(ImageView image) {
        float x = 0f;
        float y = 0f;
        float r = 400 + new Random().nextInt(1000);
        float angle = 25f;

        Path path = new Path();
        if (r % 2 == 0) {
            path.arcTo(new RectF(x, y - r, x + 2 * r, y + r), 180f, angle);
        } else {
            path.arcTo(new RectF(x - 2 * r, y - r, x, y + r), 0f, -angle);
        }
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(image, View.X, View.Y, path);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
    }

    private void animateFading(ImageView image) {
        image.animate()
                .alpha(0f)
                .setDuration(1000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        binding.flHeartContainer.removeView(image);
                    }
                });
    }
}
