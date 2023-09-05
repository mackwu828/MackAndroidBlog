package com.mackwu.component.ui.view.chat;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.mackwu.base.util.Logger;
import com.mackwu.component.databinding.LayoutChatBinding;

import java.util.LinkedList;


/**
 * @author MackWu
 * @since 2023/6/28 16:26
 */
public class ChatLayout extends FrameLayout {

    private LinkedList<Chat> chatLinkedList = new LinkedList<>();
    private LayoutChatBinding binding;

    public ChatLayout(Context context) {
        this(context, null);
    }

    public ChatLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChatLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        binding = LayoutChatBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    private int duration = 600;

    public void addQuestion() {
        Chat chat = new Chat("I'm not in a good mood today. Can you give me something fun？");
        binding.tvQuestion.setText(chat.getText());
        binding.tvQuestion.setAlpha(0);
        binding.tvQuestion.post(() -> {
            Logger.d("binding.tvQuestion.getHeight()=" + binding.tvQuestion.getHeight());
            ObjectAnimator translationY = ObjectAnimator.ofFloat(binding.tvQuestion, "translationY", binding.tvQuestion.getHeight() * 1.5f, 0);
            ObjectAnimator alpha = ObjectAnimator.ofFloat(binding.tvQuestion, "alpha", 0, 1);
            ObjectAnimator answerAlpha = ObjectAnimator.ofFloat(binding.tvAnswer, "alpha", binding.tvAnswer.getAlpha(), 0);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(translationY)
                    .with(alpha)
                    .with(answerAlpha);
            animatorSet.setDuration(duration);
            animatorSet.start();
        });
    }

    public void addAnswer() {
        Chat chat = new Chat("Be happy, friend. What would you like to have? Comedy, Light music or talk show？");
        binding.tvAnswer.setText(chat.getText());
        binding.tvAnswer.setAlpha(0);
        binding.tvAnswer.post(() -> {
            ObjectAnimator translationY = ObjectAnimator.ofFloat(binding.tvAnswer, "translationY", binding.tvAnswer.getHeight() * 1.5f, 0);
            ObjectAnimator alpha = ObjectAnimator.ofFloat(binding.tvAnswer, "alpha", 0, 1);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(translationY).with(alpha);
            animatorSet.setDuration(duration);
            animatorSet.start();

            ObjectAnimator questionAlpha = ObjectAnimator.ofFloat(binding.tvQuestion, "alpha", binding.tvQuestion.getAlpha(), 0);
            ObjectAnimator questionTranslationY = ObjectAnimator.ofFloat(binding.tvQuestion, "translationY", 0, -binding.tvAnswer.getHeight() / 2f);
            animatorSet = new AnimatorSet();
            animatorSet.play(questionAlpha).with(questionTranslationY)
                    .after(duration - 100);
            animatorSet.setDuration(duration);
            animatorSet.start();
        });
    }

}
