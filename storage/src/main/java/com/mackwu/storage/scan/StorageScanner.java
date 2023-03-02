package com.mackwu.storage.scan;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * @author MackWu
 * @since 2022/5/18 11:11
 */
public interface StorageScanner {

    /**
     * 开始扫描
     */
    void startScan(@NonNull StorageScanListener listener);

    /**
     * 取消扫描
     */
    void cancelScan();

    class Builder {
        Context context;
        // 唯一id
        String uniqueId;
        // 根路径
        String rootPath;
        // 忽略的路径
        List<String> exceptPaths;
        // 文件最大大小
        long fileMaxSize;

        public Builder(Context context) {
            this.context = context;
            this.fileMaxSize = Integer.MAX_VALUE;
        }

        /**
         * 唯一id
         *
         * @param uniqueId 唯一id
         */
        public Builder uniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
            return this;
        }

        /**
         * 根路径
         *
         * @param rootPath 外置存储根路径
         */
        public Builder rootPath(String rootPath) {
            this.rootPath = rootPath;
            return this;
        }

        /**
         * 忽略的路径
         *
         * @param exceptPaths 不搜索该路径下的文件
         */
        public Builder exceptPaths(List<String> exceptPaths) {
            this.exceptPaths = exceptPaths;
            return this;
        }

        /**
         * 文件最大大小
         *
         * @param fileMaxSize 文件最大大小
         */
        public Builder fileMaxSize(long fileMaxSize) {
            this.fileMaxSize = fileMaxSize;
            return this;
        }

        public StorageScanner build() {
            return new StorageScannerImpl(this);
        }
    }

}
