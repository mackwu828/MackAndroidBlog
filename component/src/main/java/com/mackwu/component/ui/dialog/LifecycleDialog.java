package com.mackwu.component.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.viewbinding.ViewBinding;

import com.mackwu.base.fragment.BaseDialogFragment;
import com.mackwu.base.util.Logger;
import com.mackwu.base.viewmodel.BaseViewModel;

/**
 * @author MackWu
 * @since 2022/8/22 16:21
 */
public abstract class LifecycleDialog<VM extends BaseViewModel, B extends ViewBinding> extends BaseDialogFragment<VM, B> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("onCreate...");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Logger.d("onCreateDialog...");
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.d("onCreateView...");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.d("onViewCreated...");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.d("onActivityCreated...");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.d("onStop...");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.d("onDestroyView...");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.d("onDestroy...");
    }

    @Override
    public void show(FragmentActivity activity) {
        super.show(activity);
        Logger.d("show...");
    }

    @Override
    public void dismiss() {
        super.dismiss();
        Logger.d("dismiss...");
    }

}
