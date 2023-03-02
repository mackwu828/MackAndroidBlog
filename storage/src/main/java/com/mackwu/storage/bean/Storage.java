package com.mackwu.storage.bean;

/**
 * @author MackWu
 * @since 2023/1/18 18:01
 */
public class Storage {
    // 是否挂载
    private boolean isMounted;
    // 是否是外置存储
    private boolean isRemovable;
    // 路径
    private String path;
    // 名称
    private String name;
    // 是否是USB
    private boolean isUsb;

    public boolean isMounted() {
        return isMounted;
    }

    public void setMounted(boolean mounted) {
        isMounted = mounted;
    }

    public boolean isRemovable() {
        return isRemovable;
    }

    public void setRemovable(boolean removable) {
        isRemovable = removable;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUsb() {
        return isUsb;
    }

    public void setUsb(boolean usb) {
        isUsb = usb;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "isMounted=" + isMounted +
                ", isRemovable=" + isRemovable +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", isUsb=" + isUsb +
                '}';
    }
}
