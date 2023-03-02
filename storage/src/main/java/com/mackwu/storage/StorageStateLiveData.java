package com.mackwu.storage;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.jeremyliao.liveeventbus.core.Observable;
import com.mackwu.storage.bean.StorageState;

/**
 * @author MackWu
 * @since 2022/7/5 17:53
 */
public class StorageStateLiveData {

    private StorageStateLiveData() {
    }

    public static Observable<StorageState> getInstance() {
        return LiveEventBus.get(StorageState.class);
    }

}
