package com.mackwu.base.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.mackwu.base.util.Logger;
import com.mackwu.base.util.ReflectUtil;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.base.viewmodel.ViewModelFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author MackWu
 * @since 2020/7/31 15:49
 */
public abstract class BaseDialogFragment<VM extends BaseViewModel, B extends ViewBinding> extends AppCompatDialogFragment implements IDialogFragment {

    protected VM viewModel;
    protected B binding;
    private DialogInterface.OnDismissListener onDismissListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("What dialog fragment is created? " + getClass().getSimpleName());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new CustomDialog(getContext(), getTheme());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        initDialog();
        initViewModel();
        initViewBinding(container);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(savedInstanceState);
        initData(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null) {
            return;
        }
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = getWidth();
        layoutParams.height = getHeight();
        layoutParams.dimAmount = getDimAmount();
        layoutParams.gravity = getGravity();
        window.setAttributes(layoutParams);
    }

    @Override
    public void show(FragmentActivity activity) {
        try {
            show(activity.getSupportFragmentManager(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }

    @Override
    public int getWidth() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    @Override
    public int getHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    @Override
    public float getDimAmount() {
        return 0.5f;
    }

    @Override
    public int getGravity() {
        return Gravity.CENTER;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }

    @Override
    public boolean isCanceledOnTouchOutside() {
        return true;
    }

    private void initDialog() {
        Dialog dialog = getDialog();
        if (dialog == null) {
            return;
        }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(isCancelable());
        dialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside());
    }

    @SuppressWarnings("unchecked")
    private void initViewModel() {
        try {
            Class<VM> vmCls = (Class<VM>) ReflectUtil.getActualTypeArgument(getClass(), 0);
            viewModel = new ViewModelProvider(this, new ViewModelFactory(requireActivity().getApplication())).get(vmCls != null ? vmCls : (Class<VM>) BaseViewModel.class);
            getLifecycle().addObserver(viewModel);
        } catch (Exception e) {
            // ignored
        }
    }

    @SuppressWarnings({"unchecked"})
    private void initViewBinding(@Nullable final ViewGroup container) {
        try {
            Class<B> vbClass = (Class<B>) ReflectUtil.getActualTypeArgument(getClass(), 1);
            Method inflate = vbClass.getMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
            binding = (B) inflate.invoke(null, getLayoutInflater(), container, false);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // ignored
        }
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    /**
     * 处理DialogFragment的内存泄漏
     */
    static class CustomDialog extends AppCompatDialog {

        OnShowListener showListener;
        OnDismissListener dismissListener;
        OnCancelListener cancelListener;

        public CustomDialog(Context context, int theme) {
            super(context, theme);
        }

        @Override
        public void setOnShowListener(@Nullable OnShowListener listener) {
            this.showListener = listener;
        }

        @Override
        public void setOnDismissListener(@Nullable OnDismissListener listener) {
            this.dismissListener = listener;
        }

        @Override
        public void setOnCancelListener(@Nullable OnCancelListener listener) {
            this.cancelListener = listener;
        }

        @Override
        public void show() {
            super.show();
            if (showListener != null) {
                showListener.onShow(this);
            }
        }

        @Override
        public void dismiss() {
            super.dismiss();
            if (dismissListener != null) {
                dismissListener.onDismiss(this);
            }
        }

        @Override
        public void cancel() {
            super.cancel();
            if (cancelListener != null) {
                cancelListener.onCancel(this);
            }
        }
    }

}
