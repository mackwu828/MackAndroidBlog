package com.mackwu.storage.scan;

/**
 * @author MackWu
 * @since 2023/2/16 18:26
 */
public interface StorageScanListener {

    /**
     * 扫描开始
     */
    void onScanStart();

    /**
     * 扫描进度回调
     *
     * @param path 文件路径
     */
    void onScanProgressChanged(String path);

    /**
     * 扫描完成
     */
    void onScanComplete();
}
