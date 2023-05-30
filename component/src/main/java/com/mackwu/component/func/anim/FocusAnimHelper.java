package com.mackwu.component.func.anim;

import android.view.View;

import com.mackwu.component.R;

/**
 * @author MackWu
 * @since 2023/3/30 19:13
 */
public class FocusAnimHelper {

    /**
     * 以View中心为原点，不缩放，向上平移dp_50
     *
     * @param view     view
     * @param hasFocus hasFocus
     */
    public static void overlapAnimateFocus(View view, boolean hasFocus) {
        FocusAnimator animator = (FocusAnimator) view.getTag(R.id.focus_animator);
        if (animator == null) {
            view.setPivotX(view.getWidth() / 2f);
            view.setPivotY(view.getHeight() / 2f);
            FocusAnimator.AnimateConfig animateConfig = new FocusAnimator.AnimateConfig();
            animateConfig.setScaleFactor(1f);
            animateConfig.setTranslateY(-view.getContext().getResources().getDimensionPixelSize(R.dimen.dp_50));
            animator = new FocusAnimator(view, animateConfig);
            view.setTag(R.id.focus_animator, animator);
        }
        animator.animateFocus(hasFocus);
    }

}
