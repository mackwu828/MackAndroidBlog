package com.mackwu.component.ui.view.barrage;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.mackwu.base.util.Logger;
import com.mackwu.component.R;
import com.mackwu.component.databinding.LayoutBarrageBinding;

/**
 * @author MackWu
 * @since 2022/8/16 11:41
 * 弹幕
 */
public class BarrageView extends FrameLayout {

    LayoutBarrageBinding binding;
    Animation anim;

    public BarrageView(Context context) {
        this(context, null);
    }

    public BarrageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BarrageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        binding = LayoutBarrageBinding.inflate(LayoutInflater.from(getContext()), this, true);
        setClipChildren(false);
        setClickable(true);
        setVisibility(View.GONE);

        // anim
        anim = createTranslateAnim(getScreenWidth(), -getScreenWidth() - getResources().getDimensionPixelOffset(R.dimen.dp_184));
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                hideBarrage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // setOnClickListener
        setOnClickListener(v -> hideBarrage());
    }

    public void showBarrage(Barrage barrage) {
        Logger.d("showBarrage...  " + barrage);
        setVisibility(View.VISIBLE);
        ConstraintLayout root = binding.getRoot();
        for (int i = 0; i < root.getChildCount(); i++) {
            View child = root.getChildAt(i);
            if (child instanceof TextView) {
                TextView textView = (TextView) child;
                textView.setText(barrage.getContent());
                if (textView.getLineCount() > 1) {
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.sp_34));
                }
            }
        }
        startAnimation(anim);
    }

    public void hideBarrage() {
        setVisibility(View.GONE);
        clearAnimation();
    }

    Animation createTranslateAnim(int fromX, int toX) {
        TranslateAnimation tlAnim = new TranslateAnimation(fromX, toX, 0, 0);
        long duration = (long) (Math.abs(toX - fromX) * 1.0f / getScreenWidth() * 3000);
        tlAnim.setDuration(duration);
        tlAnim.setInterpolator(new DecelerateAccelerateInterpolator());
        tlAnim.setRepeatCount(Animation.INFINITE);
        tlAnim.setFillAfter(true);
        return tlAnim;
    }

    int getScreenWidth() {
        return getContext().getResources().getDisplayMetrics().widthPixels;
    }

    static class DecelerateAccelerateInterpolator implements Interpolator {
        @Override
        public float getInterpolation(float input) {
            return (float) (Math.tan((input * 2 - 1) / 4 * Math.PI)) / 2.0f + 0.5f;
        }
    }

}
