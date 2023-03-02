package com.mackwu.storage.scan;

import android.content.Context;

import androidx.annotation.NonNull;

import com.mackwu.storage.bean.StorageScanParam;

/**
 * @author MackWu
 * @since 2023/1/31 16:25
 */
public class StorageScannerImpl implements StorageScanner {

    Context context;
    StorageScanClient storageScanClient;

    public StorageScannerImpl(StorageScanner.Builder builder) {
        this.context = builder.context;
        this.storageScanClient = new StorageScanClient(context, convertScanParam(builder));
    }

    @Override
    public void startScan(@NonNull StorageScanListener storageScanListener) {
        if (storageScanClient != null) {
            storageScanClient.startScan(storageScanListener);
        }
    }

    @Override
    public void cancelScan() {
        if (storageScanClient != null) {
            storageScanClient.cancelScan();
        }
    }

    private StorageScanParam convertScanParam(StorageScanner.Builder builder){
        StorageScanParam param = new StorageScanParam();
        param.setRootPath(builder.rootPath);
        param.setUniqueId(builder.uniqueId);
        param.setExceptPaths(builder.exceptPaths);
        param.setFileMaxSize(builder.fileMaxSize);
        return param;
    }

}
