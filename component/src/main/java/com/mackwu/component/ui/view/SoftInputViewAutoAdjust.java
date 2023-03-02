package com.mackwu.component.ui.view;

import android.graphics.Rect;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 监听软键盘弹出并自动跳转布局
 *
 * @Author shaw.yang
 * @Date 2021/3/10
 */
public class SoftInputViewAutoAdjust {

    private final int adjustViewBottom;
    private final View decorView;
    private final View adjustView;
    private int height;
    private int barHeight;

    /**
     * @param window     当前window
     * @param adjustView 需要自动调整的view
     */
    public SoftInputViewAutoAdjust(Window window, View adjustView) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        decorView = window.getDecorView();
        this.adjustView = adjustView;
        adjustViewBottom = adjustView.getPaddingBottom();
        height = decorView.getContext().getResources().getDisplayMetrics().heightPixels;
    }

    public SoftInputViewAutoAdjust listener(ISoftInputAdjust iSoftInputAdjust) {
        Rect rect = new Rect();
        if(iSoftInputAdjust!=null){
            iSoftInputAdjust.init();
        }
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            decorView.getWindowVisibleDisplayFrame(rect);
            if (rect.bottom > height && barHeight == 0) {
                //初始高跟底差距
                barHeight = rect.bottom - height;
            }

            int diff = height - rect.bottom + barHeight;
            if (diff != 0) {
                if (adjustView.getPaddingBottom() != diff) {
                    adjustView.setPadding(adjustView.getPaddingLeft(), 0, adjustView.getPaddingRight(), diff);
                    if (iSoftInputAdjust != null) {
                        iSoftInputAdjust.viewToTop(diff);
                    }
                }
            } else {
                if (adjustView.getPaddingBottom() != adjustViewBottom) {
                    restoreLayout();
                    if (iSoftInputAdjust != null) {
                        iSoftInputAdjust.viewToRestore();
                    }
                }
            }
        });
        return this;
    }

    public interface ISoftInputAdjust {
        void init();

        /**
         * View被软键盘顶上去
         * @param diff
         */
        void viewToTop(int diff);

        /**
         * View还原且软键盘收回
         */
        void viewToRestore();
    }

    public void restoreLayout() {
        adjustView.setPadding(adjustView.getPaddingLeft(), 0, adjustView.getPaddingRight(), adjustViewBottom);
    }

}
