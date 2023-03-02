package com.mackwu.component.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.mackwu.component.R;


/**
 * @author MackWu
 * @since 2020/9/18 11:56
 * replace RadioButton
 */
public class CheckView extends AppCompatImageView {

    private int checkedImageResource;
    private int uncheckedImageResource;
    private boolean isChecked;
    private OnCheckListener onCheckListener;

    public CheckView(Context context) {
        this(context, null);
    }

    public CheckView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CheckView);
        checkedImageResource = typedArray.getResourceId(R.styleable.CheckView_checked_image_resource, -1);
        uncheckedImageResource = typedArray.getResourceId(R.styleable.CheckView_unchecked_image_resource, -1);
        isChecked = typedArray.getBoolean(R.styleable.CheckView_checked, false);
        typedArray.recycle();

        // setImageResource
        setImageResource(isChecked ? checkedImageResource : uncheckedImageResource);
        // setOnClickListener
//        setOnClickListener(v -> {
//            isChecked = !isChecked;
//            setImageResource(isChecked ? checkedImageResource : uncheckedImageResource);
//            if (onCheckListener != null) onCheckListener.onCheck(isChecked);
//        });
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
        setImageResource(isChecked ? checkedImageResource : uncheckedImageResource);
    }

    public void setOnCheckListener(OnCheckListener onCheckListener) {
        this.onCheckListener = onCheckListener;
    }

    public interface OnCheckListener {
        void onCheck(boolean isChecked);
    }

}
