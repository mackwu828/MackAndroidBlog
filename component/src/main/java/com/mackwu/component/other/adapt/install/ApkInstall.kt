package com.mackwu.component.other.adapt.install

import java.io.File

/**
 * ===================================================
 * Created by MackWu on 2019/12/31 10:34
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
interface ApkInstall {

    /**
     * 文件夹路径
     */
    val dirPath: String

    /**
     * Android7.0应用间共享文件。
     * Android7.0对文件的访问权限做了限制，不能使用 `file://` 格式的 URI 访问文件，否则会出现 FileUriExposedException 异常。
     * Android7.0需要使用 `content://` 格式的 URI 访问文件，并授予 URI 临时访问权限，授权的最简单方式是使用 FileProvider 类。
     */
    fun installApk(apkFile: File)

    fun installApk()

    /**
     * Android8.0未知应用安装权限
     * 加了权限系统会自动判断是否有权限，但是会有一个弹窗让用户是否跳转到设置页面。可以判断权限主动跳转到设置页面。
     */
    fun requestPackageInstalls(requestCode: Int, onInstall: () -> Unit)

    fun requestPackageInstalls(requestCode: Int, apkFile: File)

    fun requestPackageInstalls(requestCode: Int)

}