package com.mackwu.component.other.adapt.install

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.core.content.FileProvider
import com.mackwu.component.util.kt.logD
import java.io.File

/**
 * ===================================================
 * Created by MackWu on 2019/12/31 10:49
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
open class ApkInstaller(val context: Context) : ApkInstall {

    override val dirPath: String
        get() = if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            Environment.getExternalStorageDirectory().absolutePath + File.separator + context.packageName + File.separator + "apk"
        } else {
            context.cacheDir.absolutePath + File.separator + "apk"
        }

    /**
     * 获取读写权限。cacheDir、fileDir需要读取权限。需要有系统签名
     */
    protected fun chmod(path: String) {
        logD(TAG, "chmod...  path: $path")
        try {
            if (path.contains(context.filesDir.absolutePath) || path.contains(context.cacheDir.absolutePath)) {
               Runtime.getRuntime().exec("chmod 777 $path")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Android7.0应用间共享文件。
     * Android7.0对文件的访问权限做了限制，不能使用 `file://` 格式的 URI 访问文件，否则会出现 FileUriExposedException 异常。
     * Android7.0需要使用 `content://` 格式的 URI 访问文件，并授予 URI 临时访问权限，授权的最简单方式是使用 FileProvider 类。
     */
    override fun installApk(apkFile: File) {
        logD(TAG, "installApk...")
        // startActivity
        val intent = Intent(Intent.ACTION_VIEW)
                .apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
                .apply { addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) }
                .apply { setDataAndType(getFileUri(apkFile), "application/vnd.android.package-archive") }
        context.startActivity(intent)
    }

    override fun installApk() {

    }

    private fun getFileUri(file: File): Uri {
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(context, context.packageName + ".fileprovider", file)
        } else {
            Uri.fromFile(file)
        }
        logD(TAG, "uri: $uri")
        return uri
    }

    /**
     * Android8.0未知应用安装权限
     * 加了权限系统会自动判断是否有权限，但是会有一个弹窗让用户是否跳转到设置页面。可以判断权限主动跳转到设置页面。
     */
    override fun requestPackageInstalls(requestCode: Int, onInstall: () -> Unit) {
        logD(TAG, "requestPackageInstalls...")
        if (context !is Activity) {
            throw Exception("context must be a subclass of activity")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val hasInstallPermission = context.packageManager.canRequestPackageInstalls()
            logD(TAG, "hasInstallPermission: $hasInstallPermission")
            if (!hasInstallPermission) {
                // 跳转至“安装未知应用”权限界面，引导用户开启权限
                val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:" + context.packageName))
                context.startActivityForResult(intent, requestCode)
                return
            }
        }
        onInstall.invoke()
    }

    override fun requestPackageInstalls(requestCode: Int) {
    }

    override fun requestPackageInstalls(requestCode: Int, apkFile: File) {
        requestPackageInstalls(requestCode) { installApk(apkFile) }
    }

    companion object {
        private val TAG = ApkInstaller::class.java.simpleName
    }
}