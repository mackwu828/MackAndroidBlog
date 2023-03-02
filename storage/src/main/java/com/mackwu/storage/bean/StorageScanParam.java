package com.mackwu.storage.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author MackWu
 * @since 2023/2/16 18:00
 */
public class StorageScanParam implements Parcelable {
    // 唯一id
    String uniqueId;
    // 根路径
    String rootPath;
    // 忽略的路径
    List<String> exceptPaths;
    // 文件最大大小
    long fileMaxSize;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uniqueId);
        dest.writeString(this.rootPath);
        dest.writeStringList(this.exceptPaths);
        dest.writeLong(this.fileMaxSize);
    }

    public void readFromParcel(Parcel source) {
        this.uniqueId = source.readString();
        this.rootPath = source.readString();
        this.exceptPaths = source.createStringArrayList();
        this.fileMaxSize = source.readLong();
    }

    public StorageScanParam() {
    }

    protected StorageScanParam(Parcel in) {
        this.uniqueId = in.readString();
        this.rootPath = in.readString();
        this.exceptPaths = in.createStringArrayList();
        this.fileMaxSize = in.readLong();
    }

    public static final Parcelable.Creator<StorageScanParam> CREATOR = new Parcelable.Creator<StorageScanParam>() {
        @Override
        public StorageScanParam createFromParcel(Parcel source) {
            return new StorageScanParam(source);
        }

        @Override
        public StorageScanParam[] newArray(int size) {
            return new StorageScanParam[size];
        }
    };

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public List<String> getExceptPaths() {
        return exceptPaths;
    }

    public void setExceptPaths(List<String> exceptPaths) {
        this.exceptPaths = exceptPaths;
    }

    public long getFileMaxSize() {
        return fileMaxSize;
    }

    public void setFileMaxSize(long fileMaxSize) {
        this.fileMaxSize = fileMaxSize;
    }
}
