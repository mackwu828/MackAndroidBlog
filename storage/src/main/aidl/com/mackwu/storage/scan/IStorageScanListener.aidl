// IStorageScanListener.aidl
package com.mackwu.storage.scan;

// Declare any non-default types here with import statements
interface IStorageScanListener {

    /**
     * 扫描开始
     */
    void onScanStart();

    /**
     * 扫描进度回调
     *
     * @param file file
     */
    void onScanProgressChanged(String path);

    /**
     * 扫描完成
     */
    void onScanComplete();
}