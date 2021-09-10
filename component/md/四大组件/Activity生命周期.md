
在发生某种竞态条件的情况下，可能会导致Activity的onStop()方法在onStart()方法前就发生了?

```
class LifecycleActivity : AppCompatActivity() {

    protected val currentClassName = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(currentClassName, "onCreate...")
    }

    override fun onStart() {
        super.onStart()
        Log.d(currentClassName, "onStart...")
    }

    override fun onResume() {
        super.onResume()
        Log.d(currentClassName, "onResume...")
    }

    override fun onPause() {
        super.onPause()
        Log.d(currentClassName, "onPause...")
    }

    override fun onStop() {
        super.onStop()
        Log.d(currentClassName, "onStop...")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(currentClassName, "onDestroy...")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(currentClassName, "onRestart...")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(currentClassName, "onSaveInstanceState...")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(currentClassName, "onRestoreInstanceState...")
    }
}
```


## onCreate

## onStart

## onResume

## onPause

## onStop

## onDestroy

## onRestart

## onSaveInstanceState
- 当用户按下 HOME 键时
- 长按 HOME 键，选择运行其他的程序时
- 锁屏时
- 从 activity A 中启动一个新的 activity 时
- 屏幕方向切换时

## onRestoreInstanceState



## 启动
```
2020-01-20 16:49:21.068 5068-5068/com.mackwu.component D/LifecycleActivity: onCreate...
2020-01-20 16:49:21.076 5068-5068/com.mackwu.component D/LifecycleActivity: onStart...
2020-01-20 16:49:21.078 5068-5068/com.mackwu.component D/LifecycleActivity: onResume...
```

## 点击返回
点击返回后，Activity被销毁。
```
2020-01-20 16:49:29.856 5068-5068/com.mackwu.component D/LifecycleActivity: onPause...
2020-01-20 16:49:30.244 5068-5068/com.mackwu.component D/LifecycleActivity: onStop...
2020-01-20 16:49:30.244 5068-5068/com.mackwu.component D/LifecycleActivity: onDestroy...
```
重新启动。因为Activity已经被销毁了，所以会重新走onCreate
```
2020-01-21 16:39:30.723 2090-2090/com.mackwu.component D/LifecycleActivity: onCreate...
2020-01-21 16:39:30.730 2090-2090/com.mackwu.component D/LifecycleActivity: onStart...
2020-01-21 16:39:30.732 2090-2090/com.mackwu.component D/LifecycleActivity: onResume...
```

## 点击Home
点击Home后Activity没有被销毁。
```
2020-01-21 16:29:15.525 1804-1804/com.mackwu.component D/LifecycleActivity: onPause...
2020-01-21 16:29:15.570 1804-1804/com.mackwu.component D/LifecycleActivity: onStop...
```
重新启动。因为Activity没有被销毁，所以会走onRestart，不会走onCreate。
```
2020-01-21 16:29:30.437 1804-1804/com.mackwu.component D/LifecycleActivity: onRestart...
2020-01-21 16:29:30.438 1804-1804/com.mackwu.component D/LifecycleActivity: onStart...
2020-01-21 16:29:30.439 1804-1804/com.mackwu.component D/LifecycleActivity: onResume...
```


## 启动另一个Activity
会先调用 `onPause`, 等到另一个Activity调用了 `onResume` 后，再调用 `onStop`。
```
2020-01-20 16:56:55.087 5263-5263/com.mackwu.component D/LifecycleActivity: onPause...

2020-01-20 16:56:55.105 5263-5263/com.mackwu.component D/TargetActivity: onCreate...
2020-01-20 16:56:55.110 5263-5263/com.mackwu.component D/TargetActivity: onStart...
2020-01-20 16:56:55.116 5263-5263/com.mackwu.component D/TargetActivity: onResume...

2020-01-20 16:56:55.560 5263-5263/com.mackwu.component D/LifecycleActivity: onStop...
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

## 异常退出
`onSaveInstanceState` 会在 `onStop` 之前调用，和`onPause`没有严格的先后顺序。
```
2020-01-21 17:45:49.455 2542-2542/com.mackwu.component D/LifecycleActivity: onPause...
2020-01-21 17:45:49.542 2542-2542/com.mackwu.component D/LifecycleActivity: onSaveInstanceState...
2020-01-21 17:45:49.559 2542-2542/com.mackwu.component D/LifecycleActivity: onStop...

重新进入应用：
2020-01-21 17:46:02.559 2542-2542/com.mackwu.component D/LifecycleActivity: onRestart...
2020-01-21 17:46:02.559 2542-2542/com.mackwu.component D/LifecycleActivity: onStart...
2020-01-21 17:46:02.559 2542-2542/com.mackwu.component D/LifecycleActivity: onRestoreInstanceState...
2020-01-21 17:46:02.560 2542-2542/com.mackwu.component D/LifecycleActivity: onResume...
```

## 锁屏和开屏
```
锁屏：
2020-01-20 16:52:41.302 5068-5068/com.mackwu.component D/LifecycleActivity: onPause...
2020-01-20 16:52:41.310 5068-5068/com.mackwu.component D/LifecycleActivity: onStop...

开屏：
2020-01-20 16:54:06.188 5068-5068/com.mackwu.component D/LifecycleActivity: onRestart...
2020-01-20 16:54:06.203 5068-5068/com.mackwu.component D/LifecycleActivity: onStart...
2020-01-20 16:54:06.223 5068-5068/com.mackwu.component D/LifecycleActivity: onResume...
```