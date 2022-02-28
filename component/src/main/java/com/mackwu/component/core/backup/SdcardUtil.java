package com.mackwu.component.core.backup;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.util.Pair;

import java.io.File;
import java.lang.reflect.Method;

/**
 * ===================================================
 * Created by MackWu on 2022/1/11 16:17
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class SdcardUtil {

    /**
     * 外置sdcard是否挂载
     */
    public static boolean isExternalSdCardMounted(Context context) {
        Pair<Boolean, String> externalSdCardState = getExternalSdCardInfo(context);
        if (externalSdCardState != null) {
            return externalSdCardState.first;
        }
        return false;
    }

    /**
     * 获取外置sdcard根路径。
     *
     * @return /storage/2CB4-CAE8
     */
    public static String getExternalSdCardPath(Context context) {
        Pair<Boolean, String> externalSdCardState = getExternalSdCardInfo(context);
        if (externalSdCardState != null) {
            return externalSdCardState.second;
        }
        return null;
    }

    /**
     * 获取外置sdcard剩余可用空间。
     */
    public static long getExternalSdCardFreeSpace(Context context) {
        String externalSdCardPath = getExternalSdCardPath(context);
        if (externalSdCardPath != null) {
            File file = new File(externalSdCardPath);
            if (file.exists()) {
                return file.getFreeSpace();
            }
        }
        return -1;
    }

    /**
     * 获取外置sdcard信息
     *
     * @return Pair<Boolean, String> => <外置sdcard是否挂载，外置sdcard根路径>
     */
    @SuppressWarnings({"JavaReflectionMemberAccess", "ConstantConditions"})
    public static Pair<Boolean, String> getExternalSdCardInfo(Context context) {
        StorageManager storageManager = (StorageManager) context.getApplicationContext().getSystemService(Context.STORAGE_SERVICE);
        try {
            Method getVolumeList = StorageManager.class.getMethod("getVolumeList");
            getVolumeList.setAccessible(true);
            StorageVolume[] storageVolumes = (StorageVolume[]) getVolumeList.invoke(storageManager);
            if (storageVolumes != null && storageVolumes.length > 0) {
                for (StorageVolume storageVolume : storageVolumes) {
                    boolean isRemovable = (boolean) storageVolume.getClass().getMethod("isRemovable").invoke(storageVolume);
                    String state = (String) storageVolume.getClass().getMethod("getState").invoke(storageVolume);
                    File pathFile = (File) storageVolume.getClass().getMethod("getPathFile").invoke(storageVolume);
                    boolean isMounted = Environment.MEDIA_MOUNTED.equals(state);
                    String path = pathFile.getAbsolutePath();
                    if (isRemovable) {
                        return new Pair<>(isMounted, path);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 内置sdcard是否挂载
     */
    public static boolean isBuildInSdCardMounted() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取内置sdcard根路径。
     *
     * @return Android4.1之前：/mnt/sdcard。Android4.1之后：/storage/emulated/0
     */
    public static String getBuildInSdcardRootPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

}
