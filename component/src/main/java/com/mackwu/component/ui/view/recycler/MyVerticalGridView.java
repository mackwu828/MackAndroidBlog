package com.mackwu.component.ui.view.recycler;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;

import androidx.leanback.widget.VerticalGridView;

/**
 * @author MackWu
 * @since 2022/6/30 15:58
 */
public class MyVerticalGridView extends VerticalGridView {

    private float x;
    // 列数
    private int columns;
//    // 行数
//    private int rows;
//    // 当前行
//    private int currentRow;

    public MyVerticalGridView(Context context) {
        this(context, null);
    }

    public MyVerticalGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyVerticalGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {

    }

    @Override
    public void setNumColumns(int numColumns) {
        this.columns = numColumns;
        super.setNumColumns(numColumns);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
            }
            //
            else if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {

            }
            // KEYCODE_DPAD_DOWN
            else if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN) {

            }
            // KEYCODE_DPAD_UP
            else if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP) {

            }
        }
        //
        else if (event.getAction() == KeyEvent.ACTION_DOWN) {

        }
        return super.dispatchKeyEvent(event);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        requestDisallowInterceptTouchEvent(true);
//        return super.dispatchTouchEvent(ev);
//    }
//
//    @Override
//    public boolean onGenericMotionEvent(MotionEvent event) {
//        return true;
//    }

    /**
     * 防止在空白处依然可以拖动。
     */
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = e.getX();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_MOVE:
                return e.getAction() == MotionEvent.ACTION_MOVE || e.getX() != x || super.onTouchEvent(e);
        }
        return super.onTouchEvent(e);
    }


}
