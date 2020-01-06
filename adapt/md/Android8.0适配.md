
未知应用安装权限

## 未知应用安装权限
在Android8.0的手机上安装7.0的方式无法跳转到正常的APP安装页面。具体表现就是：apk下载完成,一闪而过，没有跳转到apk安装界面

Android8.0以上，未知来源的应用是不可以通过代码来执行安装的（在sd卡中找找到apk,手动安装是可以的），
未知应用安装权限的开关被除掉，取而代之的是未知来源应用的管理列表，需要列表里面开启你的应用的未知来源的安装权限。
Google这么做是为了防止一开始正经的应用后来开始通过升级来做一些不合法的事情，侵犯用户权益。

加了权限系统会自动判断是否有权限，但是会有一个弹窗让用户是否跳转到设置页面。
```
<uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES"/>
```

可以判断权限主动跳转到设置页面。
```
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val hasInstallPermission = activity.packageManager.canRequestPackageInstalls()
            Log.d(TAG, "hasInstallPermission: $hasInstallPermission")
            if (!hasInstallPermission) {
                // 跳转至“安装未知应用”权限界面，引导用户开启权限
                val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:" + activity.packageName))
                activity.startActivityForResult(intent, requestCode)
                return
            }
            // 直接安装
        }
```