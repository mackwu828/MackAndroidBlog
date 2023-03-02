package com.mackwu.storage;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;

import com.mackwu.storage.bean.Storage;
import com.mackwu.storage.util.Logger;
import com.mackwu.storage.util.StorageUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MackWu
 * @since 2022/9/1 16:50
 */
public class StorageManager {

    private static StorageManager instance;
    private final Map<String, Storage> cachedMountedExternalStorageMap = new LinkedHashMap<>();

    private StorageManager() {
    }

    public static StorageManager getInstance() {
        if (instance == null) {
            instance = new StorageManager();
        }
        return instance;
    }

    /**
     * 绑定存储设备状态变化广播。
     *
     * @param context context
     */
    public void bindStorageObserver(Context context) {
        Logger.d("bindStorageObserver...");
        if (context instanceof LifecycleOwner) {
            ((LifecycleOwner) context).getLifecycle().addObserver(new StorageStateReceiver(context));
            StorageStateLiveData.getInstance().observe((LifecycleOwner) context, storageState -> {
                boolean isMounted = storageState.isMounted();
                String path = storageState.getPath();
                Logger.d("bindStorageObserver callback...  isMounted=" + isMounted + ", path=" + path);
                if (isMounted) {
                    Storage externalStorage = StorageUtil.getExternalStorage(context, path);
                    if (externalStorage != null) {
                        cachedMountedExternalStorageMap.put(path, externalStorage);
                    }
                } else {
                    cachedMountedExternalStorageMap.remove(path);
                }
                Logger.d("bindStorageObserver callback completed...  mountedExternalStorageMap=" + cachedMountedExternalStorageMap);
            });
        }
    }

    /**
     * 获取已挂载的外置存储。
     * 为什么要缓存外置存储？
     *
     * @param context context
     */
    public List<Storage> getMountedExternalStorages(Context context) {
        List<Storage> mountedExternalStorages = new ArrayList<>();
        List<Storage> externalStorages = StorageUtil.getExternalStorages(context);
        if (externalStorages.size() > 0) {
            for (Storage externalStorage : externalStorages) {
                if (externalStorage.isMounted()) {
                    cachedMountedExternalStorageMap.put(externalStorage.getPath(), externalStorage);
                }
            }
        }
        for (Map.Entry<String, Storage> entry : cachedMountedExternalStorageMap.entrySet()) {
            mountedExternalStorages.add(entry.getValue());
        }
        Logger.d("getMountedExternalStorages...  " + mountedExternalStorages);
        return mountedExternalStorages;
    }

}
