package com.mackwu.storage.scan;

import static android.content.Context.BIND_AUTO_CREATE;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.mackwu.storage.bean.StorageScanParam;
import com.mackwu.storage.util.Logger;

/**
 * @author MackWu
 * @since 2023/2/16 17:47
 */
public class StorageScanClient {

    Context context;
    IStorageScanService binder;
    StorageScanParam param;
    StorageScanListener storageScanListener;

    private final ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            Logger.d("onServiceConnected...");
            binder = IStorageScanService.Stub.asInterface(service);
            // 监听服务端是否死亡
            try {
                binder.asBinder().linkToDeath(serviceDeathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            startRemoteScan();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Logger.d("onServiceDisconnected...");
            binder = null;
        }
    };
    private final IBinder.DeathRecipient serviceDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Logger.d("binderDied...  storage service is dead");
            binder.asBinder().unlinkToDeath(this, 0);
        }
    };


    public StorageScanClient(Context context, StorageScanParam param) {
        this.context = context;
        this.param = param;
    }

    /**
     * 开始扫描
     *
     * @param storageScanListener 扫描监听
     */
    public void startScan(StorageScanListener storageScanListener) {
        this.storageScanListener = storageScanListener;
        if (!isServiceConnected()) {
            Intent intent = new Intent(context, StorageScanService.class);
            context.bindService(intent, conn, BIND_AUTO_CREATE);
        } else {
            startRemoteScan();
        }
    }

    public void cancelScan() {
    }

    /**
     * 开始远程扫描。扫描流程在服务端执行，回调在客户端。
     */
    private void startRemoteScan() {
        Logger.d("startRemoteScan...  pid=" + android.os.Process.myPid());
        try {
            binder.startScan(param, new IStorageScanListener.Stub() {
                @Override
                public void onScanStart() throws RemoteException {
                    Logger.d("onScanStart...  pid=" + android.os.Process.myPid());
                    if (storageScanListener != null) {
                        storageScanListener.onScanStart();
                    }
                }

                @Override
                public void onScanProgressChanged(String path) throws RemoteException {
                    //                    Logger.d("onScanProgressChanged...  pid=" + android.os.Process.myPid());
                    if (storageScanListener != null) {
                        storageScanListener.onScanProgressChanged(path);
                    }
                }

                @Override
                public void onScanComplete() throws RemoteException {
                    Logger.d("onScanComplete...  pid=" + android.os.Process.myPid());
                    if (storageScanListener != null) {
                        storageScanListener.onScanComplete();
                    }
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务是否连接
     */
    private boolean isServiceConnected() {
        return binder != null;
    }

}
