package com.mackwu.storage.bean;

import com.jeremyliao.liveeventbus.core.LiveEvent;

/**
 * @author MackWu
 * @since 2023/1/31 16:04
 */
public class StorageState implements LiveEvent {

    private boolean isMounted;
    private String path;

    public StorageState(boolean isMounted, String path) {
        this.isMounted = isMounted;
        this.path = path;
    }

    public boolean isMounted() {
        return isMounted;
    }

    public void setMounted(boolean mounted) {
        isMounted = mounted;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
