
在开发中，经常需要做一些耗时操作，比如网络请求，如果在主线程中执行耗时操作，会导致卡顿，过长时间还会导致 ANR。
所以耗时操作需要放在子线程中，而在耗时操作结束后常常需要更新UI，如果直接在子线程中更新UI会抛出异常 Only the original thread that created a view hierarchy can touch its views。这时就引入了Handler切换到主线程来更新UI。

