package com.mackwu.storage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.mackwu.storage.bean.StorageState;
import com.mackwu.storage.util.Logger;

/**
 * @author MackWu
 * @since 2022/8/26 11:49
 */
public class StorageStateReceiver extends BroadcastReceiver implements DefaultLifecycleObserver {

    Context context;
    IntentFilter intentFilter;
    Handler handler = new Handler();

    public StorageStateReceiver(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.d("StorageStateReceiver onReceive...  " + intent.getAction());
        String path = intent.getData().getPath();
        // 存储挂载
        if (Intent.ACTION_MEDIA_MOUNTED.equals(intent.getAction())) {
            StorageStateLiveData.getInstance().post(new StorageState(true, path));
        }
        // 存储移除
        else {
            handler.removeCallbacksAndMessages(null);
            handler.postDelayed(() -> StorageStateLiveData.getInstance().post(new StorageState(false, path)), 500);
        }
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        this.intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        intentFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
        intentFilter.addAction(Intent.ACTION_MEDIA_REMOVED);
        intentFilter.addAction(Intent.ACTION_MEDIA_BAD_REMOVAL);
        intentFilter.addAction(Intent.ACTION_MEDIA_EJECT);
        intentFilter.addDataScheme("file");
        context.registerReceiver(this, intentFilter);
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        context.unregisterReceiver(this);
    }

}
