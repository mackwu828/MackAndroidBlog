package com.mackwu.component.ui;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.mackwu.component.R;
import com.mackwu.xmvc.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ===================================================
 * Created by MackWu on 2020/9/1 20:19
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class LottieActivity extends BaseActivity {

    @BindView(R.id.lottie_animation_view)
    LottieAnimationView lottieAnimationView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_lottie;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @OnClick(R.id.btn_start)
    public void onBtnStartClicked() {
        startAnimation("listening.json");
//        startAnimation("thinking.json");
//        startAnimation("speaking.json");
    }

    @OnClick(R.id.btn_stop)
    public void onBtnStopClicked() {
        stopAnimation();
    }

    /**
     * 启动动画
     */
    private void startAnimation(final String assetName) {
        lottieAnimationView.setAnimation(assetName);
        lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);
        lottieAnimationView.playAnimation();
    }

    /**
     * 停止动画
     */
    private void stopAnimation() {
        lottieAnimationView.cancelAnimation();
    }

    /**
     * 暂停动画
     */
    private void pauseAnimation() {
        lottieAnimationView.pauseAnimation();
    }
}
