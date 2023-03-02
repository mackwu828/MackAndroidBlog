package com.mackwu.component.func.cache;

import android.content.Context;
import android.os.Environment;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;

/**
 * @author MackWu
 * @since 2022/9/27 14:55
 */
public class CacheManager {

    public void a(Context context) {

        try {
            File cacheDir = getDiskCacheDir(context, "bitmap");
            int appVersion = getAppVersion();
            // 第三个参数指同一个key可以对应多少个缓存文件，基本都是传1，
            // 第四个参数指定最多可以缓存多少字节的数据。
            DiskLruCache diskLruCache = DiskLruCache.open(cacheDir, appVersion, 1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * sd卡已挂载 => /mnt/sdcard/Android/data/<application package>/cache
     * sd卡未挂载 => /data/user/0/<application package>/cache
     *
     * @param context context
     * @return 磁盘缓存文件夹
     */
    public File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath, uniqueName);
    }

    /**
     * 获取app版本号。
     * 每当版本号改变，缓存路径下存储的所有数据都会被清除掉，因为DiskLruCache认为当应用程序有版本更新的时候，所有的数据都应该从网上重新获取。
     *
     * @return app版本号
     */
    public int getAppVersion() {
        return 1;
    }

}
