


window的添加、移除和更新?


window如何获取焦点？
window的层级？https://www.jianshu.com/p/4077c1c4ff27
window不同层级需要的权限？


## 如何添加悬浮窗
```
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowLayoutParams = new WindowManager.LayoutParams();
        windowLayoutParams.width = 300;
        windowLayoutParams.height = 300;
        view = LayoutInflater.from(context).inflate(R.layout.window_layout_test, null);
        if (!view.isShown()) {
            windowManager.addView(view, windowLayoutParams);
        } else {
            windowManager.updateViewLayout(view, windowLayoutParams);
        }
```

```
    android.view.WindowManager$BadTokenException: Unable to add window android.view.ViewRootImpl$W@f3ceb7e -- permission denied for window type 2038
```
添加悬浮窗权限。
```
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
```
动态申请悬浮窗权限。
```
    /**
     * 申请悬浮窗权限。
     * Android6.0(包括6.0)以上需要申请悬浮窗权限
     */
    public static void requestSystemAlertWindow(FragmentActivity activity, OnPermissionGrantedListener listener) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (canDrawOverlays(activity)) {
                if (listener != null) listener.OnPermissionGranted();
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.setData(Uri.parse("package:" + activity.getPackageName()));
                activity.startActivityForResult(intent, SYSTEM_ALERT_WINDOW_REQUEST_CODE);
            }
        }
    }

    /**
     * 是否有悬浮窗权限。
     */
    public static boolean canDrawOverlays(FragmentActivity activity) {
        boolean canDrawOverlays = true;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            canDrawOverlays = Settings.canDrawOverlays(activity);
        }
        return canDrawOverlays;
    }
```

android.view.WindowManager$BadTokenException: Unable to add window android.view.ViewRootImpl$W@5cfb873 -- permission denied for window type 2003
