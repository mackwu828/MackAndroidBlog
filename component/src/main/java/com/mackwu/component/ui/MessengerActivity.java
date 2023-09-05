package com.mackwu.component.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.component.databinding.ActivityMessengerBinding;
import com.mackwu.component.service.MessengerService;
import com.mackwu.component.ui.viewmodel.MainViewModel;

/**
 * @author MackWu
 * @since 2023/6/19 10:56
 */
public class MessengerActivity extends BaseActivity<MainViewModel, ActivityMessengerBinding> {

    private Messenger messenger;
    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            messenger = new Messenger(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            messenger = null;
        }
    };

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        bindService();

        binding.btnTest.setOnClickListener(v -> {
            sendMessaged();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService();
    }

    public void bindService() {
        bindService(new Intent(this, MessengerService.class), serviceConnection, BIND_AUTO_CREATE);
    }

    public void unbindService() {
        unbindService(serviceConnection);
    }

    public void sendMessaged() {
        try {
            Message message = Message.obtain();
            message.obj = 1;
            messenger.send(message);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

}
