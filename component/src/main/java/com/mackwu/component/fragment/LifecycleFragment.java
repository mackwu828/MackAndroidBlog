package com.mackwu.component.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mackwu.component.base.BaseFragment;

/**
 * ===================================================
 * Created by MackWu on 2020/6/20 1:36
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public abstract class LifecycleFragment extends BaseFragment {

    private static final String HEAD = LifecycleFragment.class.getSimpleName() + " ";

    @Override
    public void onAttach(@NonNull final Context context) {
        super.onAttach(context);
        Log.d("TAG", HEAD + "onAttach...");
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", HEAD + "onCreate...");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        Log.d("TAG", HEAD + "onCreateView...");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("TAG", HEAD + "onViewCreated...");
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("TAG", HEAD + "onActivityCreated...");
    }

    @Override
    public void onViewStateRestored(@Nullable final Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d("TAG", HEAD + "onViewStateRestored...");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TAG", HEAD + "onStart...");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TAG", HEAD + "onResume...");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("TAG", HEAD + "onPause...");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("TAG", HEAD + "onStop...");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("TAG", HEAD + "onDestroyView...");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG", HEAD + "onDestroy...");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("TAG", HEAD + "onDetach...");
    }

    @Override
    public void onHiddenChanged(final boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d("TAG", HEAD + "onHiddenChanged...");
    }
}
