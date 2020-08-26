package com.mackwu.xmvc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mackwu.xmvc.util.LogUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ===================================================
 * Created by MackWu on 2020/6/19 23:31
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public abstract class BaseFragment extends Fragment implements IFragment {

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtil.d("onViewCreated...  What fragment is created? " + getClass().getSimpleName());
        initUnBinder(view);
        initView(view, savedInstanceState);
        initData(savedInstanceState);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    private void initUnBinder(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null && unbinder != Unbinder.EMPTY) {
            unbinder.unbind();
            unbinder = null;
        }
    }

}
