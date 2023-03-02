package com.mackwu.component.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.Logger;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityBinderBinding;
import com.mackwu.component.func.binder.IGradeService;
import com.mackwu.component.func.binder.GradeService;

/**
 * @author MackWu
 * @since 2023/1/17 11:04
 */
public class BinderActivity extends BaseActivity<BaseViewModel, ActivityBinderBinding> {

    private IGradeService binder;

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Logger.d("onServiceConnected...  name=" + name);
//            binder = BinderProxy.asInterface(service);
            binder = IGradeService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Logger.d("onServiceDisconnected...  name=" + name);
            binder = null;
        }
    };

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        binding.btnBindService.setOnClickListener(v -> {
            Intent intent = new Intent(this, GradeService.class);
//            intent.setPackage(getPackageName());
            bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        });

        binding.btnGetData.setOnClickListener(v -> {
            try {
                int grade = binder.getStudentGrade("Anna");
                Logger.d("getStudentGrade...  grade=" + grade);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

}
