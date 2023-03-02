package com.mackwu.storage.bean;

import com.mackwu.storage.util.ByteUtil;

/**
 * @author MackWu
 * @since 2023/1/18 18:01
 */
public class StorageSize {
    // 总大小
    private long totalSize;
    // 系统大小。Android7.1以上包括7.1存在系統空间
    private long systemSize;
    // 可用大小
    private long availableSize;
    // 已用大小
    private long usedSize;

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public long getSystemSize() {
        return systemSize;
    }

    public void setSystemSize(long systemSize) {
        this.systemSize = systemSize;
    }

    public long getAvailableSize() {
        return availableSize;
    }

    public void setAvailableSize(long availableSize) {
        this.availableSize = availableSize;
    }

    public long getUsedSize() {
        return usedSize;
    }

    public void setUsedSize(long usedSize) {
        this.usedSize = usedSize;
    }

    @Override
    public String toString() {
        return "StorageSize{" +
                "totalSize=" + totalSize + "(" + ByteUtil.bytesToStrByAndroidVersion(totalSize) + ")" +
                ", systemSize=" + systemSize + "(" + ByteUtil.bytesToStrByAndroidVersion(systemSize) + ")" +
                ", availableSize=" + availableSize + "(" + ByteUtil.bytesToStrByAndroidVersion(availableSize) + ")" +
                ", usedSize=" + usedSize + "(" + ByteUtil.bytesToStrByAndroidVersion(usedSize) + ")" +
                '}';
    }
}
