package com.mackwu.component.ui;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityChatBinding;

/**
 * @author MackWu
 * @since 2023/6/28 15:56
 */
public class ChatActivity extends BaseActivity<BaseViewModel, ActivityChatBinding> {


    @SuppressLint("SetTextI18n")
    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        binding.btnTest.setOnClickListener( v-> {
            binding.chatLayout.addQuestion();
        });
        binding.btnTest2.setOnClickListener(v -> {
            binding.chatLayout.addAnswer();
        });
    }


}
