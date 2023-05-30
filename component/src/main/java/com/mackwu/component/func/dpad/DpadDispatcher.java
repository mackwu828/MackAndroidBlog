package com.mackwu.component.func.dpad;

import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author MackWu
 * @since 2023/2/28 14:46
 */
public class DpadDispatcher implements DpadDispatch {

    RecyclerView recyclerView;
    // 列数。一行有几列
    int columns = 1;
    // 底部监听
    OnDpadToBottomListener onDpadToBottomListener;
    // 顶部监听
    OnDpadToTopListener onDpadToTopListener;
    boolean interceptLastRowDpadDown;
    // 震动动画
    ShakeAnimator shakeAnimator;

    DpadDispatcher(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.shakeAnimator = new ShakeAnimator();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            RecyclerView.Adapter<?> adapter = recyclerView.getAdapter();
            View currentFocused = recyclerView.getFocusedChild();
            if (layoutManager == null || adapter == null || currentFocused == null) {
                return false;
            }
            int keyCode = event.getKeyCode();
            if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN
                    || keyCode == KeyEvent.KEYCODE_DPAD_UP
                    || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                // item数量
                int itemCount = adapter.getItemCount();
                // 行数
                int rows = itemCount % columns == 0 ? itemCount / columns : itemCount / columns + 1;
                // 焦点的位置
                int focusedPosition = layoutManager.getPosition(currentFocused);
                // 当前行
                int currentRow = focusedPosition / columns;
                // 可见的item数量
                int visibleItemCount = layoutManager.getChildCount();
                // 可见的行数
                int visibleRows = (int) Math.ceil((float) visibleItemCount / columns);
//                Logger.d("keyCode=" + keyCode + ", columns=" + columns + ", rows=" + rows + ", currentRow=" + currentRow + ", visibleRows=" + visibleRows);

                // KEYCODE_DPAD_DOWN
                if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                    // 在倒数第二页的倒数第二行时
                    if (currentRow >= rows - visibleRows - 1) {
                        if (onDpadToBottomListener != null) onDpadToBottomListener.onDpadToBottom();
                    }
                    // 在倒数第一行时
                    if (!interceptLastRowDpadDown && currentRow == rows - 1) {
                        shakeAnimator.start(currentFocused);
                        return true;
                    }
                    // 在倒数第二行向下无法获取焦点时，最后一个item获取焦点
                    View nextFocus = FocusFinder.getInstance().findNextFocus(recyclerView, currentFocused, View.FOCUS_DOWN);
                    if (currentRow == rows - 2 && nextFocus == null) {
                        RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(itemCount - 1);
                        if (viewHolder != null) {
                            viewHolder.itemView.requestFocusFromTouch();
                            return true;
                        }
                    }
                }
                // KEYCODE_DPAD_UP
                else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                    // 小于第二页的第二行时，用来处理按上加载更多
                    if (currentRow <= 1 + visibleRows) {
                        if (onDpadToTopListener != null) {
                            onDpadToTopListener.onDpadToTop();
                        }
                    }
                }
                // KEYCODE_DPAD_RIGHT
                else {
                    // 在倒数第一行时，且item个数不是列数的倍数
                    // 按右时如果右边没有焦点，则聚焦到右上，防止焦点跳到其他控件上。
                    if (currentRow == rows - 1 && itemCount % columns != 0) {
                        View rightFocused = FocusFinder.getInstance().findNextFocus(recyclerView, currentFocused, View.FOCUS_RIGHT);
                        if (rightFocused == null) {
                            View upFocused = FocusFinder.getInstance().findNextFocus(recyclerView, currentFocused, View.FOCUS_UP);
                            if (upFocused != null) {
                                upFocused.requestFocus();
                                rightFocused = FocusFinder.getInstance().findNextFocus(recyclerView, upFocused, View.FOCUS_RIGHT);
                                if (rightFocused != null) {
                                    rightFocused.requestFocus();
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void setColumns(int columns) {
        this.columns = columns;
    }

    @Override
    public void setOnDpadToBottomListener(OnDpadToBottomListener onDpadToBottomListener) {
        this.onDpadToBottomListener = onDpadToBottomListener;
    }

    @Override
    public void setOnDpadToTopListener(OnDpadToTopListener onDpadToTopListener) {
        this.onDpadToTopListener = onDpadToTopListener;
    }

    @Override
    public void setInterceptLastRowDpadDown(boolean interceptLastRowDpadDown) {
        this.interceptLastRowDpadDown = interceptLastRowDpadDown;
    }

}
