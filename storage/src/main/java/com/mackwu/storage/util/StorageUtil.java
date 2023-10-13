package com.mackwu.storage.util;

import android.annotation.SuppressLint;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;

import com.mackwu.storage.bean.Storage;
import com.mackwu.storage.bean.StorageSize;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2022/1/11 16:17
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * Android5.0 => StorageManager#getVolumeList => StorageVolume
 * Android6.0 => StorageManager#getVolumes => VolumeInfo
 */
public final class StorageUtil {

    /**
     * 获取存储数据
     */
    @SuppressWarnings({"ConstantConditions", "JavaReflectionMemberAccess"})
    public static List<Storage> getStorages(Context context) {
        try {
            StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
            // storageVolumes
            Method getVolumeListMethod = storageManager.getClass().getMethod("getVolumeList");
            StorageVolume[] storageVolumes = (StorageVolume[]) getVolumeListMethod.invoke(storageManager);
            // storageVolume
            Class<?> storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method isRemovableMethod = storageVolumeClazz.getMethod("isRemovable");
            Method getStateMethod = storageVolumeClazz.getMethod("getState");
            Method getPathFileMethod = storageVolumeClazz.getMethod("getPathFile");
            Method getUserLabelMethod = storageVolumeClazz.getMethod("getUserLabel");
            if (storageVolumes != null && storageVolumes.length > 0) {
                List<Storage> storages = new ArrayList<>();
                for (StorageVolume storageVolume : storageVolumes) {
                    // 是否是外置存储
                    boolean isRemovable = (boolean) isRemovableMethod.invoke(storageVolume);
                    // 是否挂载
                    String state = (String) getStateMethod.invoke(storageVolume);
                    // 路径
                    File pathFile = (File) getPathFileMethod.invoke(storageVolume);
                    // 过滤无效路径
                    if (pathFile.getPath().contains("/dev/null")) {
                        continue;
                    }
                    // 名称
                    String userLabel = (String) getUserLabelMethod.invoke(storageVolume);
                    // 是否是USB
                    boolean isUsb = false;
                    List<Storage> usbStorages = getUsbStorages(context);
                    if (usbStorages != null && usbStorages.size() > 0) {
                        for (Storage usbStorage : usbStorages) {
                            if (pathFile.getAbsolutePath().equals(usbStorage.getPath())) {
                                isUsb = true;
                            }
                        }
                    }
                    // storage
                    Storage storage = new Storage();
                    storage.setMounted(Environment.MEDIA_MOUNTED.equals(state));
                    storage.setRemovable(isRemovable);
                    storage.setPath(pathFile.getAbsolutePath());
                    storage.setName(userLabel);
                    storage.setUsb(isUsb);
                    storages.add(storage);
                    Logger.d(storage.toString());
                }
                return storages;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings({"ConstantConditions"})
    @SuppressLint({"PrivateApi", "DiscouragedPrivateApi"})
    private static List<Storage> getUsbStorages(Context context) {
        try {
            StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
            Method getVolumesMethod = storageManager.getClass().getMethod("getVolumes");
            List<?> volumes = (List<?>) getVolumesMethod.invoke(storageManager);
            // VolumeInfo
            Class<?> volumeInfoClazz = Class.forName("android.os.storage.VolumeInfo");
            Method getPathMethod = volumeInfoClazz.getMethod("getPath");
            Method getDescriptionMethod = volumeInfoClazz.getMethod("getDescription");
            Method getDiskMethod = volumeInfoClazz.getMethod("getDisk");
            // DiskInfo
            Class<?> diskInfoClazz = Class.forName("android.os.storage.DiskInfo");
            Method isUsbMethod = diskInfoClazz.getMethod("isUsb");
            if (volumes != null && volumes.size() > 0) {
                List<Storage> usbStorages = new ArrayList<>();
                for (Object volumeInfo : volumes) {
                    // 路径
                    File pathFile = (File) getPathMethod.invoke(volumeInfo);
                    // 路径为空
                    if (pathFile == null) {
                        continue;
                    }
                    // 过滤无效路径
                    if (pathFile.getPath().contains("/dev/null") || pathFile.getPath().contains("/data")) {
                        continue;
                    }
                    // 描述。就是名称
                    String description = (String) getDescriptionMethod.invoke(volumeInfo);
                    // 是否是USB
                    Object diskInfo = getDiskMethod.invoke(volumeInfo);
                    boolean isUsb = diskInfo != null && (boolean) isUsbMethod.invoke(diskInfo);
                    if (isUsb) {
                        Storage storage = new Storage();
                        storage.setUsb(true);
                        storage.setPath(pathFile.getAbsolutePath());
                        storage.setName(description);
                        usbStorages.add(storage);
                    }
                }
                return usbStorages;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings({"ConstantConditions"})
    @SuppressLint({"PrivateApi", "DiscouragedPrivateApi"})
    private static List<Storage> getAndroid6Storages(Context context) {
        try {
            StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
            // volumes
            Method getVolumesMethod = storageManager.getClass().getMethod("getVolumes");
            List<?> volumes = (List<?>) getVolumesMethod.invoke(storageManager);
            // VolumeInfo
            Class<?> volumeInfoClazz = Class.forName("android.os.storage.VolumeInfo");
            Method getStateMethod = volumeInfoClazz.getMethod("getState");
            Method getPathMethod = volumeInfoClazz.getMethod("getPath");
            Method getDescriptionMethod = volumeInfoClazz.getMethod("getDescription");
            Method getDiskMethod = volumeInfoClazz.getMethod("getDisk");
            // DiskInfo
            Class<?> diskInfoClazz = Class.forName("android.os.storage.DiskInfo");
            Method isUsbMethod = diskInfoClazz.getMethod("isUsb");
            if (volumes != null && volumes.size() > 0) {
                List<Storage> storages = new ArrayList<>();
                for (Object volumeInfo : volumes) {
                    // 是否挂载
                    int state = (Integer) getStateMethod.invoke(volumeInfo);
                    // 路径
                    File pathFile = (File) getPathMethod.invoke(volumeInfo);
                    // 过滤无效路径
                    if (pathFile.getPath().contains("/dev/null") || pathFile.getPath().contains("/data")) {
                        continue;
                    }
                    // 描述。就是名称
                    String description = (String) getDescriptionMethod.invoke(volumeInfo);
                    // 是否是USB
                    Object diskInfo = getDiskMethod.invoke(volumeInfo);
                    boolean isUsb = diskInfo != null && (boolean) isUsbMethod.invoke(diskInfo);
                    // storage
                    Storage storage = new Storage();
                    storage.setMounted(state == 2);
                    storage.setPath(pathFile.getAbsolutePath());
                    storage.setName(description);
                    storage.setUsb(isUsb);
                    storages.add(storage);
                }
                return storages;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取存储大小
     *
     * @param context context
     * @param path    存储路径
     */
    @SuppressLint("UsableSpace")
    @SuppressWarnings({"ConstantConditions", "JavaReflectionMemberAccess"})
    public static StorageSize getStorageSize(Context context, String path) {
        try {
            File pathFile = new File(path);
            // 总大小。Android7.1以上总大小包含系统存储空间大小
            long totalSize;
            // Android8.0以上包括8.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                StorageStatsManager storageStatsManager = context.getSystemService(StorageStatsManager.class);
                totalSize = storageStatsManager.getTotalBytes(StorageManager.UUID_DEFAULT);
            }
            // Android7.1以上包括7.1
            else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
                totalSize = (long) StorageManager.class.getMethod("getPrimaryStorageSize").invoke(storageManager);
                if (totalSize <= 0) {
                    // copy sourcecode
//                    totalSize = new FixStorageApi().getPrimaryStorageSize_N_MR1();
                }
            }
            // Android7.1以下
            else {
                totalSize = pathFile.getTotalSpace();
            }
            // 可用大小
            long availableSize;
            // Android8.0以上包括8.getUsableSpace更加精确，以下用getFreeSpace
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                availableSize = pathFile.getUsableSpace();
            } else {
                availableSize = pathFile.getFreeSpace();
            }
            // storageSize
            StorageSize storageSize = new StorageSize();
            storageSize.setTotalSize(totalSize);
            storageSize.setSystemSize(totalSize - pathFile.getTotalSpace());
            storageSize.setAvailableSize(availableSize);
            storageSize.setUsedSize(totalSize - availableSize);
//            Logger.d(storageSize.toString());
            return storageSize;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取存储单位。Android8.0包括8.0用1000，Android8.0以下用1024
     */
    public static int getStorageUnit() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? 1000 : 1024;
    }

    /*
     * * * * * * * * 外置存储 * * * * * * * *
     */
    public static Storage getExternalStorage(Context context) {
        try {
            List<Storage> storages = getStorages(context);
            if (storages != null && storages.size() > 0) {
                for (Storage storage : storages) {
                    if (storage.isRemovable()) {
                        return storage;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Storage getExternalStorage(Context context, String path) {
        try {
            List<Storage> storages = getStorages(context);
            if (storages != null && storages.size() > 0) {
                for (Storage storage : storages) {
                    if (storage.isRemovable() && storage.getPath().equals(path)) {
                        return storage;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Storage> getExternalStorages(Context context) {
        List<Storage> externalStorages = new ArrayList<>();
        try {
            List<Storage> storages = getStorages(context);
            if (storages != null && storages.size() > 0) {
                for (Storage storage : storages) {
                    if (storage.isRemovable()) {
                        externalStorages.add(storage);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return externalStorages;
    }

    /**
     * 外置存储是否挂载
     */
    public static boolean isExternalStorageMounted(Context context) {
        try {
            Storage externalStorage = getExternalStorage(context);
            if (externalStorage != null) {
                return externalStorage.isMounted();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取外置存储根路径。
     *
     * @return /storage/2CB4-CAE8
     */
    public static String getExternalStoragePath(Context context) {
        try {
            Storage externalStorage = getExternalStorage(context);
            if (externalStorage != null) {
                return externalStorage.getPath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取外置存储剩余可用大小。
     * Use StatFs may cause java.lang.IllegalArgumentException: Invalid path: /storage/0000-0000
     * StatFs statFs = new StatFs(path);
     * statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
     *
     * @param path 外置存储路径
     */
    public static long getExternalStorageAvailableSize(Context context, String path) {
        try {
            StorageSize storageSize = getStorageSize(context, path);
            if (storageSize != null) {
                return storageSize.getAvailableSize();
            }
//            StatFs statFs = new StatFs(path);
//            return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /*
     * * * * * * * * 内置存储 * * * * * * * *
     */

    /**
     * 获取内置存储。
     * 可直接用系统API:
     * 内置存储路径: Environment.getExternalStorageDirectory().getPath()
     * 内置存储是否挂载: Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
     *
     * @param context context
     */
    public static Storage getInternalStorage(Context context) {
        List<Storage> storages = getStorages(context);
        if (storages != null && storages.size() > 0) {
            for (Storage storage : storages) {
                if (!storage.isRemovable()) {
                    return storage;
                }
            }
        }
        return null;
    }

    /**
     * 内置存储是否挂载
     * Environment.getExternalStorageState()
     */
    public static boolean isInternalStorageMounted() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取内置存储路径。指的是内置sdcard
     * <p>
     * 内置sdcard存储
     * => Environment.getExternalStorageDirectory() => Android4.1之前：/mnt/sdcard。Android4.1之后：/storage/emulated/0
     * <p>
     * 内置sdcard存储下的一些分类
     * => Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC) => /storage/emulated/0/Music
     * => Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) => /storage/emulated/0/Download
     * <p>
     * 内置sdcard存储下的应用内部私有存储
     * => getExternalFilesDir(null) => /storage/emulated/0/Android/data/com.mackwu.component/files
     * => getExternalCacheDir() => /storage/emulated/0/Android/data/com.mackwu.component/cache
     * <p>
     * 应用内部私有存储。Android5.0之前data/data/ Android5.0之后引入多用户功能后/data/user/0
     * => getFilesDir() => /data/user/0/com.mackwu.component/files
     * => getCacheDir() => /data/user/0/com.mackwu.component/cache
     *
     * @return 内置存储路径
     */
    public static String getInternalStoragePath() {
        String path = Environment.getExternalStorageDirectory().getPath();
        if (!isInternalStorageMounted()) {
            path = Environment.getDataDirectory().getPath();
        }
        return path;
    }


    /**
     * 获取内置存储总大小。
     * Android7.1以上包含7.1，内置存储总大小包含系统存储大小，只获取内置sdcard存储大小不准确。
     *
     * @param context context
     */
    public static long getInternalStorageTotalSize(Context context) {
        try {
            StorageSize storageSize = getStorageSize(context, getInternalStoragePath());
            if (storageSize != null) {
                return storageSize.getTotalSize();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取内置存储剩余可用大小。
     *
     * @param context context
     */
    public static long getInternalStorageAvailableSize(Context context) {
        try {
            StorageSize storageSize = getStorageSize(context, getInternalStoragePath());
            if (storageSize != null) {
                return storageSize.getAvailableSize();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
