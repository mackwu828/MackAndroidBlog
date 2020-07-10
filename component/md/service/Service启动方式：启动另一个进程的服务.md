

## 通过action启动另一个进程的Service
```
/**
 * 通过action启动另一个进程的Service
 * 异常：
 */
fun Activity.startActionService(action: String) = try {
    startService(Intent(action))
} catch (e: Exception) {
    e.printStackTrace()
}
```
