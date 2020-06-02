package com.mackwu.component.other.adapt.install

import android.content.Context
import com.mackwu.component.util.logD
import java.io.File
import java.util.concurrent.Executors

/**
 * ===================================================
 * Created by MackWu on 2019/12/24 19:00
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
abstract class AssetApkInstaller(context: Context) : ApkInstaller(context), AssetApkInstall {

    private val executorService = Executors.newFixedThreadPool(20)

    private fun copyAssetApk(onCopy: (assetApk: File) -> Unit) {
        executorService.submit {
            try {
                // 文件夹路径
                logD(TAG, "dirPath: $dirPath")
                val dir = File(dirPath)
                // 获取文件夹读取权限
                chmod(dirPath)
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                logD(TAG, "dir exists: " + dir.exists())
                logD(TAG, "dir isDirectory: " + dir.isDirectory)

                // 生成文件
                val assetApk = File(dirPath, assetName.replace("aac", "apk"))
                // 获取文件读取权限
                chmod(assetApk.absolutePath)

                // 写入
                val input = context.assets.open(assetName)
                input.use { `in` -> assetApk.outputStream().use { out -> `in`.copyTo(out) } }
                logD(TAG, "assetApk exists: " + assetApk.exists())
                logD(TAG, "assetApk isFile: " + assetApk.isFile)
                logD(TAG, "assetApk length: " + assetApk.length())

                onCopy.invoke(assetApk)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun installApk() {
        copyAssetApk { assetApk -> super.installApk(assetApk) }
    }

    override fun requestPackageInstalls(requestCode: Int) {
        copyAssetApk { assetApk -> super.requestPackageInstalls(requestCode, assetApk) }
    }

    companion object {
        private val TAG = AssetApkInstaller::class.java.simpleName
    }
}