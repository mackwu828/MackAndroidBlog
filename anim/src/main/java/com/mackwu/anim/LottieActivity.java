package com.mackwu.anim;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.mackwu.xmvc.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ===================================================
 * Created by MackWu on 2020/8/6 14:52
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

    }

    @OnClick(R.id.btn_listening)
    public void onBtnListeningClicked() {
        showStyle("listening.json");
    }

    @OnClick(R.id.btn_thinking)
    public void onBtnThinkingClicked() {
        showStyle("thinking.json");
    }

    @OnClick(R.id.btn_speaking)
    public void onBtnSpeakingClicked() {
        showStyle("speaking.json");
    }

    private void showStyle(final String assetName) {
        lottieAnimationView.setAnimation(assetName);
        lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);
        lottieAnimationView.playAnimation();
    }
}
