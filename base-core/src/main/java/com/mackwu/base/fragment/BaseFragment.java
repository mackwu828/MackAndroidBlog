package com.mackwu.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.mackwu.base.util.Logger;
import com.mackwu.base.util.ReflectUtil;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.base.viewmodel.ViewModelFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class BaseFragment<VM extends BaseViewModel, B extends ViewBinding> extends Fragment implements IFragment {

    protected VM viewModel;
    protected B binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("What fragment is created? " + getClass().getSimpleName());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        initViewBinding(container);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        initView(savedInstanceState);
        initData(savedInstanceState);
    }

    @SuppressWarnings("unchecked")
    private void initViewModel() {
        Class<VM> vmCls = (Class<VM>) ReflectUtil.getActualTypeArgument(getClass(), 0);
        viewModel = new ViewModelProvider(this, new ViewModelFactory(requireActivity().getApplication())).get(vmCls != null ? vmCls : (Class<VM>) BaseViewModel.class);
    }

    @SuppressWarnings({"unchecked"})
    private void initViewBinding(@Nullable final ViewGroup container) {
        Class<B> vbClass = (Class<B>) ReflectUtil.getActualTypeArgument(getClass(), 1);
        try {
            Method inflate = vbClass.getMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
            binding = (B) inflate.invoke(null, getLayoutInflater(), container, false);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
