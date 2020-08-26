package com.mackwu.xmvc;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ===================================================
 * Created by MackWu on 2020/7/31 15:49
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public abstract class BaseDialogFragment extends AppCompatDialogFragment implements IFragment {

    private Unbinder unbinder;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
