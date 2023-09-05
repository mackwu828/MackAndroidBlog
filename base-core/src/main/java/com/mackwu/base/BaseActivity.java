package com.mackwu.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.mackwu.base.util.ActivityStackUtil;
import com.mackwu.base.util.Logger;
import com.mackwu.base.util.ReflectUtil;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.base.viewmodel.ViewModelFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author MackWu
 * @since 2020/6/19 23:26
 */
public abstract class BaseActivity<VM extends BaseViewModel, B extends ViewBinding> extends AppCompatActivity implements IActivity {

    protected VM viewModel;
    protected B binding;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("What activity is created? " + this.getClass().getSimpleName());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        initViewModel();
        initViewBinding();
        ActivityStackUtil.addActivity(this);
        initView(savedInstanceState);
        initData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStackUtil.removeActivity(this);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @SuppressWarnings("unchecked")
    private void initViewModel() {
        Class<VM> vmCls = (Class<VM>) ReflectUtil.getActualTypeArgument(getClass(), 0);
        viewModel = new ViewModelProvider(this, new ViewModelFactory(getApplication())).get(vmCls != null ? vmCls : (Class<VM>) BaseViewModel.class);
    }

    @SuppressWarnings({"unchecked"})
    private void initViewBinding() {
        Class<B> vbClass = (Class<B>) ReflectUtil.getActualTypeArgument(getClass(), 1);
        try {
            Method inflate = (vbClass != null ? vbClass : getViewBindingCls()).getMethod("inflate", LayoutInflater.class);
            binding = (B) inflate.invoke(null, getLayoutInflater());
            setContentView(Objects.requireNonNull(binding).getRoot());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取ViewBinding。
     * 如果子类没有使用泛型，{@link #initViewBinding}通过反射会获取不到ViewBinding对象，需要主动设置。
     */
    protected Class<B> getViewBindingCls() {
        return null;
    }

    /**
     * 是否横屏
     */
    protected boolean isOrientationLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    protected int getScreenWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    protected int getScreenHeight() {
        return getResources().getDisplayMetrics().heightPixels;
    }

}
