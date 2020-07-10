

## 设置非全屏的Activity
### 一、设置Activity宽高
```
/**
 * 设置非全屏的Activity
 * @param width Activity宽度
 * @param height Activity高度
 * @param x
 * @param y
 * @param dimAmount Activity之外部分透明程度
 * @param isTouchOutside 响应activity对窗口之外的触摸消息
 */
fun Activity.setNonFullActivity(width: Int, height: Int, x: Int = 0, y: Int = 0, dimAmount: Float = 0.5f, isTouchOutside: Boolean = false) {
    val attributes = window.attributes
    attributes.width = width
    attributes.height = height
    attributes.x = x
    attributes.y = y
    attributes.dimAmount = dimAmount
    window.attributes = attributes
    if (isTouchOutside) window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL)
}
```
### 二、设置Activity样式
```
    <!--
        非全屏的Activity
        <item name="android:windowCloseOnTouchOutside">false</item> 点击activity窗口之外时，是否关闭activity。默认false
        <item name="android:windowIsFloating">true</item> 是否悬浮在其他activity之上。
    -->
    <style name="Theme.NonFullActivity" parent="AppTheme">
        <item name="android:windowCloseOnTouchOutside">false</item>
        <item name="android:windowIsFloating">true</item>
    </style>
```

```
        <activity
            android:name=".activity.NonFullActivity"
            android:theme="@style/Theme.NonFullActivity" />
```


## 启动非全屏的Activity
只调用了 `onPause`, 没有调用 `onStop`
```
2020-01-20 16:37:39.228 4847-4847/com.mackwu.component D/LifecycleActivity: onCreate...
2020-01-20 16:37:39.238 4847-4847/com.mackwu.component D/LifecycleActivity: onStart...
2020-01-20 16:37:39.240 4847-4847/com.mackwu.component D/LifecycleActivity: onResume...

点击跳转：
2020-01-20 16:37:42.372 4847-4847/com.mackwu.component D/LifecycleActivity: onPause...
2020-01-20 16:37:42.389 4847-4847/com.mackwu.component D/NonFullActivity: onCreate...
2020-01-20 16:37:42.393 4847-4847/com.mackwu.component D/NonFullActivity: onStart...
2020-01-20 16:37:42.401 4847-4847/com.mackwu.component D/NonFullActivity: onResume...

点击返回：
2020-01-20 16:37:46.146 4847-4847/com.mackwu.component D/NonFullActivity: onPause...
2020-01-20 16:37:46.152 4847-4847/com.mackwu.component D/LifecycleActivity: onResume...
2020-01-20 16:37:46.663 4847-4847/com.mackwu.component D/NonFullActivity: onStop...
2020-01-20 16:37:46.663 4847-4847/com.mackwu.component D/NonFullActivity: onDestroy...
```