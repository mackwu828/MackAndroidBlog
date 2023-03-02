// IStorageScanService.aidl
package com.mackwu.storage.scan;

// Declare any non-default types here with import statements
import com.mackwu.storage.bean.StorageScanParam;
import com.mackwu.storage.scan.IStorageScanListener;

interface IStorageScanService {

    /**
     * 开始扫描
     */
    void startScan(in StorageScanParam param, in IStorageScanListener listener);

    /**
     * 取消扫描
     */
    void cancelScan();
}