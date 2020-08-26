
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
