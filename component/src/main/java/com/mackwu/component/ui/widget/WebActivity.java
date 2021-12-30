package com.mackwu.component.ui.widget;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.LogUtil;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.IWebInterface;
import com.mackwu.component.core.WebProcessManager;
import com.mackwu.component.databinding.WidgetActivityWebBinding;
import com.mackwu.component.service.WebService;
import com.mackwu.component.ui.window.WebWindow;

/**
 * ===================================================
 * Created by MackWu on 2020/12/18 10:57
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class WebActivity extends BaseActivity<BaseViewModel, WidgetActivityWebBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        binding.btnShow.setOnClickListener(v -> {
            WebWindow.getInstance().show();
        });
        binding.btnHide.setOnClickListener(v -> {
            WebWindow.getInstance().hide();
        });
        binding.btnShowAcrossProcess.setOnClickListener(v -> WebProcessManager.getInstance().showAcrossProcess());
        binding.btnHideAcrossProcess.setOnClickListener(v -> WebProcessManager.getInstance().hideAcrossProcess());
        bindWebService();
    }

    private void bindWebService() {
        bindService(new Intent(this, WebService.class), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                LogUtil.d("onServiceConnected...");
                IWebInterface iWebInterface = IWebInterface.Stub.asInterface(service);
                WebProcessManager.getInstance().setWebInterface(iWebInterface);
                WebProcessManager.getInstance().loadUrlAcrossProcess();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                LogUtil.d("onServiceDisconnected...");
            }
        }, Service.BIND_AUTO_CREATE);
    }

}