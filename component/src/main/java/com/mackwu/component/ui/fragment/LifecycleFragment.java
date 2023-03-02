package com.mackwu.component.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.mackwu.base.fragment.BaseFragment;
import com.mackwu.base.util.Logger;
import com.mackwu.base.viewmodel.BaseViewModel;


/**
 * ===================================================
 * Created by MackWu on 2020/6/20 1:36
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public abstract class LifecycleFragment<VM extends BaseViewModel, B extends ViewBinding> extends BaseFragment<VM, B> {

    private final String HEAD = getClass().getSimpleName() + " ";

    @Override
    public void onAttach(@NonNull final Context context) {
        super.onAttach(context);
        Logger.d(HEAD + "onAttach...");
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(HEAD + "onCreate...");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        Logger.d(HEAD + "onCreateView...");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.d(HEAD + "onViewCreated...");
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.d(HEAD + "onActivityCreated...");
    }

    @Override
    public void onViewStateRestored(@Nullable final Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Logger.d(HEAD + "onViewStateRestored...");
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.d(HEAD + "onStart...");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.d(HEAD + "onResume...");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.d(HEAD + "onPause...");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.d(HEAD + "onStop...");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.d(HEAD + "onDestroyView...");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.d(HEAD + "onDestroy...");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.d(HEAD + "onDetach...");
    }

    @Override
    public void onHiddenChanged(final boolean hidden) {
        super.onHiddenChanged(hidden);
        Logger.d(HEAD + "onHiddenChanged...");
    }
}
